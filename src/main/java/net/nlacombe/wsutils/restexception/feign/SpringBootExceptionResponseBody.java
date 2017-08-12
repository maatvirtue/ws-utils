package net.nlacombe.wsutils.restexception.feign;

import java.time.Instant;

public class SpringBootExceptionResponseBody
{
	private Instant timestamp;
	private String exception;
	private Integer status;
	private String error;
	private String path;
	private String message;

	public Instant getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Instant timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getException()
	{
		return exception;
	}

	public void setException(String exception)
	{
		this.exception = exception;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
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
