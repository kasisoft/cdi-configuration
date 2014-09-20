package com.kasisoft.cdi.configuration.internal;

import static com.kasisoft.cdi.configuration.internal.Messages.*;

import com.kasisoft.libs.common.util.*;

import javax.annotation.*;
import javax.inject.*;

import java.util.*;

import java.io.*;

import lombok.*;
import lombok.extern.slf4j.*;

/**
 * We're using a simple approach here as we're just looking for toplevel files named {@link #NAME_SETTINGS}. Supporting
 * patterns etc. would require to analyze classpath resources like jar files which is too much work for such a simple
 * requirement.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Singleton
@Slf4j
public class SettingsLoader {

  private static final String NAME_SETTINGS = "settings.properties";

  private PropertyResolver   resolver;
  
  @PostConstruct
  private void loadConfigurations() throws IOException {

    resolver = new PropertyResolver( Thread.currentThread().getContextClassLoader() );
    
    resolver.withSystemSubstitutions();
    
    resolver.load( NAME_SETTINGS );
    
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