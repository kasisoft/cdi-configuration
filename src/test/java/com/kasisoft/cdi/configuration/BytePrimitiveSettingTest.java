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
public class BytePrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      
      // properties are set to: 1, -1
      { Byte1.class, (byte) 1 },
      { Byte2.class, (byte) -1  },
      
      // properties are set to: MAX, -MAX
      { Byte3.class, Byte.MAX_VALUE },
      { Byte4.class, Byte.MIN_VALUE },
      
      // properties aren't set. defaults are set to: 1, -1
      { Byte5.class, (byte) 1  },
      { Byte6.class, (byte) -1 },
      
      // properties with customized names
      { Byte7.class, (byte) 1 },
      
    };
  }
  
  @Test(dataProvider = "data")
  public void bytes( Class<GetByte> clazz, byte expected ) throws Exception {
    GetByte getter = this.<GetByte>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<Byte8>lookup( Byte8.class );
  }
  
  private static interface GetByte {
    
    byte getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Byte1 implements GetByte {
    
    @Inject @PrimitiveSetting
    private byte    byte_value_1;
    
    public byte getValue() { return byte_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte2 implements GetByte {
    
    @Inject @PrimitiveSetting
    private byte    byte_value_2;
    
    public byte getValue() { return byte_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte3 implements GetByte {
    
    @Inject @PrimitiveSetting
    private byte    byte_value_3;
    
    public byte getValue() { return byte_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte4 implements GetByte {
    
    @Inject @PrimitiveSetting
    private byte    byte_value_4;
    
    public byte getValue() { return byte_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte5 implements GetByte {
    
    @Inject @PrimitiveSetting(defaultValue = "1")
    private byte    byte_value_6;
    
    public byte getValue() { return byte_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Byte6 implements GetByte {
    
    @Inject @PrimitiveSetting(defaultValue = "-1")
    private byte    byte_value_7;
    
    public byte getValue() { return byte_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte7 implements GetByte {
    
    @Inject @PrimitiveSetting("byte_customized_7")
    private byte    byte_value_7;
    
    public byte getValue() { return byte_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Byte8 implements GetByte {
    
    @Inject @PrimitiveSetting
    private byte    byte_value_is_missing_and_no_default;
    
    public byte getValue() { return byte_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
