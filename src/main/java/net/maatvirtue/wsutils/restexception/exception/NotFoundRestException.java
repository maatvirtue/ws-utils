package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

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
