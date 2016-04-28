package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping("unknown-error")
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
