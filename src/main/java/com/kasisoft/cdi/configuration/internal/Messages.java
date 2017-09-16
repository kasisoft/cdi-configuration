package com.kasisoft.cdi.configuration.internal;

import com.kasisoft.libs.common.i18n.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class Messages {

  @I18N("The basic resource '%s' is not a file.")
  public static I18NFormatter         base_is_not_a_dir;

  @I18N("Could not calculate canonical path of '%s'. Cause: %s")
  public static I18NFormatter         canonical_failure;
  
  @I18N("Could not load properties. Cause: %s")
  public static I18NFormatter         could_not_load_properties;
  
  @I18N("The value '%s' for the key '%s' could not be converted into the type '%s'. Cause: %s.")
  public static I18NFormatter         invalid_type;
  
  @I18N("Failed to create directory '%s'.")
  public static I18NFormatter         makedir_failure;
  
  @I18N("Could not find any value for the required field '%s'.")
  public static I18NFormatter         missing_required_value;
  
  @I18N("Property: '%s' -> '%s'")
  public static I18NFormatter         property;
  
  static {
    I18NSupport.initialize( Messages.class );
  }
  
} /* ENDCLASS */
