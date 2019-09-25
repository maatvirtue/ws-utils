package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "access-denied")
public class AccessDeniedRestException extends RestException
{
	private static int HTTP_STATUS = 403;

	public AccessDeniedRestException()
	{
		super(HTTP_STATUS);
	}

	public AccessDeniedRestException(String message)
	{
		super(HTTP_STATUS, message);
	}
}
