package net.maatvirtue.wsutils.restexception.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping("not-found")
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
