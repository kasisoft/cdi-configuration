package com.kasisoft.cdi.configuration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.kasisoft.cdi.weldex.*;

import lombok.experimental.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractConfigurationTest {

  protected <T> T lookup( Class<T> clazz ) throws Exception {
    T result = (T) CdiContext.component( clazz );
    assertThat( result, is( notNullValue() ) );
    return result;
  }
  
} /* ENDCLASS */
