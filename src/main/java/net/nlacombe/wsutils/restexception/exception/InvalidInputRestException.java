package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "invalid-input", status = 400)
public class InvalidInputRestException extends RestException
{
	public InvalidInputRestException()
	{
	}

	public InvalidInputRestException(String message)
	{
		super(message);
	}
}
