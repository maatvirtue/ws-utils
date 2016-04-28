package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

import javax.ws.rs.core.Response;

@RestExceptionMapping(value = "access-denied", status = Response.Status.FORBIDDEN)
public class AccessDeniedRestException extends RestException
{
	public AccessDeniedRestException()
	{
		//
	}

	public AccessDeniedRestException(String message)
	{
		super(message);
	}
}
