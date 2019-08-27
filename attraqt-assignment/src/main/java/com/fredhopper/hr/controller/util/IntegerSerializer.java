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
public class IntegerSerializer implements MessageBodyWriter<Integer>, MessageBodyReader<Integer> {
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Integer.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(Integer t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return t.toString().getBytes().length;
	}

	@Override
	public void writeTo(Integer t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		entityStream.write(t.toString().getBytes());
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(Integer.class);
	}

	@Override
	public Integer readFrom(Class<Integer> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		// Read the stream fully into a StringBuilder, and then parse it.
		StringBuilder sb = new StringBuilder();
		int c;
		while ((c = entityStream.read()) != -1) {
			sb.append((char) c);
		}
		
		return Integer.valueOf(sb.toString());
	}
}
