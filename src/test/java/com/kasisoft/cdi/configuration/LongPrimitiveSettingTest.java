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
public class LongPrimitiveSettingTest extends AbstractEjbTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1, -1
      { Long1.class.getSimpleName(), 1L },
      { Long2.class.getSimpleName(), -1L  },
      // properties are set to: MAX, -MAX
      { Long3.class.getSimpleName(), Long.MAX_VALUE },
      { Long4.class.getSimpleName(), Long.MIN_VALUE },
      // properties aren't set. defaults are set to: 1, -1
      { Long5.class.getSimpleName(), 1L  },
      { Long6.class.getSimpleName(), -1L },
      // properties with customized names
      { Long7.class.getSimpleName(), 1L },
    };
  }
  
  @Test(dataProvider = "data")
  public void longs( String name, long expected ) throws Exception {
    GetLong getter = (GetLong) getContainer().getContext().lookup( "java:global/configuration/" + name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    getContainer().getContext().lookup( "java:global/configuration/Long8" );
  }
  
  private static interface GetLong {
    
    long getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Long1 implements GetLong {
    
    @Inject @PrimitiveSetting
    private long    long_value_1;
    
    public long getValue() { return long_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long2 implements GetLong {
    
    @Inject @PrimitiveSetting
    private long    long_value_2;
    
    public long getValue() { return long_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long3 implements GetLong {
    
    @Inject @PrimitiveSetting
    private long    long_value_3;
    
    public long getValue() { return long_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long4 implements GetLong {
    
    @Inject @PrimitiveSetting
    private long    long_value_4;
    
    public long getValue() { return long_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long5 implements GetLong {
    
    @Inject @PrimitiveSetting(defaultValue = "1")
    private long    long_value_5;
    
    public long getValue() { return long_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Long6 implements GetLong {
    
    @Inject @PrimitiveSetting(defaultValue = "-1")
    private long    long_value_6;
    
    public long getValue() { return long_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long7 implements GetLong {
    
    @Inject @PrimitiveSetting("long_customized_7")
    private long    long_value_7;
    
    public long getValue() { return long_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Long8 implements GetLong {
    
    @Inject @PrimitiveSetting
    private long    long_value_is_missing_and_no_default;
    
    public long getValue() { return long_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
