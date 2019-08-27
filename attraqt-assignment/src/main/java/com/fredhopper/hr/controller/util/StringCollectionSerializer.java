package com.fredhopper.hr.controller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class StringCollectionSerializer implements MessageBodyWriter<Collection<String>>, MessageBodyReader<Collection<String>> {
	@Override
	public long getSize(Collection<String> arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// XXX: not complete, should actually check the generic type.
		return Collection.class.isAssignableFrom(arg0);
	}

	@Override
	public void writeTo(Collection<String> arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6)
			throws IOException, WebApplicationException {
		
		PrintWriter pw = new PrintWriter(arg6);
		try {
			for (Object o : arg0) {
				pw.println(o);
			}
		} finally {
			pw.close();
		}
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// XXX: not complete, should actually check the generic type.
		return type.isAssignableFrom(Collection.class);
	}

	@Override
	public Collection<String> readFrom(Class<Collection<String>> type,
			Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		// Read line by line, and convert each line to a string.
		List<String> result = new ArrayList<>();
		BufferedReader r = new BufferedReader(new InputStreamReader(entityStream));
		String s;
		while ((s = r.readLine()) != null) {
			result.add(s);
		}
		return result;
	}
}
