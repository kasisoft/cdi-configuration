package com.kasisoft.cdi.configuration;

import javax.enterprise.util.*;
import javax.inject.*;

import java.lang.annotation.*;

/**
 * Marker for configurable properties. <code>value()</code> as well as <code>defaultValue()</code> will be considered as
 * <code>null</code> values in case they are empty.
 *
 * @ks.note [27-Dec-2013:KASI]   This annotation is similar to {@link Setting}. It's only needed as the returntype
 *                               cannot be used to distinguish production methods.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface PrimitiveSetting {

  @Nonbinding
  String value() default "";

  @Nonbinding
  String defaultValue() default "";

} /* ENDCLASS */