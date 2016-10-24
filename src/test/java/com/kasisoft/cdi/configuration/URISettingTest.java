package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import java.net.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class URISettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() throws URISyntaxException {
    return new Object[][] {
      
      { URIObj1.class, new URI( "file:/E:/output.log" ) },
      { URIObj2.class, new URI( "http://www.amiga-news.de" ) },
      
      // missing value
      { URIObj3.class, null },
      
      // customized name
      { URIObj4.class, new URI( "http://blog.gwup.net" ) },
      
      // default value
      { URIObj5.class, new URI( "http://www.scienceblogs.org" ) },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void uris( Class<GetURI> clazz, URI expected ) throws Exception {
    GetURI getter = this.<GetURI>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetURI {
    
    URI getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class URIObj1 implements GetURI {
    
    @Inject @Setting
    private URI    uri_value_1;
    
    public URI getValue() { return uri_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URIObj2 implements GetURI {
    
    @Inject @Setting
    private URI    uri_value_2;
    
    public URI getValue() { return uri_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URIObj3 implements GetURI {
    
    @Inject @Setting(required = false)
    private URI    uri_value_is_missing_and_no_default;
    
    public URI getValue() { return uri_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URIObj4 implements GetURI {
    
    @Inject @Setting("uri_customized_4")
    private URI    uri_4;
    
    public URI getValue() { return uri_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URIObj5 implements GetURI {
    
    @Inject @Setting(defaultValue="http://www.scienceblogs.org")
    private URI    uri_5;
    
    public URI getValue() { return uri_5; }
    
  } /* ENDCLASS */
  
} /* ENDCLASS */
