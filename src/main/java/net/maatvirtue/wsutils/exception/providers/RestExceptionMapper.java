package net.maatvirtue.wsutils.exception.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.maatvirtue.wsutils.exception.api.RestException;
import net.maatvirtue.wsutils.exception.api.RestExceptionMapping;
import net.maatvirtue.wsutils.exception.api.RestExceptionResponseBody;

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
				.entity(serialize(restException.getRestExceptionResponseBody()))
				.build();
	}

	private String serialize(RestExceptionResponseBody restExceptionResponseBody)
	{
		try
		{
			return jsonConverter.writeValueAsString(restExceptionResponseBody);
		}
		catch(JsonProcessingException exception)
		{
			throw new RuntimeException("Error serializing rest exception", exception);
		}
	}

	private void complete(RestException restException)
	{
		if(restException.getCode() != null && restException.getHttpStatus() != null)
			return;

		RestExceptionMapping restExceptionMapping = restException.getClass().getAnnotation(RestExceptionMapping.class);
		String code = restExceptionMapping.value();

		if(restException.getCode() == null)
			restException.setCode(code);

		if(restException.getHttpStatus() == null)
			restException.setHttpStatus(restExceptionMapping.status());
	}
}
