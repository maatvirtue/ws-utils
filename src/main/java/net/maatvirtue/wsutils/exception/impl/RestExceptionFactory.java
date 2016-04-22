package net.maatvirtue.wsutils.exception.impl;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import net.maatvirtue.wsutils.exception.api.RestException;
import net.maatvirtue.wsutils.exception.api.RestExceptionMapping;
import net.maatvirtue.wsutils.exception.api.UnknownRestException;
import net.maatvirtue.wsutils.exception.constants.Constants;
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
		if(!RestException.class.isAssignableFrom(restExceptionMappingClass))
			logger.warn("Class " + restExceptionMappingClass.getCanonicalName() + " is not a " + RestException.class.getCanonicalName());

		String code = getCodeFromRestExceptionMapping(restExceptionMappingClass);

		restExceptions.put(code, (Class<? extends RestException>) restExceptionMappingClass);
	}

	private String getCodeFromRestExceptionMapping(Class<?> restExceptionMappingClass)
	{
		RestExceptionMapping restExceptionMapping = restExceptionMappingClass.getAnnotation(RestExceptionMapping.class);

		return restExceptionMapping.value();
	}
}
