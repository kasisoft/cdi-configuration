package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.libs.common.constants.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import java.io.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@ManagedBean
public class DirSettingTest extends AbstractConfigurationTest {

  private static File   basedir;
  
  @BeforeSuite
  public static void prepare() {
    basedir = CommonProperty.TempDir.getDefaultValue();
  }
  
  
  @DataProvider(name = "data")
  public Object[][] data() {
    return new Object[][] {
      { Dir1.class.getSimpleName(), basedir },
      { Dir2.class.getSimpleName(), new File( basedir, "bibo" ) },
    };
  }
  
  @Test(dataProvider = "data")
  public void dirs( String name, File expected ) throws Exception {
    GetDir getter = this.<GetDir>lookup( name );
    assertThat( getter.getValue(), is( expected ) );
  }
  
  private static interface GetDir {
    
    File getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Dir1 implements GetDir {
    
    @Inject @DirSetting
    private File    dir_value_1;
    
    public File getValue() { return dir_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir2 implements GetDir {
    
    @Inject @DirSetting(extension = "bibo")
    private File    dir_value_2;
    
    public File getValue() { return dir_value_2; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
