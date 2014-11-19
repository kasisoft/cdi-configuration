package com.kasisoft.cdi.configuration;

import javax.enterprise.util.*;
import javax.inject.*;

import java.lang.annotation.*;

/**
 * Marker for configurable properties. <code>value()</code> as well as <code>defaultValue()</code> will be considered as
 * <code>null</code> values in case they are empty.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface DirSetting {

  @Nonbinding
  String value() default "";

  @Nonbinding
  String defaultValue() default "";

  @Nonbinding
  String extension() default "";
  
  @Nonbinding
  boolean required() default true;

} /* ENDCLASS */