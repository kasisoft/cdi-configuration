package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.cdi.testbasis.*;

import org.testng.annotations.*;

import java.net.*;

import java.io.*;

import lombok.*;
import lombok.experimental.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractConfigurationTest extends AbstractEjbTest {

  static String    projectdirname = null;
  
  @SuppressWarnings("null")
  @BeforeSuite
  public static void determineConfiguration() throws Exception {
    URL  url  = AbstractConfigurationTest.class.getResource( "AbstractConfigurationTest.class" );
    File file = new File( url.toURI() ).getParentFile().getCanonicalFile();
    while( file != null ) {
      File pom = new File( file, "pom.xml" );
      if( pom.isFile() ) {
        break;
      }
      file = file.getParentFile();
    }
    assertThat( file, is( notNullValue() ) );
    projectdirname = file.getName();
  }
  
  protected <T> T lookup( String name ) throws Exception {
    T result = (T) getContainer().getContext().lookup( String.format( "java:global/%s/%s", projectdirname, name ) );
    assertThat( result, is( notNullValue() ) );
    return result;
  }
  
} /* ENDCLASS */
