package net.nlacombe.wsutils.restexception.exception;

import net.nlacombe.wsutils.restexception.feign.SpringBootExceptionResponseBody;

import java.time.Instant;

public class SpringBootDefaultException extends RuntimeException
{
	private Instant timestamp;
	private String exceptionClassText;
	private Class<?> exception;
	private Integer status;
	private String error;
	private String path;

	public SpringBootDefaultException(SpringBootExceptionResponseBody springBootExceptionResponseBody)
	{
		super(springBootExceptionResponseBody.getMessage());

		this.timestamp = springBootExceptionResponseBody.getTimestamp();
		this.exceptionClassText = springBootExceptionResponseBody.getException();
		this.exception = getExceptionClass(exceptionClassText);
		this.status = springBootExceptionResponseBody.getStatus();
		this.error = springBootExceptionResponseBody.getError();
		this.path = springBootExceptionResponseBody.getPath();
	}

	private Class<?> getExceptionClass(String exceptionClassText)
	{
		try
		{
			return Class.forName(exceptionClassText);
		}
		catch (ClassNotFoundException e)
		{
			return null;
		}
	}

	public Instant getTimestamp()
	{
		return timestamp;
	}

	public String getExceptionClassText()
	{
		return exceptionClassText;
	}

	public Class<?> getException()
	{
		return exception;
	}

	public Integer getStatus()
	{
		return status;
	}

	public String getError()
	{
		return error;
	}

	public String getPath()
	{
		return path;
	}
}
