package com.brianmearns.contracts;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * Contract annotation indicates that a method returns the object on which it
 * was invoked, or possibly {@code null}. Combine with {@code @Nullable} or
 * {@code NotNull} to indicate whether or not the method may return null.
 *
 * <p>
 * Since the type of the return value is necessarily the type of the host class,
 * a reflexive method is necessarily {@link Automorphic} as well.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.CLASS)
@Implies(Automorphic.class)
public @interface Reflexive {
}

