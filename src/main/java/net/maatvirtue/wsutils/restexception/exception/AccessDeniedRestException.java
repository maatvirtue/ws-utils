package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping("access-denied")
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
