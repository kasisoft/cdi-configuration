package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class SettingsLoaderTest {

  @Test
  public void checkLoadedValues() {
    assertThat( SettingsLoader.instance().getValue( "property_1" ), is( "Bibo" ) );
    assertThat( SettingsLoader.instance().getValue( "property_2" ), is( nullValue() ) );
    assertThat( SettingsLoader.instance().getValue( "property_2", "" ), is( "" ) );
    assertThat( SettingsLoader.instance().getValue( "property_2", "Gollum" ), is( "Gollum" ) );
  }
  
} /* ENDCLASS */
