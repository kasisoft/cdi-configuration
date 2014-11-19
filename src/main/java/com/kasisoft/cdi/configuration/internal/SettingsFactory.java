package com.kasisoft.cdi.configuration.internal;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.cdi.configuration.*;
import com.kasisoft.libs.common.util.*;
import com.kasisoft.libs.common.xml.adapters.*;

import javax.annotation.*;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.*;

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
  
  @Produces @DirSetting
  public File getDirSetting( InjectionPoint ip ) {
    return getFileSetting( ip );
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

  private File getFileSetting( InjectionPoint ip ) {
    DirSetting setting = ip.getAnnotated().getAnnotation( DirSetting.class );
    File       result  = getSetting( ip, File.class, setting.value(), setting.defaultValue(), Boolean.valueOf( setting.required() ) );
    if( result != null ) {
      // check whether we should extend the file
      String extension = StringFunctions.cleanup( setting.extension() );
      if( extension != null ) {
        result = extendDir( result, extension );
      }
    }
    return result;
  }
  
  private File extendDir( File dir, String extension ) {
    if( ! dir.isDirectory() ) {
      String message = base_is_not_a_dir.format( dir.getAbsolutePath() );
      log.error( message );
      throw new IllegalStateException( message );
    }
    File result = new File( dir, extension );
    try {
      result = result.getCanonicalFile();
    } catch( IOException ex ) {
      String message = canonical_failure.format( result.getPath(), ex.getLocalizedMessage() );
      log.error( message );
      throw new IllegalStateException();
    }
    if( ! result.mkdirs() ) {
      String message = makedir_failure.format( result.getAbsolutePath() );
      log.error( message );
      throw new IllegalStateException();
    }
    return result;
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

    String value = SettingsLoader.instance().getValue( key, StringFunctions.cleanup( defvalue ) );

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