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
public class IntegerSettingTest extends AbstractEjbTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1, -1
      { IntObj1.class.getSimpleName(), Integer.valueOf(1) },
      { IntObj2.class.getSimpleName(), Integer.valueOf(-1)  },
      // properties are set to: MAX, -MAX
      { IntObj3.class.getSimpleName(), Integer.valueOf( Integer.MAX_VALUE ) },
      { IntObj4.class.getSimpleName(), Integer.valueOf( Integer.MIN_VALUE ) },
      // properties aren't set. defaults are set to: 1, -1
      { IntObj5.class.getSimpleName(), Integer.valueOf(1) },
      { IntObj6.class.getSimpleName(), Integer.valueOf(-1) },
      // properties with customized names
      { IntObj7.class.getSimpleName(), Integer.valueOf(1) },
      // missing value
      { IntObj8.class.getSimpleName(), null },
    };
  }
  
  @Test(dataProvider = "data")
  public void ints( String name, Integer expected ) throws Exception {
    GetInt getter = (GetInt) getContainer().getContext().lookup( "java:global/configuration/" + name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetInt {
    
    Integer getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class IntObj1 implements GetInt {
    
    @Inject @Setting
    private Integer    int_value_1;
    
    public Integer getValue() { return int_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj2 implements GetInt {
    
    @Inject @Setting
    private Integer    int_value_2;
    
    public Integer getValue() { return int_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj3 implements GetInt {
    
    @Inject @Setting
    private Integer    int_value_3;
    
    public Integer getValue() { return int_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj4 implements GetInt {
    
    @Inject @Setting
    private Integer    int_value_4;
    
    public Integer getValue() { return int_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj5 implements GetInt {
    
    @Inject @Setting(defaultValue = "1")
    private Integer    int_value_5;
    
    public Integer getValue() { return int_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class IntObj6 implements GetInt {
    
    @Inject @Setting(defaultValue = "-1")
    private Integer    int_value_6;
    
    public Integer getValue() { return int_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj7 implements GetInt {
    
    @Inject @Setting("int_customized_7")
    private Integer    int_value_7;
    
    public Integer getValue() { return int_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class IntObj8 implements GetInt {
    
    @Inject @Setting(required = false)
    private Integer    int_value_is_missing_and_no_default;
    
    public Integer getValue() { return int_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
