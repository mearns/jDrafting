package com.brianmearns.contracts;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * Contract annotation indicates that a method returns an object which is asignable
 * from the type of the object on which it is invoked, which could include returning
 * {@code null}. For instance, if class {@code T} has a method {@code f(...)} that
 * has a return type {@code S}, then {@code f(...)} is <em>endomorphic</em> if and
 * only if {@code S} is equal to or a subclass of {@code T}.
 *
 * <p>
 * This is useful for interfaces to enforce that a method returns an object whose
 * type is defined by the implementing class, and not simply the class which originally
 * defined the method.
 *
 * <p>
 * Note that this specifies not just the runtime type of the return value, but also the
 * declared return type of the method.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.CLASS)
public @interface Endomorphic {
}
