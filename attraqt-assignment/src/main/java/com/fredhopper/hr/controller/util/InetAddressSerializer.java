package com.fredhopper.hr.controller.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.InetAddress;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("text/plain")
public class InetAddressSerializer implements MessageBodyWriter<InetAddress> {
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return InetAddress.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(InetAddress t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return t.getHostAddress().getBytes().length;
	}

	@Override
	public void writeTo(InetAddress t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		entityStream.write(t.getHostAddress().getBytes());
	}
}
