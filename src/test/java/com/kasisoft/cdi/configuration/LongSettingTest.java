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
public class LongSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1, -1
      { LongObj1.class, Long.valueOf(1L) },
      { LongObj2.class, Long.valueOf(-1L) },
      
      // properties are set to: MAX, -MAX
      { LongObj3.class, Long.valueOf( Long.MAX_VALUE ) },
      { LongObj4.class, Long.valueOf( Long.MIN_VALUE ) },
      
      // properties aren't set. defaults are set to: 1, -1
      { LongObj5.class, Long.valueOf(1L) },
      { LongObj6.class, Long.valueOf(-1L) },
      
      // properties with customized names
      { LongObj7.class, Long.valueOf(1L) },
      
      // missing value
      { LongObj8.class, null },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void longs( Class<GetLong> clazz, Long expected ) throws Exception {
    GetLong getter = this.<GetLong>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetLong {
    
    Long getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class LongObj1 implements GetLong {
    
    @Inject @Setting
    private Long    long_value_1;
    
    public Long getValue() { return long_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj2 implements GetLong {
    
    @Inject @Setting
    private Long    long_value_2;
    
    public Long getValue() { return long_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj3 implements GetLong {
    
    @Inject @Setting
    private Long    long_value_3;
    
    public Long getValue() { return long_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj4 implements GetLong {
    
    @Inject @Setting
    private Long    long_value_4;
    
    public Long getValue() { return long_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj5 implements GetLong {
    
    @Inject @Setting(defaultValue = "1")
    private Long    long_value_5;
    
    public Long getValue() { return long_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class LongObj6 implements GetLong {
    
    @Inject @Setting(defaultValue = "-1")
    private Long    long_value_6;
    
    public Long getValue() { return long_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj7 implements GetLong {
    
    @Inject @Setting("long_customized_7")
    private Long    long_value_7;
    
    public Long getValue() { return long_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class LongObj8 implements GetLong {
    
    @Inject @Setting(required = false)
    private Long    long_value_is_missing_and_no_default;
    
    public Long getValue() { return long_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
