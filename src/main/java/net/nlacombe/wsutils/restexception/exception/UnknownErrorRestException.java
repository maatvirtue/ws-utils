package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "unknown-error", status = 500)
public class UnknownErrorRestException extends RestException
{
	public UnknownErrorRestException()
	{
	}

	public UnknownErrorRestException(String message)
	{
		super(message);
	}

	public UnknownErrorRestException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}
