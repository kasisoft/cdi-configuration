package com.kasisoft.cdi.configuration.internal;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.cdi.configuration.*;
import com.kasisoft.libs.common.model.*;
import com.kasisoft.libs.common.text.*;

import com.kasisoft.libs.common.xml.adapters.*;

import javax.annotation.*;
import javax.enterprise.inject.*;
import javax.enterprise.inject.spi.*;

import java.util.*;

import java.net.*;

import java.awt.*;

import java.io.*;

import java.nio.file.*;

import lombok.*;
import lombok.experimental.*;
import lombok.extern.slf4j.*;

/**
 * This factory is responsible to provide the values needed for the configuration.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsFactory {

  Map<Class<?>,TypeAdapter<String,?>>   adapters;

  @PostConstruct
  private void configure() {
    adapters = new Hashtable<>();
    adapters.put( Boolean   . class , new BooleanAdapter () );
    adapters.put( Double    . class , new DoubleAdapter  () );
    adapters.put( Float     . class , new FloatAdapter   () );
    adapters.put( File      . class , new FileAdapter    ( true ) );
    adapters.put( Integer   . class , new IntegerAdapter () );
    adapters.put( Short     . class , new ShortAdapter   () );
    adapters.put( Byte      . class , new ByteAdapter    () );
    adapters.put( Long      . class , new LongAdapter    () );
    adapters.put( String    . class , new StringAdapter  () );
    adapters.put( Version   . class , new VersionAdapter ( true ) );
    adapters.put( Color     . class , new ColorAdapter   () );
    adapters.put( URI       . class , new URIAdapter     () );
    adapters.put( URL       . class , new URLAdapter     () );
  }

  @Produces @Setting
  public URL getSettingAsURL( InjectionPoint ip ) {
    return getSetting( ip, URL.class );
  }

  @Produces @Setting
  public Version getSettingAsVersion( InjectionPoint ip ) {
    return getSetting( ip, Version.class );
  }

  @Produces @Setting
  public Color getSettingAsColor( InjectionPoint ip ) {
    return getSetting( ip, Color.class );
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
  public Float getSettingAsFloat( InjectionPoint ip ) {
    return getSetting( ip, Float.class );
  }

  @Produces @PrimitiveSetting
  public float getSettingAsFloatPrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Float.class );
  }

  @Produces @Setting
  public File getSettingAsFile( InjectionPoint ip ) {
    return getSetting( ip, File.class );
  }

  @Produces @Setting
  public URI getSettingAsURI( InjectionPoint ip ) {
    return getSetting( ip, URI.class );
  }

  @Produces @Setting
  public Path getSettingAsPath( InjectionPoint ip ) {
    URI uri = getSettingAsURI( ip );
    if( uri != null ) {
      String scheme = StringFunctions.cleanup( uri.getScheme() );
      if( scheme == null ) {
        return Paths.get( uri.toString() );
      } else {
        return Paths.get( uri );
      }
    }
    return null;
  }

  @Produces @DirSetting
  public File getDirSettingAsFile( InjectionPoint ip ) {
    DirSetting setting = ip.getAnnotated().getAnnotation( DirSetting.class );
    File       result  = getSetting( ip.getMember().getName(), File.class, setting.value(), setting.defaultValue(), setting.required() );
    if( result != null ) {
      // check whether we should extend the file
      String extension = StringFunctions.cleanup( setting.extension() );
      if( extension != null ) {
        result = extendDir( result, extension );
      }
    }
    if( result != null ) {
      try {
        result = result.getCanonicalFile();
      } catch( IOException ex ) {
        throw error( canonical_failure.format( result.getPath(), ex.getLocalizedMessage() ),  ex );
      }
    }
    return result;
  }
  
  private IllegalStateException error( String message, Exception ex ) {
    if( ex != null ) {
      log.warn( message, ex );
      return new IllegalStateException( message, ex );
    } else {
      log.warn( message );
      return new IllegalStateException( message );
    }
  }
  
  @Produces @DirSetting
  public Path getDirSettingAsPath( InjectionPoint ip ) {
    File file = getDirSettingAsFile( ip );
    if( file != null ) {
      return file.toPath();
    } else {
      return null;
    }
  }
  
  @Produces @DirSetting
  public URI getDirSettingAsURI( InjectionPoint ip ) {
    File file = getDirSettingAsFile( ip );
    if( file != null ) {
      return file.toURI();
    }
    return null;
  }

  @Produces @DirSetting
  public String getDirSettingAsString( InjectionPoint ip ) {
    File file = getDirSettingAsFile( ip );
    if( file != null ) {
      return file.getAbsolutePath();
    } else {
      return null;
    }
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
  public Long getSettingAsLong( InjectionPoint ip ) {
    return getSetting( ip, Long.class );
  }

  @Produces @PrimitiveSetting
  public long getSettingAsLongPrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Long.class);
  }

  @Produces @Setting
  public Byte getSettingAsByte( InjectionPoint ip ) {
    return getSetting( ip, Byte.class );
  }

  @Produces @PrimitiveSetting
  public byte getSettingAsBytePrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Byte.class);
  }

  @Produces @Setting
  public Short getSettingAsShort( InjectionPoint ip ) {
    return getSetting( ip, Short.class );
  }

  @Produces @PrimitiveSetting
  public short getSettingAsShortPrimitive( InjectionPoint ip ) {
    return getPrimitiveSetting( ip, Short.class);
  }

  @Produces @Setting
  public String getSettingAsString( InjectionPoint ip ) {
    return getSetting( ip, String.class );
  }

  private File extendDir( File dir, String extension ) {
    if( ! dir.isDirectory() ) {
      dir.mkdirs();
    }
    if( ! dir.isDirectory() ) {
      throw error( base_is_not_a_dir.format( dir.getAbsolutePath() ), null );
    }
    File result = new File( dir, extension );
    result.mkdirs();
    if( ! result.isDirectory() ) {
      throw error( makedir_failure.format( result.getAbsolutePath() ), null );
    }
    return result;
  }

  private <T> T getSetting( InjectionPoint ip, Class<T> type ) {
    Setting setting = ip.getAnnotated().getAnnotation( Setting.class );
    return getSetting( ip.getMember().getName(), setting, type );
  }

  private <T> T getSetting( String memberName, Setting setting, Class<T> type ) {
    return getSetting( memberName, type, setting.value(), setting.defaultValue(), Boolean.valueOf( setting.required() ) );
  }

  private <T> T getPrimitiveSetting( InjectionPoint ip, Class<T> type ) {
    PrimitiveSetting setting = ip.getAnnotated().getAnnotation( PrimitiveSetting.class );
    return getSetting( ip.getMember().getName(), type, setting.value(), setting.defaultValue(), true );
  }

  private <T> T getSetting( String memberName, Class<T> type, String key, String defvalue, boolean required ) {
    
    key = StringFunctions.cleanup( key );
    if( key == null ) {
      // the user hasn't specified a custom property name, so we'll take the member name instead
      key = memberName;
    }

    String value = SettingsLoader.instance().getValue( key, StringFunctions.cleanup( defvalue ) );

    if( (value == null) && required ) {
      throw error( missing_required_value.format( key ), null );
    }

    try {
      return (T) adapters.get( type ).unmarshal( value );
    } catch( Exception ex ) {
      throw error( invalid_type.format( value, key, type.getName(), ex.getLocalizedMessage() ), ex );
    }
    
  }

} /* ENDCLASS */