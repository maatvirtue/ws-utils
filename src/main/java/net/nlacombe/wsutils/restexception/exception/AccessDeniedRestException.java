package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "access-denied", status = 403)
public class AccessDeniedRestException extends RestException
{
	public AccessDeniedRestException()
	{
	}

	public AccessDeniedRestException(String message)
	{
		super(message);
	}
}
