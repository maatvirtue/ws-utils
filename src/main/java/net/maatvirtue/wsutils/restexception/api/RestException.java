package net.maatvirtue.wsutils.restexception.api;

import javax.ws.rs.core.Response;

public abstract class RestException extends RuntimeException
{
	private Response.Status httpStatus;
	private String code;
	private String message;

	public RestException()
	{
		this(null);
	}

	public RestException(String message)
	{
		this(null, message);
	}

	public RestException(Response.Status httpStatus, String message)
	{
		this(httpStatus, null, message);
	}

	public RestException(Response.Status httpStatus, String code, String message)
	{
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public RestExceptionResponseBody getRestExceptionResponseBody()
	{
		return new RestExceptionResponseBody(code, message);
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCode()
	{
		return code;
	}

	public final void setCode(String code)
	{
		this.code = code;
	}

	public Response.Status getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(Response.Status httpStatus)
	{
		this.httpStatus = httpStatus;
	}
}
