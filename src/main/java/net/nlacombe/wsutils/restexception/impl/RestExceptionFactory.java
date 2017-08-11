package net.nlacombe.wsutils.restexception.impl;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.UnknownRestException;

public class RestExceptionFactory
{
	private static RestExceptionFactory instance;

	private RestExceptionStorage restExceptionStorage = RestExceptionStorage.getInstance();

	private RestExceptionFactory()
	{
	}

	public static RestExceptionFactory getInstance()
	{
		if (instance == null)
			instance = new RestExceptionFactory();

		return instance;
	}

	public RestException getRestException(int httpStatus, String code, String message)
	{
		Class<? extends RestException> restExceptionClass = restExceptionStorage.getRestExceptionClass(code);

		if (restExceptionClass == null)
			throw new UnknownRestException(httpStatus, code, message);

		RestException restException = instantiate(restExceptionClass);
		restException.setHttpStatus(httpStatus);
		restException.setErrorCode(code);
		restException.setMessage(message);

		return restException;
	}

	private RestException instantiate(Class<? extends RestException> restExceptionClass)
	{
		try
		{
			return restExceptionClass.getConstructor().newInstance();
		}
		catch (Exception exception)
		{
			throw new RuntimeException("Error instanciating restexception class.", exception);
		}
	}
}
