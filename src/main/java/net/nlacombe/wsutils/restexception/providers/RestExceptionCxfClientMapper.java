package net.nlacombe.wsutils.restexception.providers;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionResponseBody;
import net.nlacombe.wsutils.restexception.impl.RestExceptionFactory;
import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionCxfClientMapper implements ResponseExceptionMapper<RestException>
{
	private RestExceptionFactory restExceptionFactory = RestExceptionFactory.getInstance();

	@Override
	public RestException fromResponse(Response response)
	{
		Response.Status httpStatus = Response.Status.fromStatusCode(response.getStatus());

		if (!(httpStatus.getFamily() == Response.Status.Family.CLIENT_ERROR || httpStatus.getFamily() == Response.Status.Family.SERVER_ERROR))
			return null;

		RestExceptionResponseBody restExceptionResponseBody = response.readEntity(RestExceptionResponseBody.class);

		throw restExceptionFactory.getRestException(httpStatus, restExceptionResponseBody.getCode(), restExceptionResponseBody.getMessage());
	}
}
