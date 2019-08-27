package com.fredhopper.hr.controller.service;

import com.fredhopper.hr.controller.Health;
import com.fredhopper.hr.controller.Service;

public class DataService extends Service {
    public DataService(String customerName, String serviceName) {
    	super(customerName, serviceName);
    }

    @Override
    public Health getHealth() {
		// Data services are always available.
		return Health.OPERATIONAL;
    }
}
