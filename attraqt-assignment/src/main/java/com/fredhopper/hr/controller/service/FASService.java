package com.fredhopper.hr.controller.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fredhopper.hr.controller.Health;
import com.fredhopper.hr.controller.Server;
import com.fredhopper.hr.controller.Service;

/**
 * A service implementation for "FAS".
 */
public class FASService extends Service {
	
    public FASService(String customerName, String serviceName) {
    	super(customerName, serviceName);
    }

    @Override
    public Health getHealth() {
		// FAS is healthy if:
		// - all servers ping below 600: otherwise degraded
		// - all servers have a load of less than 0.7: otherwise degraded
		// - there is at least two servers available: degraded if at least one,
		// or down if none.
	    Set<Server> servers =getServers();	
	    DecimalFormat df = new DecimalFormat("#.#");
	    df.setRoundingMode(RoundingMode.CEILING);
	    if(servers != null && servers.size()>0) {
	    	List<Server> operationalServers = new ArrayList<>();
	    	servers.forEach(s ->{
	    		if(((Float.valueOf(df.format(s.getLoad()))) < 0.7) && (s.ping() < 600)) {
	    			operationalServers.add(s);	    			
	    		}
	    	});
	    	
	    	
	
			if(operationalServers.size() >1) {
				return Health.OPERATIONAL;
			}else {
				return Health.DEGRADED;
			}
	    }else {
	    	return Health.DOWN;
	    }
	
    }
}
