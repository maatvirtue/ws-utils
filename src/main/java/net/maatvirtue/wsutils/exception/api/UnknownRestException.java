package net.maatvirtue.wsutils.exception.api;

import javax.ws.rs.core.Response;

public class UnknownRestException extends RestException
{
	public UnknownRestException(Response.Status httpStatus, String code, String message)
	{
		super(httpStatus, code, message);
	}

	@Override
	public String getMessage()
	{
		return "Unknown rest exception code \"" + getCode() + "\" with message \"" + super.getMessage() + "\"";
	}
}
