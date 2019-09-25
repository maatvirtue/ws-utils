package net.nlacombe.wsutils.restexception.api;

import net.nlacombe.wsutils.restexception.constants.Constants;

public abstract class RestException extends RuntimeException
{
	private int httpStatus;
	private String errorCode;
	private String message;

	public RestException()
	{
		this(Constants.DEFAULT_HTTP_STATUS, null, null, null);
	}

	public RestException(Throwable throwable)
	{
		this(Constants.DEFAULT_HTTP_STATUS, null, null, throwable);
	}

	public RestException(int httpStatus)
	{
		this(httpStatus, null, null, null);
	}

	public RestException(String message)
	{
		this(Constants.DEFAULT_HTTP_STATUS, null, message, null);
	}

	public RestException(String message, Throwable throwable)
	{
		this(Constants.DEFAULT_HTTP_STATUS, null, message, throwable);
	}

	public RestException(int httpStatus, String message)
	{
		this(httpStatus, null, message, null);
	}

	public RestException(int httpStatus, String errorCode, String message)
	{
		this(httpStatus, errorCode, message, null);
	}

	public RestException(int httpStatus, String errorCode, String message, Throwable throwable)
	{
		super(message, throwable);

		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	public RestExceptionResponseBody getRestExceptionResponseBody()
	{
		return new RestExceptionResponseBody(errorCode, getMessage());
	}

	public int getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
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
}
