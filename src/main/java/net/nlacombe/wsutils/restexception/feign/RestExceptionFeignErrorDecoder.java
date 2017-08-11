package net.nlacombe.wsutils.restexception.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import net.nlacombe.wsutils.restexception.api.RestExceptionResponseBody;
import net.nlacombe.wsutils.restexception.exception.UnknownErrorRestException;
import net.nlacombe.wsutils.restexception.impl.RestExceptionFactory;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestExceptionFeignErrorDecoder implements ErrorDecoder
{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestExceptionFeignErrorDecoder.class);

	private RestExceptionFactory restExceptionFactory = RestExceptionFactory.getInstance();
	private ObjectMapper jsonConverter = new ObjectMapper();

	@Override
	public Exception decode(String methodKey, Response response)
	{
		int httpStatus = response.status();

		if (httpStatus < 400 || httpStatus > 599)
			return null;

		RestExceptionResponseBody restExceptionResponseBody;

		try
		{
			restExceptionResponseBody = jsonConverter.readValue(response.body().asInputStream(), RestExceptionResponseBody.class);
		}
		catch (IOException e)
		{
			throw new UnknownErrorRestException("Error reading RestException from response.", e);
		}

		return restExceptionFactory.getRestException(httpStatus, restExceptionResponseBody.getErrorCode(), restExceptionResponseBody.getMessage());
	}
}
