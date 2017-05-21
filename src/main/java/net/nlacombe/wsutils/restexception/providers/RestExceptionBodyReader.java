package net.nlacombe.wsutils.restexception.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nlacombe.wsutils.restexception.api.RestExceptionResponseBody;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class RestExceptionBodyReader implements MessageBodyReader<RestExceptionResponseBody>
{
	private ObjectMapper jsonConverter = new ObjectMapper();

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return type.isAssignableFrom(RestExceptionResponseBody.class) && mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public RestExceptionResponseBody readFrom(Class<RestExceptionResponseBody> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException
	{
		return jsonConverter.readValue(entityStream, RestExceptionResponseBody.class);
	}
}
