package net.nlacombe.wsutils.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter
{
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException
	{
		if(request.getHeaderString("Origin") != null)
		{
			response.getHeaders().add("Access-Control-Allow-Origin", request.getHeaderString("Origin"));

			String requestHeaders = request.getHeaderString("Access-Control-Request-Headers");

			if(requestHeaders != null)
				response.getHeaders().add("Access-Control-Allow-Headers", requestHeaders);

			response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.getHeaders().add("Access-Control-Max-Age", "0");
		}
	}
}
