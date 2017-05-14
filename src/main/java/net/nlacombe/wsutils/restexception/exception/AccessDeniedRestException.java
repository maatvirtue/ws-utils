package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

import javax.ws.rs.core.Response;

@RestExceptionMapping(value = "access-denied", status = Response.Status.FORBIDDEN)
public class AccessDeniedRestException extends RestException
{
	public AccessDeniedRestException()
	{
		//
	}

	public AccessDeniedRestException(String message)
	{
		super(message);
	}
}
