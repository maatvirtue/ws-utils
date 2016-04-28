package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

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
