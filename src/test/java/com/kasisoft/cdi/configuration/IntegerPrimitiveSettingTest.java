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
public class IntegerPrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1, -1
      { Int1.class, 1 },
      { Int2.class, -1  },
      
      // properties are set to: MAX, -MAX
      { Int3.class, Integer.MAX_VALUE },
      { Int4.class, Integer.MIN_VALUE },
      
      // properties aren't set. defaults are set to: 1, -1
      { Int5.class, 1  },
      { Int6.class, -1 },
      
      // properties with customized names
      { Int7.class, 1 },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void ints( Class<GetInt> clazz, int expected ) throws Exception {
    GetInt getter = this.<GetInt>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<Int8>lookup( Int8.class );
  }
  
  private static interface GetInt {
    
    int getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Int1 implements GetInt {
    
    @Inject @PrimitiveSetting
    private int    int_value_1;
    
    public int getValue() { return int_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int2 implements GetInt {
    
    @Inject @PrimitiveSetting
    private int    int_value_2;
    
    public int getValue() { return int_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int3 implements GetInt {
    
    @Inject @PrimitiveSetting
    private int    int_value_3;
    
    public int getValue() { return int_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int4 implements GetInt {
    
    @Inject @PrimitiveSetting
    private int    int_value_4;
    
    public int getValue() { return int_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int5 implements GetInt {
    
    @Inject @PrimitiveSetting(defaultValue = "1")
    private int    int_value_5;
    
    public int getValue() { return int_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Int6 implements GetInt {
    
    @Inject @PrimitiveSetting(defaultValue = "-1")
    private int    int_value_6;
    
    public int getValue() { return int_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int7 implements GetInt {
    
    @Inject @PrimitiveSetting("int_customized_7")
    private int    int_value_7;
    
    public int getValue() { return int_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Int8 implements GetInt {
    
    @Inject @PrimitiveSetting
    private int    int_value_is_missing_and_no_default;
    
    public int getValue() { return int_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
