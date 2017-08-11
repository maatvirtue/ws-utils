package net.nlacombe.wsutils.restexception.api;

import net.nlacombe.wsutils.restexception.constants.Constants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestExceptionMapping
{
	/**
	 * Unique code for the exception.
	 */
	String errorCode();

	int status() default Constants.DEFAULT_HTTP_STATUS;
}
