package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

import javax.ws.rs.core.Response;

@RestExceptionMapping(value = "not-found", status = Response.Status.NOT_FOUND)
public class NotFoundRestException extends RestException
{
	public NotFoundRestException()
	{
		//
	}

	public NotFoundRestException(String message)
	{
		super(message);
	}
}
