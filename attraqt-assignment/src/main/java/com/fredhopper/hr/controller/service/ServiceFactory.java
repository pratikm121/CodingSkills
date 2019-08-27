package com.fredhopper.hr.controller.service;

import com.fredhopper.hr.controller.Service;
import com.fredhopper.hr.controller.ServiceType;

/**
 * Factory for services.
 */
public final class ServiceFactory {
    /**
     * Create the appropriate service based on the name.
     * 
     * @param customerName
     * @param service
     * @return
     * @throws IllegalArgumentException
     *             if the service name is unknown
     */
    public static Service createService(String customerName, ServiceType service) {
	switch (service) {
	case FAS:
	    return new FASService(customerName, service.name());
	case SUGGEST:
	    return new SuggestService(customerName, service.name());
	case DATA:
	    return new DataService(customerName, service.name());
	}
	throw new IllegalArgumentException("Cannot create a service " + service);
    }
}
