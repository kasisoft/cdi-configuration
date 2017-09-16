package com.kasisoft.cdi.configuration;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.libs.common.base.*;
import com.kasisoft.libs.common.config.*;

import java.util.function.*;

import java.util.*;

import java.nio.file.*;

import lombok.extern.slf4j.*;

import lombok.experimental.*;

import lombok.*;

/**
 * We're using a simple approach here as we're just looking for toplevel files named {@link #NAME_SETTINGS}. Supporting
 * patterns etc. would require to analyze classpath resources like jar files which is too much work for such a simple
 * requirement.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsLoader {

  private static final String NAME_SETTINGS = "settings.properties";

  private static SettingsLoader   instance = null;

  static {
    instance = new SettingsLoader();
    instance.load( NAME_SETTINGS );
  }
  
  public static SettingsLoader instance() {
    return instance;
  }
  
  PropertyResolver   resolver;

  private SettingsLoader() {
    resolver = new PropertyResolver( Thread.currentThread().getContextClassLoader() );
    resolver.withSystemSubstitutions();
  }

  public void load( @NonNull Map<String, String> properties ) {
    load( properties, null );
  }
  
  public void load( @NonNull Map<String, String> properties, Consumer<Exception> exceptionHandler ) {
    try {
      resolver.load( properties );
    } catch( Exception ex ) {
      loadingFailure( ex, exceptionHandler );
    }
    logProperties();
  }
  
  public void load( @NonNull Path path ) {
    load( path, null );
  }
  
  public void load( @NonNull Path path, Consumer<Exception> exceptionHandler ) {
    try {
      resolver.load( path.toFile() );
    } catch( Exception ex ) {
      loadingFailure( ex, exceptionHandler );
    }
    logProperties();
  }
  
  public void load( @NonNull String propfile ) {
    load( propfile, null );
  }
  
  public void load( @NonNull String propfile, Consumer<Exception> exceptionHandler ) {
    try {
      resolver.load( propfile );
    } catch( Exception ex ) {
      loadingFailure( ex, exceptionHandler );
    }
    logProperties();
  }

  private void loadingFailure( Exception ex, Consumer<Exception> handler ) {
    if( handler == null ) {
      handler = $ -> { throw FailureException.wrap($); };
    }
    log.warn( could_not_load_properties.format( ex.getLocalizedMessage() ), ex );
    handler.accept( ex );
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