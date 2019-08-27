package com.fredhopper.hr.controller;

import java.util.Collection;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.client.ClientConfig;

import com.fredhopper.hr.controller.util.ApplicationConstants;
import com.fredhopper.hr.controller.util.FloatSerializer;
import com.fredhopper.hr.controller.util.InetAddressSerializer;
import com.fredhopper.hr.controller.util.IntegerSerializer;
import com.fredhopper.hr.controller.util.StringCollectionSerializer;

/**
 * This is a helper class that allows to talk to an instance of Controller
 * remotely through HTTP protocol.
 */
public class ControllerClient {

    // NOTE: Add methods if needed.

    private final WebTarget controller;

    public ControllerClient(String controllerUrl) {
		ClientConfig cc = new ClientConfig();
	
		// Add other things here if needed.
		cc.register(StringCollectionSerializer.class);
		cc.register(InetAddressSerializer.class);
		cc.register(FloatSerializer.class);
		cc.register(IntegerSerializer.class);
	
		// Create a simple Jersey client with the proper provider configuration.
		controller = ClientBuilder.newClient(cc).target(controllerUrl);
    }

    /**
     * @return the names of all customers
     */
    public Collection<String> getCustomers() {
    	return controller.path(ApplicationConstants.CUSTOMERS).request()
    			.get(makeGenericStringCollectionType());
    }

    /**
     * @return the services for the customer
     */
    public Collection<String> getCustomerServices(String customer) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES).request()
    			.get(makeGenericStringCollectionType());
    }

    // Methods to consider:
    // 1. Get the servers of a service of a customer
    public Collection<String> getServers(String customer,String service) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).path(ApplicationConstants.SERVERS).request()
    			.get(makeGenericStringCollectionType());
    }
    
    // 2. Get the health of a service of a customer
    public Collection<String> getHealth(String customer,String service) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).path(ApplicationConstants.HEALTH).request()
    			.get(makeGenericStringCollectionType());
    }  
    
    // 3. Get the server info of a service of a customer
    public Collection<String> getServerInfo(String customer,String service) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).path(ApplicationConstants.INFO).request()
    			.get(makeGenericStringCollectionType());
    }
    
    // 4. Add a new server for a service of a customer
    public Collection<String> addServer(String customer,String service) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).path("server").path("add").request()
    			.get(makeGenericStringCollectionType());
    }
    
    // 5. Remove a server of a service of a customer
    public Collection<String> removeServer(String customer,String service,String serverId) {
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).path("server").path("remove").path(serverId).request()
    			.get(makeGenericStringCollectionType());
    }
    
   public Service getService(String customer,String service){
    	return controller.path(ApplicationConstants.CUSTOMERS).path(customer)
    			.path(ApplicationConstants.SERVICES)
    			.path(service).request().get(makeGenericServiceType());
    	
	}

    /** Create a GenericType<Collection<String>> for use with jersey's methods. */
    private static GenericType<Collection<String>> makeGenericStringCollectionType() {
    	return new GenericType<Collection<String>>() { /* Nothing here */ };
    }
    
    /** Create a GenericType<Service> for use with jersey's methods. */
    private static GenericType<Service> makeGenericServiceType() {
    	return new GenericType<Service>() { /* Nothing here */ };
    }

}
