package com.fredhopper.hr.controller;

import java.util.Collection;

import com.fredhopper.hr.controller.util.ApplicationConstants;

/**
 * Overview of the environment spits out an overview of what is going on in the
 * environment.
 */
public class Overview implements Runnable {

    private final ControllerClient controller;

    public Overview(ControllerClient controller) {
    	this.controller = controller;
    }

    @Override
    public void run() {
    	Collection<String> customers = controller.getCustomers();
    	System.out.println("Follwing are the Overview detail of the environment :- ");
    	customers.forEach(customer ->{
    		Collection<String> services = controller.getCustomerServices(customer);
    		services.forEach(service ->{
    			System.out.println("Customer ="+customer + ", Service ="+ service);
        		Collection<String> servers = controller.getServerInfo(customer, service);
    			servers.forEach(s -> System.out.println(s)); 
    			Collection<String> health =controller.getHealth(customer, service);
    			health.forEach(h -> System.out.println("Service Health ="+h));
        	});
    	});
    }

    public static void main(String[] args) {
    	// Run the example.
    	new Overview(new ControllerClient(ApplicationConstants.URL)).run();
	}
}
