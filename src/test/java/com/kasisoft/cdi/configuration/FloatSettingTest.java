package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.cdi.testbasis.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class FloatSettingTest extends AbstractEjbTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1.0, -1.0
      { FloatObj1.class.getSimpleName(), Float.valueOf( (float) 1.0 ) },
      { FloatObj2.class.getSimpleName(), Float.valueOf( (float) -1.0 ) },
      // properties are set to: INF, -INF
      { FloatObj3.class.getSimpleName(), Float.valueOf( Float.POSITIVE_INFINITY ) },
      { FloatObj4.class.getSimpleName(), Float.valueOf( Float.NEGATIVE_INFINITY ) },
      // properties are set to: NAN
      { FloatObj5.class.getSimpleName(), Float.valueOf( Float.NaN ) },
      // properties aren't set. defaults are set to: 1.0, -1.0
      { FloatObj6.class.getSimpleName(), Float.valueOf( (float) 1.0 ) },
      { FloatObj7.class.getSimpleName(), Float.valueOf( (float) -1.0 ) },
      // properties with customized names
      { FloatObj8.class.getSimpleName(), Float.valueOf( (float) 1.0 ) },
      // missing value
      { FloatObj9.class.getSimpleName(), null },
    };
  }
  
  @Test(dataProvider = "data")
  public void floats( String name, Float expected ) throws Exception {
    GetFloat getter = (GetFloat) getContainer().getContext().lookup( "java:global/configuration/" + name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetFloat {
    
    Float getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class FloatObj1 implements GetFloat {
    
    @Inject @Setting
    private Float    float_value_1;
    
    public Float getValue() { return float_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj2 implements GetFloat {
    
    @Inject @Setting
    private Float    float_value_2;
    
    public Float getValue() { return float_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj3 implements GetFloat {
    
    @Inject @Setting
    private Float    float_value_3;
    
    public Float getValue() { return float_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj4 implements GetFloat {
    
    @Inject @Setting
    private Float    float_value_4;
    
    public Float getValue() { return float_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj5 implements GetFloat {
    
    @Inject @Setting
    private Float    float_value_5;
    
    public Float getValue() { return float_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj6 implements GetFloat {
    
    @Inject @Setting(defaultValue = "1.0")
    private Float    float_value_6;
    
    public Float getValue() { return float_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class FloatObj7 implements GetFloat {
    
    @Inject @Setting(defaultValue = "-1.0")
    private Float    float_value_7;
    
    public Float getValue() { return float_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj8 implements GetFloat {
    
    @Inject @Setting("float_customized_8")
    private Float    float_value_8;
    
    public Float getValue() { return float_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class FloatObj9 implements GetFloat {
    
    @Inject @Setting(required = false)
    private Float    float_value_is_missing_and_no_default;
    
    public Float getValue() { return float_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
