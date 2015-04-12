package com.emc.ga4gh.spring.aop.logger;

import java.lang.annotation.*;

/**
 * Created by liza on 12.04.15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Log {
}
