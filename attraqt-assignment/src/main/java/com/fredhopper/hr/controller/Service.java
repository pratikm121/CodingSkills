package com.fredhopper.hr.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * A service delivered to a customer.
 */
@Provider
public abstract class Service implements Cloneable{
    private final Set<Server> servers = new HashSet<Server>();
    private final String customerName;
    private final String serviceName;

    public Service(String customerName, String serviceName) {
		this.customerName = customerName;
		this.serviceName = serviceName;
    }
    
    @Override
    public Service clone() throws CloneNotSupportedException {
    	return (Service)super.clone();
    }
    
    

    public String getCustomerName() {
    	return customerName;
    }

    public String getServiceName() {
    	return serviceName;
    }

    public void addServer(Server server) {
    	servers.add(server);
    }

    public Set<Server> getServers() {
    	return servers;
    }

    public abstract Health getHealth();

    @GET
    @Path("servers")
    @Produces("text/plain")
    public Collection<String> getServerIds() {
    	return servers.stream().map(Server::getId).collect(Collectors.toSet());
    }

    @Path("servers/{server}")
    public Server getServer(@PathParam("server") String serverId) {
    	return servers.stream().filter(server -> server.getId().equals(serverId)).findFirst().orElse(null);
    }

    @GET
    @Path("health")
    @Produces("text/plain")
    public String getServiceHealth() {
    	return getHealth().name();
    }
}
