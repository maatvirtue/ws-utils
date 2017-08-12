package net.nlacombe.wsutils.restexception.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import net.nlacombe.wsutils.restexception.api.RestExceptionResponseBody;
import net.nlacombe.wsutils.restexception.exception.SpringBootDefaultException;
import net.nlacombe.wsutils.restexception.exception.UnknownErrorRestException;
import net.nlacombe.wsutils.restexception.impl.RestExceptionFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestExceptionFeignErrorDecoder implements ErrorDecoder
{
	private RestExceptionFactory restExceptionFactory = RestExceptionFactory.getInstance();
	private ObjectMapper jsonConverter = new ObjectMapper();

	@Override
	public Exception decode(String methodKey, Response response)
	{
		int httpStatus = response.status();

		if (httpStatus < 400 || httpStatus > 599)
			return null;

		String responseJson = getResponseJson(response);

		RestExceptionResponseBody restExceptionResponseBody = getRestExceptionResponseBody(responseJson);

		if (restExceptionResponseBody != null)
			return restExceptionFactory.getRestException(httpStatus, restExceptionResponseBody.getErrorCode(), restExceptionResponseBody.getMessage());

		SpringBootExceptionResponseBody springBootResponseBody = getSpringBootResponseBody(responseJson);

		if (springBootResponseBody != null)
			throw new SpringBootDefaultException(springBootResponseBody);

		throw new UnknownErrorRestException("Response is not a rest exception or spring boot default exception.");
	}

	private SpringBootExceptionResponseBody getSpringBootResponseBody(String responseJson)
	{
		try
		{
			return jsonConverter.readValue(responseJson, SpringBootExceptionResponseBody.class);
		}
		catch (IOException e)
		{
			return null;
		}
	}

	private RestExceptionResponseBody getRestExceptionResponseBody(String responseJson)
	{
		try
		{
			return jsonConverter.readValue(responseJson, RestExceptionResponseBody.class);
		}
		catch (IOException e)
		{
			return null;
		}
	}

	private String getResponseJson(Response response)
	{
		try
		{
			return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			throw new UnknownErrorRestException("Error reading from response.", e);
		}
	}
}
