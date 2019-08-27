package com.fredhopper.hr.controller;


import java.util.Timer;
import com.fredhopper.hr.controller.monkey.Monkey;
import com.fredhopper.hr.controller.monkey.crash.CrashMonkey;
import com.fredhopper.hr.controller.monkey.monitor.MonitorMonkey;
import com.fredhopper.hr.controller.util.ApplicationConstants;

/**
 * The monkeys are a set of {@link Monkey} instances each of which may have
 * different roles in the environment. This class initializes the monkeys and
 * sets them loose!
 */
public class Monkeys {
	

    // NOTE:
    // The ultimate goal of any monkey is not to interfere with functionality of
    // environment but only to help to improve it.

    public static void main(String[] args) {
    	// Monkeys to initialize:
    	MonitorMonkey monitor = new MonitorMonkey(new ControllerClient(ApplicationConstants.URL));
    	CrashMonkey crash = new CrashMonkey(new ControllerClient(ApplicationConstants.URL));
    	
    	//This will schedule a monkey to run every 15 seconds and check the status.
    	Timer t=new Timer();
    	t.scheduleAtFixedRate(monitor, 0,15*1000);
    	t.scheduleAtFixedRate(crash, 0,15*1000);
    }

}
