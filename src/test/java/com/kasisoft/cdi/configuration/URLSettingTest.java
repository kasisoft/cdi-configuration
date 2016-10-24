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
public class URLSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() throws MalformedURLException {
    return new Object[][] {
      
      { URLObj1.class, new URL( "file:/E:/output.log" ) },
      { URLObj2.class, new URL( "http://www.amiga-news.de" ) },
      
      // missing value
      { URLObj3.class, null },
      
      // customized name
      { URLObj4.class, new URL( "http://blog.gwup.net" ) },
      
      // default value
      { URLObj5.class, new URL( "http://www.scienceblogs.org" ) },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void urls( Class<GetURL> clazz, URL expected ) throws Exception {
    GetURL getter = this.<GetURL>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetURL {
    
    URL getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class URLObj1 implements GetURL {
    
    @Inject @Setting
    private URL    url_value_1;
    
    public URL getValue() { return url_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URLObj2 implements GetURL {
    
    @Inject @Setting
    private URL    url_value_2;
    
    public URL getValue() { return url_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URLObj3 implements GetURL {
    
    @Inject @Setting(required = false)
    private URL    url_value_is_missing_and_no_default;
    
    public URL getValue() { return url_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URLObj4 implements GetURL {
    
    @Inject @Setting("url_customized_4")
    private URL    url_4;
    
    public URL getValue() { return url_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class URLObj5 implements GetURL {
    
    @Inject @Setting(defaultValue="http://www.scienceblogs.org")
    private URL    url_5;
    
    public URL getValue() { return url_5; }
    
  } /* ENDCLASS */
  
} /* ENDCLASS */
