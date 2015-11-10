package com.brianmearns.contracts;

import java.lang.annotation.*;

/**
 * Poor man's inheritance for annotations. Indicates that when the annotated annotation is present, processors
 * should also consider that all of the other annotation classes are present.
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Inherited
@Retention(RetentionPolicy.CLASS)
public @interface Implies {
    Class[] value();
}
