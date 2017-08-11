package net.nlacombe.wsutils.restexception.api;

public class RestExceptionResponseBody
{
	private String errorCode;
	private String message;

	public RestExceptionResponseBody()
	{
	}

	public RestExceptionResponseBody(String errorCode, String message)
	{
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
