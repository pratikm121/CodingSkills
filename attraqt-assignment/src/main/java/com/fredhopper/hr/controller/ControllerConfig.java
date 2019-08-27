package com.fredhopper.hr.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.fredhopper.hr.controller.service.ServiceFactory;
import com.fredhopper.hr.controller.util.ApplicationConstants;
import com.fredhopper.hr.controller.util.FloatSerializer;
import com.fredhopper.hr.controller.util.InetAddressSerializer;
import com.fredhopper.hr.controller.util.IntegerSerializer;
import com.fredhopper.hr.controller.util.ServiceSerializer;
import com.fredhopper.hr.controller.util.StringCollectionSerializer;
import com.fredhopper.hr.controller.world.World;

/**
 * A simple controller for an environment that consists of servers, customers,
 * and service instances.
 */
public class ControllerConfig extends ResourceConfig {

    public ControllerConfig(Controller controller) {
		register(controller);
		register(StringCollectionSerializer.class);
		register(InetAddressSerializer.class);
		register(FloatSerializer.class);
		register(IntegerSerializer.class);
		register(ServiceSerializer.class);
		register(JacksonFeature.class);
    }

    /**
     * Start the controller on port {@code ApplicationConstants.PORT}.
     * 
     * @throws Exception
     * @see <a href="http://localhost:7000">Open running application</a>
     */
    public static void main(String[] args) throws Exception {
		Controller controller = new Controller();
	
		// Set up some demo data
		configureDemo(controller);
	
		// Start the controller server
		startServer(controller, ApplicationConstants.PORT);
    }

    private static void configureDemo(Controller controller) {
		// Some customers ..."customerA", "customerB", "customerC"
		for (String customerName : new String[] { "customerA", "customerB", "customerC"}) {
		    // ... with all the services we offer.
		    for (ServiceType type : ServiceType.values()) {
		    	Service s = ServiceFactory.createService(customerName, type);		
				// Give the service a server
				s.addServer(World.allocateNewServer());
			//	s.addServer(World.allocateNewServer());
			//	s.addServer(World.allocateNewServer());
				controller.addService(s);
		    }
		}
    }

    private static void startServer(Controller controller, int port) throws Exception {
		final ControllerConfig rc = new ControllerConfig(controller);
		ServletContainer container = new ServletContainer(rc);
		Server server = new Server(port);
		ServletContextHandler sch = new ServletContextHandler(server, "/",
			ServletContextHandler.SESSIONS);
		ServletHolder sh = new ServletHolder(container);
		sch.addServlet(sh, "/*");
	
		// Start it.
		server.start();
    }
}
