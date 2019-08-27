package com.fredhopper.hr.controller.monkey.crash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimerTask;

import com.fredhopper.hr.controller.ControllerClient;
import com.fredhopper.hr.controller.monkey.Monkey;
import com.fredhopper.hr.controller.util.ApplicationConstants;
import com.fredhopper.hr.controller.world.World;

/**
 * A monkey that tries to kill a server in Controller environment on a random
 * basis.
 */
public class CrashMonkey extends TimerTask implements Monkey,Runnable  {
	
	
    // NOTE:
    // The details of how a server is killed is not specified.
    // It is left to the implementor of the crash monkey to decide about such
    // details.

    private final ControllerClient controller;
    private final String customer = "customerA";
    private final String service = "FAS";

    public CrashMonkey(ControllerClient controller) {
    	this.controller = controller;
    }

    @Override
    public void run() {
    	System.out.println("Running ");
    	Collection<String> servers =controller.getServers(customer, service);
    	List<String> serverList = new ArrayList<>(servers);
    	serverList.forEach(s -> {
    		System.out.println(s);
    	});
    	String randomServerId = serverList.get((int)Math.random() * serverList.size());
    	
    }
    
    public static void main(String[] args) {
    	CrashMonkey crash = new CrashMonkey(new ControllerClient(ApplicationConstants.URL));
    	crash.run();
	}
}

