package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "not-found")
public class NotFoundRestException extends RestException
{
	private static int HTTP_STATUS = 404;

	public NotFoundRestException()
	{
		super(HTTP_STATUS);
	}

	public NotFoundRestException(String message)
	{
		super(HTTP_STATUS, message);
	}
}
