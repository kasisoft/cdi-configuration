package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class FloatPrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1.0, -1.0
      { Float1.class.getSimpleName(), (float) 1.0 },
      { Float2.class.getSimpleName(), (float) -1.0  },
      // properties are set to: INF, -INF
      { Float3.class.getSimpleName(), Float.POSITIVE_INFINITY },
      { Float4.class.getSimpleName(), Float.NEGATIVE_INFINITY  },
      // properties are set to: NAN
      { Float5.class.getSimpleName(), Float.NaN },
      // properties aren't set. defaults are set to: 1.0, -1.0
      { Float6.class.getSimpleName(), (float) 1.0  },
      { Float7.class.getSimpleName(), (float) -1.0 },
      // properties with customized names
      { Float8.class.getSimpleName(), (float) 1.0 },
    };
  }
  
  @Test(dataProvider = "data")
  public void floats( String name, float expected ) throws Exception {
    GetFloat getter = this.<GetFloat>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<GetFloat>lookup( Float9.class.getSimpleName() );
  }
  
  private static interface GetFloat {
    
    float getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Float1 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_1;
    
    public float getValue() { return float_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float2 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_2;
    
    public float getValue() { return float_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float3 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_3;
    
    public float getValue() { return float_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float4 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_4;
    
    public float getValue() { return float_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float5 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_5;
    
    public float getValue() { return float_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float6 implements GetFloat {
    
    @Inject @PrimitiveSetting(defaultValue = "1.0")
    private float    float_value_6;
    
    public float getValue() { return float_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Float7 implements GetFloat {
    
    @Inject @PrimitiveSetting(defaultValue = "-1.0")
    private float    float_value_7;
    
    public float getValue() { return float_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float8 implements GetFloat {
    
    @Inject @PrimitiveSetting("float_customized_8")
    private float    float_value_8;
    
    public float getValue() { return float_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Float9 implements GetFloat {
    
    @Inject @PrimitiveSetting
    private float    float_value_is_missing_and_no_default;
    
    public float getValue() { return float_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
