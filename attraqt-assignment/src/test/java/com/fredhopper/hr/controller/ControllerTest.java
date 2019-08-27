package com.fredhopper.hr.controller;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.fredhopper.hr.controller.service.ServiceFactory;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() {
    	controller = new Controller();
    }

    @Test
    public void getCustomerNamesReturnsAllCustomerNames() {
		Service s = ServiceFactory.createService("test", ServiceType.FAS);	
		controller.addService(s);
		assertEquals(Collections.singleton("test"), controller.getCustomerNames());
    }

    @Test
    public void addServiceAddsService() {
		Service s = ServiceFactory.createService("test", ServiceType.FAS);
		controller.addService(s);
		assertEquals(s, controller.getService("test", ServiceType.FAS.name()));
    }
    
    @Test
    public void getServicesReturnsServices() {
		Service s = ServiceFactory.createService("test", ServiceType.FAS);
		controller.addService(s);
		assertEquals(Collections.singleton("FAS"), controller.getServices("test"));
    }

    //Invalid test as you will not be able to pass invalid Servicetype.
    /*@Test(expected = IllegalArgumentException.class)
    public void addServiceRejectsKnownService() {
    	Service s = ServiceFactory.createService("test", ServiceType.DATA);
		controller.addService(s);
		controller.addService(s);
    }*/
}
