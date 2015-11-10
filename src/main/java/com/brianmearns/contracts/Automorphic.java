package com.brianmearns.contracts;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * Contract annotation indicates that a method returns an object whose type is
 * the same as the type of the object on which it is invoked, which could include returning
 * {@code null}. For instance, if class {@code T} has a method {@code f(...)} that
 * has a return type {@code T}, then {@code f(...)} is <em>automorphic</em>.
 *
 * <p>
 * Note that this is a special case of {@link Endomorphic}.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.CLASS)
@Implies(Endomorphic.class)
public @interface Automorphic {
}

