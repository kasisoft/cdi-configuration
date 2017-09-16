package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.libs.common.constants.*;

import org.testng.annotations.*;

import javax.annotation.*;
import javax.inject.*;

import java.net.*;

import java.io.*;

import java.nio.file.*;

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
  
  
  @DataProvider(name = "dirs")
  public Object[][] dirs() {
    return new Object[][] {
      { Dir1.class  , basedir },
      { Dir2.class  , new File( basedir, "bibo" ) },
    };
  }

  @Test(dataProvider = "dirs")
  public void dirs( Class<GetDir<File>> clazz, File expected ) throws Exception {
    GetDir<File> getter = this.<GetDir<File>>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }

  @DataProvider(name = "pathes")
  public Object[][] pathes() {
    return new Object[][] {
      { Dir3.class  , basedir.toPath() },
      { Dir4.class  , new File( basedir, "bibo" ).toPath() },
    };
  }
  
  @Test(dataProvider = "pathes")
  public void pathes( Class<GetDir<Path>> clazz, Path expected ) throws Exception {
    GetDir<Path> getter = this.<GetDir<Path>>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }

  @DataProvider(name = "uris")
  public Object[][] uris() {
    return new Object[][] {
      { Dir5.class  , basedir.toURI() },
      { Dir6.class  , new File( basedir, "bibo" ).toURI() },
    };
  }
  
  @Test(dataProvider = "uris")
  public void uris( Class<GetDir<URI>> clazz, URI expected ) throws Exception {
    GetDir<URI> getter = this.<GetDir<URI>>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }

  @DataProvider(name = "strings")
  public Object[][] strings() throws Exception {
    return new Object[][] {
      { Dir7.class  , basedir.getCanonicalPath() },
      { Dir8.class  , new File( basedir, "bibo" ).getCanonicalPath() },
    };
  }
  
  @Test(dataProvider = "strings")
  public void strings( Class<GetDir<String>> clazz, String expected ) throws Exception {
    GetDir<String> getter = this.<GetDir<String>>lookup( clazz );
    assertThat( getter.getValue(), is( expected ) );
  }

  private static interface GetDir<T> {
    
    T getValue();
    
  } /* ENDINTERFACE */
  
  
  @Named @ManagedBean @ToString
  public static class Dir1 implements GetDir<File> {
    
    @Inject @DirSetting
    private File    dir_value_1;
    
    public File getValue() { return dir_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir2 implements GetDir<File> {
    
    @Inject @DirSetting(extension = "bibo")
    private File    dir_value_2;
    
    public File getValue() { return dir_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir3 implements GetDir<Path> {
    
    @Inject @DirSetting
    private Path    dir_value_1;
    
    public Path getValue() { return dir_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir4 implements GetDir<Path> {
    
    @Inject @DirSetting(extension = "bibo")
    private Path    dir_value_2;
    
    public Path getValue() { return dir_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir5 implements GetDir<URI> {
    
    @Inject @DirSetting
    private URI    dir_value_1;
    
    public URI getValue() { return dir_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir6 implements GetDir<URI> {
    
    @Inject @DirSetting(extension = "bibo")
    private URI    dir_value_2;
    
    public URI getValue() { return dir_value_2; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir7 implements GetDir<String> {
    
    @Inject @DirSetting
    private String    dir_value_1;
    
    public String getValue() { return dir_value_1; }
    
  } /* ENDCLASS */

  @Named @ManagedBean @ToString
  public static class Dir8 implements GetDir<String> {
    
    @Inject @DirSetting(extension = "bibo")
    private String    dir_value_2;
    
    public String getValue() { return dir_value_2; }
    
  } /* ENDCLASS */

} /* ENDCLASS */
