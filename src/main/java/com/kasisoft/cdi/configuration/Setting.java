package com.kasisoft.cdi.configuration;

import javax.enterprise.util.*;
import javax.inject.*;

import java.lang.annotation.*;

/**
 * Marker for configurable properties.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Setting {

  @Nonbinding
  String value() default "";

  @Nonbinding
  String defaultValue() default "";

  @Nonbinding
  boolean required() default true;

} /* ENDCLASS */