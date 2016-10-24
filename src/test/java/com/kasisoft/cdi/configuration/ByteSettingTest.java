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
public class ByteSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1, -1
      { ByteObj1.class, Byte.valueOf( (byte) 1 ) },
      { ByteObj2.class, Byte.valueOf( (byte) -1 ) },
      
      // properties are set to: MAX, -MAX
      { ByteObj3.class, Byte.valueOf( Byte.MAX_VALUE ) },
      { ByteObj4.class, Byte.valueOf( Byte.MIN_VALUE ) },
      
      // properties aren't set. defaults are set to: 1, -1
      { ByteObj5.class, Byte.valueOf( (byte) 1 ) },
      { ByteObj6.class, Byte.valueOf( (byte) -1 ) },
      
      // properties with customized names
      { ByteObj7.class, Byte.valueOf( (byte) 1 ) },
      
      // missing value
      { ByteObj8.class, null },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void bytes( Class<GetByte> clazz, Byte expected ) throws Exception {
    GetByte getter = this.<GetByte>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetByte {
    
    Byte getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class ByteObj1 implements GetByte {
    
    @Inject @Setting
    private Byte    byte_value_1;
    
    public Byte getValue() { return byte_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj2 implements GetByte {
    
    @Inject @Setting
    private Byte    byte_value_2;
    
    public Byte getValue() { return byte_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj3 implements GetByte {
    
    @Inject @Setting
    private Byte    byte_value_3;
    
    public Byte getValue() { return byte_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj4 implements GetByte {
    
    @Inject @Setting
    private Byte    byte_value_4;
    
    public Byte getValue() { return byte_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj5 implements GetByte {
    
    @Inject @Setting(defaultValue = "1")
    private Byte    byte_value_6;
    
    public Byte getValue() { return byte_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class ByteObj6 implements GetByte {
    
    @Inject @Setting(defaultValue = "-1")
    private Byte    byte_value_7;
    
    public Byte getValue() { return byte_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj7 implements GetByte {
    
    @Inject @Setting("byte_customized_7")
    private Byte    byte_value_7;
    
    public Byte getValue() { return byte_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class ByteObj8 implements GetByte {
    
    @Inject @Setting(required = false)
    private Byte    byte_value_is_missing_and_no_default;
    
    public Byte getValue() { return byte_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
