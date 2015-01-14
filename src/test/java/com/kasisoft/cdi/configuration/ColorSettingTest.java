package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import java.awt.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class ColorSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      { ColorObj1.class.getSimpleName(), new Color( 0xff0000 ) },
      { ColorObj2.class.getSimpleName(), new Color( 0xff00ffaa, true ) },
      // missing value
      { ColorObj3.class.getSimpleName(), null },
      // customized name
      { ColorObj4.class.getSimpleName(), new Color( 0x00ffaa ) },
      // default value
      { ColorObj5.class.getSimpleName(), new Color( 0xccffaa ) },
      // invalid and not required
      { ColorObj6.class.getSimpleName(), null },
    };
  }
  
  @Test(dataProvider = "data")
  public void colors( String name, Color expected ) throws Exception {
    GetColor getter = this.<GetColor>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetColor {
    
    Color getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class ColorObj1 implements GetColor {
    
    @Inject @Setting
    private Color    color_value_1;
    
    public Color getValue() { return color_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ColorObj2 implements GetColor {
    
    @Inject @Setting
    private Color    color_value_2;
    
    public Color getValue() { return color_value_2; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class ColorObj3 implements GetColor {
    
    @Inject @Setting(required = false)
    private Color    color_value_is_missing_and_no_default;
    
    public Color getValue() { return color_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ColorObj4 implements GetColor {
    
    @Inject @Setting("color_customized_4")
    private Color    color_4;
    
    public Color getValue() { return color_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ColorObj5 implements GetColor {
    
    @Inject @Setting(defaultValue="#ccffaa")
    private Color    color_value_5;
    
    public Color getValue() { return color_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ColorObj6 implements GetColor {
    
    @Inject @Setting(required = false)
    private Color    color_value_6;
    
    public Color getValue() { return color_value_6; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
