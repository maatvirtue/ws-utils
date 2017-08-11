package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "not-found", status = 404)
public class NotFoundRestException extends RestException
{
	public NotFoundRestException()
	{
	}

	public NotFoundRestException(String message)
	{
		super(message);
	}
}
