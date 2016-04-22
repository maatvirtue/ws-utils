package net.maatvirtue.wsutils.exception.jaxrs.providers;

import net.maatvirtue.wsutils.exception.api.RestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException>
{
	@Override
	public Response toResponse(RestException restException)
	{
		return Response.status(restException.getHttpStatus()).entity(restException.getRestExceptionResponseBody()).build();
	}
}
