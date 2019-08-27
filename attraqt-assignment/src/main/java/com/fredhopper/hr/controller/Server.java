package com.fredhopper.hr.controller;

import java.net.InetAddress;
import java.util.Objects;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import com.fredhopper.hr.controller.world.World;

@Provider
public class Server {
    private final long startTime;
    private final InetAddress address;
    private final String id;

    public Server(InetAddress address, String id) {
		this.startTime = System.currentTimeMillis();
		this.address = address;
		this.id = id;
    }

    /**
     * Get the time at which this server was started.
     * 
     * @return
     */
    public long getStartTime() {
    	return startTime;
    }

    @GET
    @Path("address")
    @Produces("text/plain")
    public InetAddress getAddress() {
    	return address;
    }

    @GET
    @Produces("text/plain")
    public String getId() {
    	return id;
    }

    /**
     * Get the "ping" response time from this server.
     * 
     * @return
     */
    @GET
    @Path("ping")
    @Produces("text/plain")
    public int ping() {
    	return World.pingServer(id);
    }

    /**
     * Get the "load" of this server.
     * 
     * @return
     */
    @GET
    @Path("load")
    @Produces("text/plain")
    public float getLoad() {
    	return World.getServerLoad(id);
    }

    @Override
    public int hashCode() {
    	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
    	return (o instanceof Server) && Objects.equals(((Server) o).id, id);
    }
}
