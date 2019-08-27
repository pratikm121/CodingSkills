package com.fredhopper.hr.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import com.fredhopper.hr.controller.world.World;


/**
 * The actual controlling application.
 */
@Provider
@Path("/")
public class Controller {
	private final List<Service> services = new ArrayList<>();
	
	//http://localhost:7000/customers/
	@GET
	@Path("customers")
	@Produces("text/plain")
	public Collection<String> getCustomerNames() {
		return services.stream().map(Service::getCustomerName).collect(Collectors.toSet());
	}
	
	//http://localhost:7000/customers/customerA/services
	@GET
	@Path("customers/{customer}/services")
	@Produces("text/plain")
	public Collection<String> getServices(@PathParam("customer") String customerName) {
		return services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.map(Service::getServiceName)
				.collect(Collectors.toSet());
	}

	public void addService(Service s) {
		services.add(s);
	}
	
	//http://localhost:7000/customers/customerA/services/DATA/servers
	@GET
	@Path("customers/{customer}/services/{service}/servers")
	@Produces("text/plain")
	public Collection<String> getServers(@PathParam("customer") String customerName, @PathParam("service") String serviceName) {
		Collection<String> serverDetails = new ArrayList<>();
		String details = "";
		Service service =  services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.findFirst()
				.orElse(null);
		
		if(service != null) {
			Set<Server> servers = service.getServers();
			for(Server server : servers){
				details = "Server Id ="+server.getId();
				World.pingServer(server.getId());
				serverDetails.add(details);
			}
			return serverDetails;
		}else {
			return serverDetails;
		}
	}
	
	//http://localhost:7000/customers/customerA/services/DATA/info
	@GET
	@Path("customers/{customer}/services/{service}/info")
	@Produces("text/plain")
	public Collection<String> getServerInfo(@PathParam("customer") String customerName, @PathParam("service") String serviceName) {
		Service service =  services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.findFirst()
				.orElse(null);
		
		Collection<String> serverDetails = new ArrayList<>();
		
		if(service != null) {			
			Set<Server> servers = service.getServers();
			String details = "";
			for(Server server : servers){
				details = "Server Id ="+server.getId() + " "+ "Server Load ="+server.getLoad()+ " " +
							"Server ping ="+server.ping()+" "+ 
						  "Server Address ="+server.getAddress()+" "+ "Server StartTime ="+server.getStartTime();
				serverDetails.add(details);
			}
			return serverDetails;
		}else {
			return serverDetails;
		}
	}
	
	//http://localhost:7000/customers/customerA/services/DATA/health
	@GET
	@Path("customers/{customer}/services/{service}/health")
	@Produces("text/plain")
	public Collection<String> getServiceHealth(@PathParam("customer") String customerName, @PathParam("service") String serviceName) {
		return services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.map(Service::getServiceHealth)
				.collect(Collectors.toSet());
	}
	
	//http://localhost:7000/customers/customerA/services/DATA/server/add
	@GET
	@Path("customers/{customer}/services/{service}/server/add")
	@Produces("text/plain")
	public Collection<String> addServer(@PathParam("customer") String customerName, @PathParam("service") String serviceName) {
		Service service =  services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.findFirst()
				.orElse(null);
		Collection<String> confirmation = new ArrayList<>();
		
		if(service != null) {
			Server server= World.allocateNewServer();
			service.addServer(server);
			confirmation.add("New Server "+server.getId() + " added at "+server.getAddress());
		}else {
			confirmation.add("New server could not be added. Please try again!!");
		}
		
		return confirmation;
	}
	
	//http://localhost:7000/customers/customerA/services/DATA/server/remove/{serverId}
	@GET
	@Path("customers/{customer}/services/{service}/server/remove/{serverId}")
	@Produces("text/plain")
	public Collection<String> removeServer(@PathParam("customer") String customerName, @PathParam("service") String serviceName, @PathParam("serverId") String serverId) {
		Collection<String> confirmation = new ArrayList<>();
		String details = "";
		Service service =  services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.findFirst()
				.orElse(null);
		
		if(service != null && service.getServers().size() >0) {
			Set<Server> servers = service.getServers();
			Server server = servers.stream()
							.filter(s -> serverId.equals(s.getId()))
							.findFirst().orElse(null);
			
			if(server != null) {
				World.deallocateServer(server);
				details = "Server Id "+ serverId + " has been deallocated from service "+serviceName + " of "+customerName;
				confirmation.add(details);
				return confirmation;
			}else {
				details = "No such server with Id "+serverId;
				confirmation.add(details);
				return confirmation;
			}
		}else {
			details = "No such server allocated to the service "+serviceName + " of "+customerName;
			confirmation.add(details);
			return confirmation;
		}
	
	}	
	
	@Path("customers/{customer}/services/{service}")
	public Service getService(@PathParam("customer") String customerName, @PathParam("service") String serviceName) {
		return services.stream()
				.filter(s -> customerName.equals(s.getCustomerName()))
				.filter(s -> serviceName.equals(s.getServiceName()))
				.findFirst()
				.orElse(null);
	}
	
}
