package com.fredhopper.hr.controller.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("text/plain")
@Consumes("text/plain")
public class FloatSerializer implements MessageBodyWriter<Float>, MessageBodyReader<Float> {
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Float.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(Float t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return t.toString().getBytes().length;
	}

	@Override
	public void writeTo(Float t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		entityStream.write(t.toString().getBytes());
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(Float.class);
	}

	@Override
	public Float readFrom(Class<Float> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		// Read the stream fully into a StringBuilder, and then parse it.
		StringBuilder sb = new StringBuilder();
		int c;
		while ((c = entityStream.read()) != -1) {
			sb.append((char) c);
		}
		
		return Float.valueOf(sb.toString());
	}
}
