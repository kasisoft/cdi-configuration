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
public class DoubleSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1.0, -1.0
      { DoubleObj1.class, Double.valueOf( 1.0 ) },
      { DoubleObj2.class, Double.valueOf( -1.0 )  },
      
      // properties are set to: INF, -INF
      { DoubleObj3.class, Double.valueOf( Double.POSITIVE_INFINITY ) },
      { DoubleObj4.class, Double.valueOf( Double.NEGATIVE_INFINITY ) },
      
      // properties are set to: NAN
      { DoubleObj5.class, Double.valueOf( Double.NaN ) },
      
      // properties aren't set. defaults are set to: 1.0, -1.0
      { DoubleObj6.class, Double.valueOf( 1.0 ) },
      { DoubleObj7.class, Double.valueOf( -1.0 ) },
      
      // properties with customized names
      { DoubleObj8.class, Double.valueOf( 1.0 ) },
      
      // missing value
      { DoubleObj9.class, null },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void doubles( Class<GetDouble> clazz, Double expected ) throws Exception {
    GetDouble getter = this.<GetDouble>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetDouble {
    
    Double getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class DoubleObj1 implements GetDouble {
    
    @Inject @Setting
    private Double    double_value_1;
    
    public Double getValue() { return double_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj2 implements GetDouble {
    
    @Inject @Setting
    private Double    double_value_2;
    
    public Double getValue() { return double_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj3 implements GetDouble {
    
    @Inject @Setting
    private Double    double_value_3;
    
    public Double getValue() { return double_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj4 implements GetDouble {
    
    @Inject @Setting
    private Double    double_value_4;
    
    public Double getValue() { return double_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj5 implements GetDouble {
    
    @Inject @Setting
    private Double    double_value_5;
    
    public Double getValue() { return double_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj6 implements GetDouble {
    
    @Inject @Setting(defaultValue = "1.0")
    private Double    double_value_6;
    
    public Double getValue() { return double_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class DoubleObj7 implements GetDouble {
    
    @Inject @Setting(defaultValue = "-1.0")
    private Double    double_value_7;
    
    public Double getValue() { return double_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj8 implements GetDouble {
    
    @Inject @Setting("double_customized_8")
    private Double    double_value_8;
    
    public Double getValue() { return double_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class DoubleObj9 implements GetDouble {
    
    @Inject @Setting(required = false)
    private Double    double_value_is_missing_and_no_default;
    
    public Double getValue() { return double_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
