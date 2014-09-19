package com.kasisoft.cdi.configuration.internal;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.cdi.configuration.*;
import com.kasisoft.libs.common.util.*;
import com.kasisoft.libs.common.xml.adapters.*;

import javax.annotation.*;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.*;
import javax.inject.*;

import java.util.*;

import java.io.*;

import lombok.extern.slf4j.*;

/**
 * This factory is responsible to provide the values needed for the configuration.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Slf4j
public class SettingsFactory {

  @Inject
  private SettingsLoader                        loader;

  private Map<Class<?>,TypeAdapter<String,?>>   adapters;

  @PostConstruct
  private void configure() {
    adapters = new Hashtable<>();
    adapters.put( Boolean . class , new BooleanAdapter () );
    adapters.put( Double  . class , new DoubleAdapter  () );
    adapters.put( File    . class , new FileAdapter    ( true ) );
    adapters.put( Integer . class , new IntegerAdapter () );
    adapters.put( String  . class , new StringAdapter  () );
  }

  @Produces @Setting
  public Boolean getSettingAsBoolean( InjectionPoint ip ) {
    return getSetting( ip, Boolean.class );
  }

  @Produces @PrimitiveSetting
  public boolean getSettingAsBooleanPrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Boolean.class );
  }

  @Produces @Setting
  public Double getSettingAsDouble( InjectionPoint ip ) {
    return getSetting( ip, Double.class );
  }

  @Produces @PrimitiveSetting
  public double getSettingAsDoublePrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Double.class );
  }

  @Produces @Setting
  public File getSettingAsFile( InjectionPoint ip ) {
    return getSetting( ip, File.class );
  }

  @Produces @Setting
  public Integer getSettingAsInteger( InjectionPoint ip ) {
    return getSetting( ip, Integer.class );
  }

  @Produces @PrimitiveSetting
  public int getSettingAsIntegerPrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Integer.class);
  }

  @Produces @Setting
  public String getSettingAsString( InjectionPoint ip ) {
    return getSetting( ip, String.class );
  }

  private <T> T getSetting( InjectionPoint ip, Class<T> type ) {
    Setting setting = ip.getAnnotated().getAnnotation( Setting.class );
    return getSetting( ip, type, setting.value(), setting.defaultValue(), Boolean.valueOf( setting.required() ) );
  }

  private <T> T getPrimitiveSetting( InjectionPoint ip, Class<T> type ) {
    PrimitiveSetting setting = ip.getAnnotated().getAnnotation( PrimitiveSetting.class );
    return getSetting( ip, type, setting.value(), setting.defaultValue(), null );
  }
  
  private <T> T getSetting( InjectionPoint ip, Class<T> type, String key, String defvalue, Boolean required ) {
    
    key = StringFunctions.cleanup( key );
    if( key == null ) {
      // the user hasn't specified a custom property name, so we'll take the member name instead
      key = ip.getMember().getName();
    }

    String value = loader.getValue( key, defvalue );

    if( value == null ) {
      if( (required == null) || required.booleanValue() ) {
        String message = missing_required_value.format( key );
        log.error( message );
        throw new IllegalStateException( message );
      }
    }

    try {
      return (T) adapters.get( type ).unmarshal( value );
    } catch( Exception ex ) {
      String message = invalid_type.format( value, key, type.getName(), ex.getLocalizedMessage() );
      log.error( message );
      throw new IllegalStateException( message );
    }
    
  }

} /* ENDCLASS */