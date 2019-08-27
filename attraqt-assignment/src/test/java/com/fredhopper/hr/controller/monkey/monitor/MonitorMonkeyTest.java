package com.fredhopper.hr.controller.monkey.monitor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fredhopper.hr.controller.ControllerClient;
import com.fredhopper.hr.controller.util.ApplicationConstants;

public class MonitorMonkeyTest {
	
	private MonitorMonkey monitor;

	@Before
	public void init() {
		monitor = new MonitorMonkey(new ControllerClient(ApplicationConstants.URL));
	}
	
	@Test
	public void generateHealthStatus() {
		String customer = "customerA";
		String health = "OPERATIONAL";
		String service = "SUGGEST";
		String operationalExpected = "The health of the service "+ service + " is OPERATIONAL for the customer "+ customer+ " and running as expected.";
		String downExpected = "The health of the service "+ service + " is DOWN for the customer "+ customer+ ". Please check and respond immidiately.";
		String degradedExpected = "The health of the service "+ service + " is DEGRADED for the customer "+ customer+ ". Please check and take neccesaary steps.";
		
		assertEquals(operationalExpected, monitor.generateHealthStatus(health, customer, service));
		health = "DEGRADED";
		assertEquals(degradedExpected, monitor.generateHealthStatus(health, customer, service));
		health = "DOWN";
		assertEquals(downExpected, monitor.generateHealthStatus(health, customer, service));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidHealthTypeThrowsError() {
		monitor.generateHealthStatus("MAINTAINANCE", "customerA", "SUGGEST");
	}

}
