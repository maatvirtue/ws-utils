package net.maatvirtue.wsutils.restexception.impl;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;
import net.maatvirtue.wsutils.restexception.api.UnknownRestException;
import net.maatvirtue.wsutils.restexception.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class RestExceptionFactory
{
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionFactory.class);
	private static RestExceptionFactory instance;

	private Map<String, Class<? extends RestException>> restExceptions;

	private RestExceptionFactory()
	{
		restExceptions = new HashMap<>();

		discoverAndLoadRestExceptions();
	}

	public static RestExceptionFactory getInstance()
	{
		if(instance == null)
			instance = new RestExceptionFactory();

		return instance;
	}

	public RestException getRestException(Response.Status httpStatus, String code, String message)
	{
		Class<? extends RestException> restExceptionClass = restExceptions.get(code);

		if(restExceptionClass == null)
			throw new UnknownRestException(httpStatus, code, message);

		RestException restException = instantiate(restExceptionClass);
		restException.setHttpStatus(httpStatus);
		restException.setCode(code);
		restException.setMessage(message);

		return restException;
	}

	private RestException instantiate(Class<? extends RestException> restExceptionClass)
	{
		try
		{
			return restExceptionClass.getConstructor().newInstance();
		}
		catch(Exception exception)
		{
			throw new RuntimeException("Error instanciating restexception class.", exception);
		}
	}

	private void discoverAndLoadRestExceptions()
	{
		new FastClasspathScanner(Constants.SCANNED_BASE_PACKAGE)
				.matchClassesWithAnnotation(RestExceptionMapping.class, this::loadRestExceptionClass)
				.scan();
	}

	@SuppressWarnings("unchecked")
	private void loadRestExceptionClass(Class<?> restExceptionMappingClass)
	{
		try
		{
			validateClassIsRestException(restExceptionMappingClass);
			validateClassHasNoParamConstructor(restExceptionMappingClass);

			String code = getCodeFromRestExceptionMapping(restExceptionMappingClass);

			validateCode(restExceptionMappingClass, code);

			restExceptions.put(code, (Class<? extends RestException>) restExceptionMappingClass);
		}
		catch(IllegalArgumentException exception)
		{
			logger.warn(exception.getMessage());
		}
		catch(RuntimeException exception)
		{
			logger.error("Error loading rest exception mapping class: " + restExceptionMappingClass, exception);
		}
	}

	private void validateCode(Class<?> restExceptionMappingClass, String code)
	{
		if(code == null)
			throw new IllegalArgumentException("Code must not be null");

		if(restExceptions.containsKey(code))
			throw new IllegalArgumentException("Duplicate code. Code \"" + code + "\" is used for class " +
					restExceptions.get(code).getCanonicalName() + " and class " + restExceptionMappingClass.getCanonicalName() +
					". Class " + restExceptionMappingClass.getCanonicalName() + " will be ignored.");
	}

	private void validateClassHasNoParamConstructor(Class<?> restExceptionMappingClass)
	{
		if(!hasNoParamConstructor(restExceptionMappingClass))
			throw new IllegalArgumentException("Class " + restExceptionMappingClass.getCanonicalName() +
					" does not have a no-param constructor. Ignoring class.");
	}

	private void validateClassIsRestException(Class<?> restExceptionMappingClass)
	{
		if(!RestException.class.isAssignableFrom(restExceptionMappingClass))
			throw new IllegalArgumentException("Class " + restExceptionMappingClass.getCanonicalName() + " is not a " + RestException.class.getCanonicalName() + ". Ignoring class.");
	}

	private boolean hasNoParamConstructor(Class<?> clazz)
	{
		try
		{
			clazz.getConstructor();
			return true;
		}
		catch(NoSuchMethodException e)
		{
			return false;
		}
	}

	private String getCodeFromRestExceptionMapping(Class<?> restExceptionMappingClass)
	{
		RestExceptionMapping restExceptionMapping = restExceptionMappingClass.getAnnotation(RestExceptionMapping.class);

		return restExceptionMapping.value();
	}
}
