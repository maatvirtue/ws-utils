package net.maatvirtue.wsutils.restexception.impl;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestExceptionScanner
{
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionScanner.class);

	private RestExceptionStorage restExceptionStorage = RestExceptionStorage.getInstance();
	private String basePackageToScan;

	public RestExceptionScanner(String basePackageToScan)
	{
		this.basePackageToScan = basePackageToScan;
	}

	public void scan()
	{
		FastClasspathScanner fastClasspathScanner;

		if(basePackageToScan == null)
			fastClasspathScanner = new FastClasspathScanner();
		else
			fastClasspathScanner = new FastClasspathScanner(basePackageToScan);

		fastClasspathScanner.matchClassesWithAnnotation(RestExceptionMapping.class, this::loadRestExceptionClass).scan();
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

			restExceptionStorage.addRestExceptionClass(code, (Class<? extends RestException>) restExceptionMappingClass);
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
		if(code == null || code.equals(""))
			throw generateError(restExceptionMappingClass, "Class has an empty or null code.");
	}

	private void validateClassHasNoParamConstructor(Class<?> restExceptionMappingClass)
	{
		if(!hasNoParamConstructor(restExceptionMappingClass))
			throw generateError(restExceptionMappingClass, "Class does not have a no-param constructor.");
	}

	private void validateClassIsRestException(Class<?> restExceptionMappingClass)
	{
		if(!RestException.class.isAssignableFrom(restExceptionMappingClass))
			throw generateError(restExceptionMappingClass, "Class is not a " + RestException.class.getCanonicalName());
	}

	private IllegalArgumentException generateError(Class<?> restExceptionMappingClass, String specificMessage)
	{
		String message = "";

		message += "Class " + restExceptionMappingClass.getCanonicalName() + " is an invalid " + RestException.class.getSimpleName() + ". ";
		message += specificMessage + " ";
		message += "Ignoring class.";

		return new IllegalArgumentException(message);
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
