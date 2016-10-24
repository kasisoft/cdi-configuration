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
public class DoublePrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1.0, -1.0
      { Double1.class, 1.0 },
      { Double2.class, -1.0  },
      
      // properties are set to: INF, -INF
      { Double3.class, Double.POSITIVE_INFINITY },
      { Double4.class, Double.NEGATIVE_INFINITY  },
      
      // properties are set to: NAN
      { Double5.class, Double.NaN },
      
      // properties aren't set. defaults are set to: 1.0, -1.0
      { Double6.class, 1.0  },
      { Double7.class, -1.0 },
      
      // properties with customized names
      { Double8.class, 1.0 },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void doubles( Class<GetDouble> clazz, double expected ) throws Exception {
    GetDouble getter = this.<GetDouble>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<Double9>lookup( Double9.class );
  }
  
  private static interface GetDouble {
    
    double getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Double1 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_1;
    
    public double getValue() { return double_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double2 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_2;
    
    public double getValue() { return double_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double3 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_3;
    
    public double getValue() { return double_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double4 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_4;
    
    public double getValue() { return double_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double5 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_5;
    
    public double getValue() { return double_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double6 implements GetDouble {
    
    @Inject @PrimitiveSetting(defaultValue = "1.0")
    private double    double_value_6;
    
    public double getValue() { return double_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Double7 implements GetDouble {
    
    @Inject @PrimitiveSetting(defaultValue = "-1.0")
    private double    double_value_7;
    
    public double getValue() { return double_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double8 implements GetDouble {
    
    @Inject @PrimitiveSetting("double_customized_8")
    private double    double_value_8;
    
    public double getValue() { return double_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Double9 implements GetDouble {
    
    @Inject @PrimitiveSetting
    private double    double_value_is_missing_and_no_default;
    
    public double getValue() { return double_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
