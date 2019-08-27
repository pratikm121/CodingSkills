package com.fredhopper.hr.controller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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

import com.fredhopper.hr.controller.Service;

@Provider
@Produces("text/plain")
@Consumes("text/plain")
public class ServiceSerializer implements MessageBodyWriter<Service>, MessageBodyReader<Service>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		System.out.println("1");
		return Service.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Service t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		System.out.println("2");
		entityStream.write(t.toString().getBytes());
		
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		System.out.println("3" + type.isAssignableFrom(Service.class));
		return type.isAssignableFrom(Service.class);
	}

	@Override
	public Service readFrom(Class<Service> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		System.out.println("4");
		Service service = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(entityStream))) {
			return service;
        }
		
		
	}

}
