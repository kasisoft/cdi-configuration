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
public class BooleanSettingTest extends AbstractConfigurationTest {

  @DataProvider(name = "booleansData")
  public Object[][] booleansData() {
    return new Object[][] {
      // properties are set to: false, true
      { BooleanObj1.class.getSimpleName(), Boolean.FALSE },
      { BooleanObj2.class.getSimpleName(), Boolean.TRUE  },
      // properties are set to: no, yes
      { BooleanObj3.class.getSimpleName(), Boolean.FALSE },
      { BooleanObj4.class.getSimpleName(), Boolean.TRUE  },
      // properties aren't set. defaults are set to: false, true
      { BooleanObj5.class.getSimpleName(), Boolean.FALSE },
      { BooleanObj6.class.getSimpleName(), Boolean.TRUE  },
      // properties aren't set. defaults are set to: no, yes
      { BooleanObj7.class.getSimpleName(), Boolean.FALSE },
      { BooleanObj8.class.getSimpleName(), Boolean.TRUE  },
      // properties with customized names
      { BooleanObj9.class.getSimpleName(), Boolean.FALSE },
      { BooleanObj10.class.getSimpleName(), Boolean.TRUE  },
      // missing value
      { BooleanObj11.class.getSimpleName(), null },
    };
  }
  
  @Test(dataProvider = "booleansData")
  public void booleans( String name, Boolean expected ) throws Exception {
    GetBoolean getter = this.<GetBoolean>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetBoolean {
    
    Boolean getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class BooleanObj1 implements GetBoolean {
    
    @Inject @Setting
    private Boolean    boolean_value_1;
    
    public Boolean getValue() { return boolean_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj2 implements GetBoolean {
    
    @Inject @Setting
    private Boolean    boolean_value_2;
    
    public Boolean getValue() { return boolean_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj3 implements GetBoolean {
    
    @Inject @Setting
    private Boolean    boolean_value_3;
    
    public Boolean getValue() { return boolean_value_3; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj4 implements GetBoolean {
    
    @Inject @Setting
    private Boolean    boolean_value_4;
    
    public Boolean getValue() { return boolean_value_4; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj5 implements GetBoolean {
    
    @Inject @Setting(defaultValue = "false")
    private Boolean    boolean_value_5;
    
    public Boolean getValue() { return boolean_value_5; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj6 implements GetBoolean {
    
    @Inject @Setting(defaultValue = "true")
    private Boolean    boolean_value_6;
    
    public Boolean getValue() { return boolean_value_6; }
    
  } /* ENDCLASS */
  
  @Named @ManagedBean @ToString
  public static class BooleanObj7 implements GetBoolean {
    
    @Inject @Setting(defaultValue = "no")
    private Boolean    boolean_value_7;
    
    public Boolean getValue() { return boolean_value_7; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj8 implements GetBoolean {
    
    @Inject @Setting(defaultValue = "yes")
    private Boolean    boolean_value_8;
    
    public Boolean getValue() { return boolean_value_8; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj9 implements GetBoolean {
    
    @Inject @Setting("boolean_customized_9")
    private Boolean    boolean_value_9;
    
    public Boolean getValue() { return boolean_value_9; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj10 implements GetBoolean {
    
    @Inject @Setting("boolean_customized_10")
    private Boolean    boolean_value_10;
    
    public Boolean getValue() { return boolean_value_10; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class BooleanObj11 implements GetBoolean {
    
    @Inject @Setting(required = false)
    private Boolean    boolean_value_is_missing_and_no_default;
    
    public Boolean getValue() { return boolean_value_is_missing_and_no_default; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
