//package com.kasisoft.cdi.configuration;
//
//import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.Matchers.*;
//
//import org.testng.annotations.*;
//
//import javax.annotation.*;
//import javax.inject.*;
//
//import lombok.*;
//
///**
// * @author daniel.kasmeroglu@kasisoft.net
// */
//@ManagedBean
//public class CharacterPrimitiveSettingTest extends AbstractConfigurationTest {
//
//  @DataProvider(name = "data")
//  public Object[][] data() {
//    return new Object[][] {
//      
//      // properties are set to: 'a'
//      { Char1.class, 'a' },
//      
//      // properties aren't set. defaults are set to: 'b'
//      { Char2.class, 'b'  },
//      
//      // properties with customized names
//      { Char3.class, 'c' },
//      
//    };
//  }
//  
//  @Test(dataProvider = "data", enabled = false)
//  public void chars( Class<GetChar> clazz, char expected ) throws Exception {
//    GetChar getter = (GetChar) lookup( clazz );
//    assertThat( getter.getValue(), is( expected ) );
//  }
//  
//  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
//  @Test(expectedExceptions = Exception.class)
//  public void missingValue() throws Exception {
//    lookup( Char4.class );
//  }
//  
//  private static interface GetChar {
//    
//    char getValue();
//    
//  } /* ENDINTERFACE */
//  
//  
//  @Named @ManagedBean @ToString
//  public static class Char1 implements GetChar {
//    
//    @Inject @PrimitiveSetting
//    private char    char_value_1;
//    
//    public char getValue() { return char_value_1; }
//    
//  } /* ENDCLASS */
//
//  @Named @ManagedBean @ToString
//  public static class Char2 implements GetChar {
//    
//    @Inject @PrimitiveSetting(defaultValue = "b")
//    private char    char_value_2;
//    
//    public char getValue() { return char_value_2; }
//    
//  } /* ENDCLASS */
//  
//  @Named @ManagedBean @ToString
//  public static class Char3 implements GetChar {
//    
//    @Inject @PrimitiveSetting("char_customized_3")
//    private char    char_value_3;
//    
//    public char getValue() { return char_value_3; }
//    
//  } /* ENDCLASS */
//
//  @Named @ManagedBean @ToString
//  public static class Char4 implements GetChar {
//    
//    @Inject @PrimitiveSetting
//    private char    char_value_is_missing_and_no_default;
//    
//    public char getValue() { return char_value_is_missing_and_no_default; }
//    
//  } /* ENDCLASS */
//
//} /* ENDCLASS */
