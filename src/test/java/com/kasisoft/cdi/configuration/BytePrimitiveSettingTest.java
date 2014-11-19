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
public class BytePrimitiveSettingTest extends AbstractEjbTest {

  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      // properties are set to: 1, -1
      { Byte1.class.getSimpleName(), (byte) 1 },
      { Byte2.class.getSimpleName(), (byte) -1  },
      // properties are set to: MAX, -MAX
      { Byte3.class.getSimpleName(), Byte.MAX_VALUE },
      { Byte4.class.getSimpleName(), Byte.MIN_VALUE },
      // properties aren't set. defaults are set to: 1, -1
      { Byte5.class.getSimpleName(), (byte) 1  },
      { Byte6.class.getSimpleName(), (byte) -1 },
      // properties with customized names
      { Byte7.class.getSimpleName(), (byte) 1 },
    };
  }
  
  @Test(dataProvider = "data")
  public void bytes( String name, byte expected ) throws Exception {
    GetByte getter = (GetByte) getContainer().getContext().lookup( "java:global/configuration/" + name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    getContainer().getContext().lookup( "java:global/configuration/Byte8" );
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
