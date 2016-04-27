package net.maatvirtue.wsutils.restexception.impl;

import net.maatvirtue.wsutils.restexception.api.RestException;

import java.util.HashMap;
import java.util.Map;

public class RestExceptionStorage
{
	private static RestExceptionStorage instance;

	private Map<String, Class<? extends RestException>> restExceptions;

	private RestExceptionStorage()
	{
		restExceptions = new HashMap<>();
	}

	public static RestExceptionStorage getInstance()
	{
		if(instance == null)
			instance = new RestExceptionStorage();

		return instance;
	}

	public void addRestExceptionClass(String code, Class<? extends RestException> restExceptionClass)
	{
		if(restExceptions.containsKey(code))
			throw new IllegalArgumentException("Duplicate code. Code \"" + code + "\" is used for class " +
					restExceptions.get(code).getCanonicalName() + " and class " + restExceptionClass.getCanonicalName() +
					". Class " + restExceptionClass.getCanonicalName() + " will be ignored.");

		restExceptions.put(code, restExceptionClass);
	}

	public Class<? extends RestException> getRestExceptionClass(String code)
	{
		return restExceptions.get(code);
	}
}
