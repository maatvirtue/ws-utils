package net.nlacombe.wsutils.restexception.api;

import javax.ws.rs.core.Response;
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
	String value() default "";

	Response.Status status() default Response.Status.INTERNAL_SERVER_ERROR;
}
