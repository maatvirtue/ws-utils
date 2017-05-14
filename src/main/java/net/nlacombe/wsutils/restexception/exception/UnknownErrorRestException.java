package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

import javax.ws.rs.core.Response;

@RestExceptionMapping(value = "unknown-error", status = Response.Status.INTERNAL_SERVER_ERROR)
public class UnknownErrorRestException extends RestException
{
	public UnknownErrorRestException()
	{
		//
	}

	public UnknownErrorRestException(String message)
	{
		super(message);
	}
}
