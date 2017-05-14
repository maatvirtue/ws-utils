package net.nlacombe.wsutils.restexception.providers;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.exception.UnknownErrorRestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable>
{
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionMapper.class);

	private RestExceptionMapper restExceptionMapper = new RestExceptionMapper();

	@Override
	public Response toResponse(Throwable throwable)
	{
		RestException restException;

		if (throwable instanceof RestException)
			restException = (RestException) throwable;
		else
		{
			String message = "Unknown error.";

			logger.error(message, throwable);

			restException = new UnknownErrorRestException(message);
		}

		return restExceptionMapper.toResponse(restException);
	}
}
