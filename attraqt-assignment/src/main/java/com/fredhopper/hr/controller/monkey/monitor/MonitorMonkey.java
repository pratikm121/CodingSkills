package com.fredhopper.hr.controller.monkey.monitor;

import java.util.Collection;
import java.util.TimerTask;

import com.fredhopper.hr.controller.ControllerClient;
import com.fredhopper.hr.controller.Health;
import com.fredhopper.hr.controller.monkey.Monkey;

/**
 * A monkey that tries to maintain the environment as expected.
 */
public class MonitorMonkey extends TimerTask implements Monkey {

    private final ControllerClient controller;

    public MonitorMonkey(ControllerClient controller) {
    	this.controller = controller;
    }
    
    public String generateHealthStatus(String health,String customer,String service) {
    	String status = "The health of the service "+ service + " is "+ health+" for the customer "+ customer;
    	if(Health.DOWN.name().equals(health)) {
    		return status + ". Please check and respond immidiately.";
    	}else if (Health.DEGRADED.name().equals(health)) {
    		return status + ". Please check and take neccesaary steps.";
    	}else if(Health.OPERATIONAL.name().equals(health)) {
    		return status + " and running as expected.";
    	}else {
    		throw new IllegalArgumentException("Invalid Health type passed "+ health);
    	}
    }

	// Detect whether the service runs up to expectations.
    @Override
    public void run() {	
    	Collection<String> customers = controller.getCustomers();
    	System.out.println("Following are the details each service for each customer == ");
    	customers.forEach(customer ->{
    		Collection<String> services = controller.getCustomerServices(customer);
    		services.forEach(service ->{
        		Collection<String> health =controller.getHealth(customer, service);        		
    			health.forEach(h -> {
    				System.out.println("Customer ="+ customer + " ServiceName ="+service);
    				System.out.println(generateHealthStatus(h,customer, service));
    			});
        	});
    	});    	
    }

}
