package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "duplicate")
public class DuplicateRestException extends RestException
{
	private static int HTTP_STATUS = 409;

	public DuplicateRestException()
	{
		super(HTTP_STATUS);
	}

	public DuplicateRestException(String message)
	{
		super(HTTP_STATUS, message);
	}
}
