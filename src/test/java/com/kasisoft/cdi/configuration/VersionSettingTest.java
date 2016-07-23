package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.libs.common.model.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import java.text.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class VersionSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() throws ParseException {
    return new Object[][] {
      { VersionObj1.class.getSimpleName(), new Version("1.0") },
      { VersionObj2.class.getSimpleName(), new Version("2.1.3") },
      { VersionObj3.class.getSimpleName(), new Version("1.0.4.qualifier") },
      { VersionObj4.class.getSimpleName(), new Version("1.0.4_qualifier") },
      { VersionObj5.class.getSimpleName(), new Version("1.9") },
      // missing value
      { VersionObj6.class.getSimpleName(), null },
      // customized name
      { VersionObj7.class.getSimpleName(), new Version("3.0") },
    };
  }
  
  @Test(dataProvider = "data")
  public void versions( String name, Version expected ) throws Exception {
    GetVersion getter = this.<GetVersion>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetVersion {
    
    Version getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class VersionObj1 implements GetVersion {
    
    @Inject @Setting
    private Version    version_value_1;
    
    public Version getValue() { return version_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class VersionObj2 implements GetVersion {
    
    @Inject @Setting
    private Version    version_value_2;
    
    public Version getValue() { return version_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class VersionObj3 implements GetVersion {
    
    @Inject @Setting
    private Version    version_value_3;
    
    public Version getValue() { return version_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class VersionObj4 implements GetVersion {
    
    @Inject @Setting
    private Version    version_value_4;
    
    public Version getValue() { return version_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class VersionObj5 implements GetVersion {
    
    @Inject @Setting(defaultValue = "1.9")
    private Version    version_value_5;
    
    public Version getValue() { return version_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class VersionObj6 implements GetVersion {
    
    @Inject @Setting(required = false)
    private Version    version_value_is_missing_and_no_default;
    
    public Version getValue() { return version_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class VersionObj7 implements GetVersion {
    
    @Inject @Setting("version_customized_7")
    private Version    version_7;
    
    public Version getValue() { return version_7; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
