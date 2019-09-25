package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "invalid-input")
public class InvalidInputRestException extends RestException
{
	private static int HTTP_STATUS = 400;

	public InvalidInputRestException()
	{
		super(HTTP_STATUS);
	}

	public InvalidInputRestException(String message)
	{
		super(HTTP_STATUS, message);
	}
}
