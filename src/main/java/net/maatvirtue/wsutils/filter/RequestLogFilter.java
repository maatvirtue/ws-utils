package net.maatvirtue.wsutils.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URI;

@Provider
public class RequestLogFilter implements ContainerRequestFilter
{
	private Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

	@Override
	public void filter(ContainerRequestContext request) throws IOException
	{
		logger.debug("Client request: " + request.getMethod() + " " + getRelativeUri(request.getUriInfo().getRequestUri()));
	}

	private String getRelativeUri(URI requestUri)
	{
		String relativeUri = "";

		relativeUri += requestUri.getPath();

		if(requestUri.getQuery() != null)
			relativeUri += "?" + requestUri.getQuery();

		return relativeUri;
	}
}
