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
public class ShortPrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1, -1
      { Short1.class.getSimpleName(), (short) 1 },
      { Short2.class.getSimpleName(), (short) -1  },
      // properties are set to: MAX, -MAX
      { Short3.class.getSimpleName(), Short.MAX_VALUE },
      { Short4.class.getSimpleName(), Short.MIN_VALUE },
      // properties aren't set. defaults are set to: 1, -1
      { Short5.class.getSimpleName(), (short) 1  },
      { Short6.class.getSimpleName(), (short) -1 },
      // properties with customized names
      { Short7.class.getSimpleName(), (short) 1 },
    };
  }
  
  @Test(dataProvider = "data")
  public void shorts( String name, short expected ) throws Exception {
    GetShort getter = this.<GetShort>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<GetShort>lookup( Short8.class.getSimpleName() );
  }
  
  private static interface GetShort {
    
    short getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Short1 implements GetShort {
    
    @Inject @PrimitiveSetting
    private short    short_value_1;
    
    public short getValue() { return short_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short2 implements GetShort {
    
    @Inject @PrimitiveSetting
    private short    short_value_2;
    
    public short getValue() { return short_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short3 implements GetShort {
    
    @Inject @PrimitiveSetting
    private short    short_value_3;
    
    public short getValue() { return short_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short4 implements GetShort {
    
    @Inject @PrimitiveSetting
    private short    short_value_4;
    
    public short getValue() { return short_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short5 implements GetShort {
    
    @Inject @PrimitiveSetting(defaultValue = "1")
    private short    short_value_5;
    
    public short getValue() { return short_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Short6 implements GetShort {
    
    @Inject @PrimitiveSetting(defaultValue = "-1")
    private short    short_value_6;
    
    public short getValue() { return short_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short7 implements GetShort {
    
    @Inject @PrimitiveSetting("short_customized_7")
    private short    short_value_7;
    
    public short getValue() { return short_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short8 implements GetShort {
    
    @Inject @PrimitiveSetting
    private short    short_value_is_missing_and_no_default;
    
    public short getValue() { return short_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
