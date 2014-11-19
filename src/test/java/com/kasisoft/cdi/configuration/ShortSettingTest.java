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
public class ShortSettingTest extends AbstractEjbTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1, -1
      { ShortObj1.class.getSimpleName(), Short.valueOf( (short) 1 ) },
      { ShortObj2.class.getSimpleName(), Short.valueOf( (short) -1 ) },
      // properties are set to: MAX, -MAX
      { ShortObj3.class.getSimpleName(), Short.valueOf( Short.MAX_VALUE ) },
      { ShortObj4.class.getSimpleName(), Short.valueOf( Short.MIN_VALUE ) },
      // properties aren't set. defaults are set to: 1, -1
      { ShortObj5.class.getSimpleName(), Short.valueOf( (short) 1 ) },
      { ShortObj6.class.getSimpleName(), Short.valueOf( (short) -1 ) },
      // properties with customized names
      { ShortObj7.class.getSimpleName(), Short.valueOf( (short) 1 ) },
      // missing value
      { Short8.class.getSimpleName(), null },
    };
  }
  
  @Test(dataProvider = "data")
  public void shorts( String name, Short expected ) throws Exception {
    GetShort getter = (GetShort) getContainer().getContext().lookup( "java:global/configuration/" + name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetShort {
    
    Short getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class ShortObj1 implements GetShort {
    
    @Inject @Setting
    private Short    short_value_1;
    
    public Short getValue() { return short_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ShortObj2 implements GetShort {
    
    @Inject @Setting
    private Short    short_value_2;
    
    public Short getValue() { return short_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ShortObj3 implements GetShort {
    
    @Inject @Setting
    private Short    short_value_3;
    
    public Short getValue() { return short_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ShortObj4 implements GetShort {
    
    @Inject @Setting
    private Short    short_value_4;
    
    public Short getValue() { return short_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ShortObj5 implements GetShort {
    
    @Inject @Setting(defaultValue = "1")
    private Short    short_value_5;
    
    public Short getValue() { return short_value_5; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class ShortObj6 implements GetShort {
    
    @Inject @Setting(defaultValue = "-1")
    private Short    short_value_6;
    
    public Short getValue() { return short_value_6; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ShortObj7 implements GetShort {
    
    @Inject @Setting("short_customized_7")
    private Short    short_value_7;
    
    public Short getValue() { return short_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Short8 implements GetShort {
    
    @Inject @Setting(required = false)
    private Short    short_value_is_missing_and_no_default;
    
    public Short getValue() { return short_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
