package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "duplicate", status = 409)
public class DuplicateRestException extends RestException
{
	public DuplicateRestException()
	{
	}

	public DuplicateRestException(String message)
	{
		super(message);
	}
}
