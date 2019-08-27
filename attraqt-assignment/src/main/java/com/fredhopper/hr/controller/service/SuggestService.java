package com.fredhopper.hr.controller.service;

import com.fredhopper.hr.controller.Health;
import com.fredhopper.hr.controller.Service;

public class SuggestService extends Service {
    
	public SuggestService(String customerName, String serviceName) {
    	super(customerName, serviceName);
    }

    @Override
    public Health getHealth() {
		// Suggest is operational if there is at least 2 servers,
		// degraded if there is just one,
		// and down otherwise.
		switch (getServers().size()) {
		case 0:
		    return Health.DOWN;
		case 1:
		    return Health.DEGRADED;
		default:
		    return Health.OPERATIONAL;
		}
    }
}
