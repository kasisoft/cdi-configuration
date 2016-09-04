package com.kasisoft.cdi.configuration;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.libs.common.config.*;

import java.util.*;

import java.nio.file.*;

import java.io.*;

import lombok.*;
import lombok.experimental.*;
import lombok.extern.slf4j.*;

/**
 * We're using a simple approach here as we're just looking for toplevel files named {@link #NAME_SETTINGS}. Supporting
 * patterns etc. would require to analyze classpath resources like jar files which is too much work for such a simple
 * requirement.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsLoader {

  static final String NAME_SETTINGS = "settings.properties";

  static SettingsLoader   instance = null;

  static {
    instance = new SettingsLoader();
    try {
      instance.load( NAME_SETTINGS );
    } catch( IOException ex ) {
      log.error( could_not_load_properties.format( NAME_SETTINGS, ex.getLocalizedMessage() ) );
      throw new RuntimeException( could_not_load_properties.format( NAME_SETTINGS, ex.getLocalizedMessage() ), ex );
    }
  }
  
  public static SettingsLoader instance() {
    return instance;
  }
  
  PropertyResolver   resolver;

  private SettingsLoader() {
    resolver = new PropertyResolver( Thread.currentThread().getContextClassLoader() );
    resolver.withSystemSubstitutions();
  }

  public void load( @NonNull Map<String, String> properties ) throws IOException {
    resolver.load( properties );
    logProperties();
  }
  
  public void load( @NonNull Path path ) throws IOException {
    resolver.load( path.toFile() );
    logProperties();
  }
    
  public void load( @NonNull String propfile ) throws IOException {
    resolver.load( propfile );
    logProperties();
  }

  private void logProperties() {
    if( log.isDebugEnabled() ) {
      
      String[] keys = resolver.getPropertyNames();
      Arrays.sort( keys );
      
      for( String key : keys ) {
        log.debug( property.format( key, resolver.getProperty( key ) ) );
      }
      
    }
  }

  public String getValue( @NonNull String key ) {
    return resolver.getProperty( key );
  }

  public String getValue( @NonNull String key, String defvalue ) {
    return resolver.getProperty( key, defvalue );
  }

} /* ENDCLASS */