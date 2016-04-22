package net.maatvirtue.wsutils.exception.jaxrs.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.maatvirtue.wsutils.exception.api.RestException;
import net.maatvirtue.wsutils.exception.api.RestExceptionResponseBody;
import net.maatvirtue.wsutils.exception.impl.RestExceptionFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;

@Provider
public class RestExceptionResponseFilter implements ClientResponseFilter
{
	private RestExceptionFactory restExceptionFactory = RestExceptionFactory.getInstance();
	private ObjectMapper jsonConverter = new ObjectMapper();

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException
	{
		Response.Status httpStatus = Response.Status.fromStatusCode(responseContext.getStatus());

		if(isRestException(httpStatus, responseContext.getEntityStream()))
			throw convertToRestException(httpStatus, responseContext.getEntityStream());
	}

	private boolean isRestException(Response.Status httpStatus, InputStream httpBodyInputStream)
	{
		if(!(httpStatus.getFamily() == Response.Status.Family.CLIENT_ERROR || httpStatus.getFamily() == Response.Status.Family.SERVER_ERROR))
			return false;

		try
		{
			jsonConverter.readValue(httpBodyInputStream, RestExceptionResponseBody.class);
			return true;
		}
		catch(IOException exception)
		{
			return false;
		}
	}

	private RestException convertToRestException(Response.Status httpStatus, InputStream httpBodyInputStream) throws IOException
	{
		RestExceptionResponseBody restExceptionResponseBody = jsonConverter.readValue(httpBodyInputStream, RestExceptionResponseBody.class);

		return restExceptionFactory.getRestException(httpStatus, restExceptionResponseBody.getCode(), restExceptionResponseBody.getMessage());
	}
}
