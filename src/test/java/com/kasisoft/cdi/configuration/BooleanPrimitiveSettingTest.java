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
public class BooleanPrimitiveSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "booleansData")
  public Object[][] booleansData() {
    return new Object[][] {
      
      // properties are set to: false, true
      { Boolean1.class, false },
      { Boolean2.class, true  },
      
      // properties are set to: no, yes
      { Boolean3.class, false },
      { Boolean4.class, true  },
      
      // properties aren't set. defaults are set to: false, true
      { Boolean5.class, false },
      { Boolean6.class, true  },
      
      // properties aren't set. defaults are set to: no, yes
      { Boolean7.class, false },
      { Boolean8.class, true  },
      
      // properties with customized names
      { Boolean9.class, false },
      { Boolean10.class, true  },
      
    };
  }
  
  @Test(dataProvider = "booleansData")
  public void booleans( Class<GetBoolean> clazz, boolean expected ) throws Exception {
    GetBoolean getter = this.<GetBoolean>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  /** @todo [19-Nov-2014:KASI]   Investigate whether it makes sense that an UndeclaredThrowableException turns up. */
  @Test(expectedExceptions = Exception.class)
  public void missingValue() throws Exception {
    this.<Boolean11>lookup( Boolean11.class );
  }
  
  private static interface GetBoolean {
    
    boolean getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Boolean1 implements GetBoolean {
    
    @Inject @PrimitiveSetting
    private boolean    boolean_value_1;
    
    public boolean getValue() { return boolean_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean2 implements GetBoolean {
    
    @Inject @PrimitiveSetting
    private boolean    boolean_value_2;
    
    public boolean getValue() { return boolean_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean3 implements GetBoolean {
    
    @Inject @PrimitiveSetting
    private boolean    boolean_value_3;
    
    public boolean getValue() { return boolean_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean4 implements GetBoolean {
    
    @Inject @PrimitiveSetting
    private boolean    boolean_value_4;
    
    public boolean getValue() { return boolean_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean5 implements GetBoolean {
    
    @Inject @PrimitiveSetting(defaultValue = "false")
    private boolean    boolean_value_5;
    
    public boolean getValue() { return boolean_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean6 implements GetBoolean {
    
    @Inject @PrimitiveSetting(defaultValue = "true")
    private boolean    boolean_value_6;
    
    public boolean getValue() { return boolean_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class Boolean7 implements GetBoolean {
    
    @Inject @PrimitiveSetting(defaultValue = "no")
    private boolean    boolean_value_7;
    
    public boolean getValue() { return boolean_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean8 implements GetBoolean {
    
    @Inject @PrimitiveSetting(defaultValue = "yes")
    private boolean    boolean_value_8;
    
    public boolean getValue() { return boolean_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean9 implements GetBoolean {
    
    @Inject @PrimitiveSetting("boolean_customized_9")
    private boolean    boolean_value_9;
    
    public boolean getValue() { return boolean_value_9; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean10 implements GetBoolean {
    
    @Inject @PrimitiveSetting("boolean_customized_10")
    private boolean    boolean_value_10;
    
    public boolean getValue() { return boolean_value_10; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Boolean11 implements GetBoolean {
    
    @Inject @PrimitiveSetting
    private boolean    boolean_value_is_missing_and_no_default;
    
    public boolean getValue() { return boolean_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
