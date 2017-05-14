package net.nlacombe.wsutils.restexception.providers;

import net.nlacombe.wsutils.restexception.impl.RestExceptionScanner;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionFeature implements Feature
{
	public RestExceptionFeature(String packageToScan)
	{
		new RestExceptionScanner(packageToScan).scan();
	}

	@Override
	public boolean configure(FeatureContext context)
	{
		context.register(RestExceptionBodyReader.class);
		context.register(GlobalExceptionMapper.class);
		context.register(RestExceptionCxfClientMapper.class);

		return true;
	}
}
