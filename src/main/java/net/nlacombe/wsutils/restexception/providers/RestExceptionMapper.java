package net.nlacombe.wsutils.restexception.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;
import net.nlacombe.wsutils.restexception.api.RestExceptionResponseBody;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException>
{
	private ObjectMapper jsonConverter = new ObjectMapper();

	@Override
	public Response toResponse(RestException restException)
	{
		complete(restException);

		return Response.status(restException.getHttpStatus())
				.type(MediaType.APPLICATION_JSON_TYPE)
				.entity(serialize(restException.getRestExceptionResponseBody()))
				.build();
	}

	private String serialize(RestExceptionResponseBody restExceptionResponseBody)
	{
		try
		{
			return jsonConverter.writeValueAsString(restExceptionResponseBody);
		}
		catch (JsonProcessingException exception)
		{
			throw new RuntimeException("Error serializing rest exception", exception);
		}
	}

	private void complete(RestException restException)
	{
		if (restException.getErrorCode() != null)
			return;

		RestExceptionMapping restExceptionMapping = restException.getClass().getAnnotation(RestExceptionMapping.class);
		String code = restExceptionMapping.errorCode();

		if (restException.getErrorCode() == null)
			restException.setErrorCode(code);
	}
}
