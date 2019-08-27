package com.fredhopper.hr.controller.world;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.fredhopper.hr.controller.Server;

/**
 * The "world".
 * 
 * The world provides some services that can be used, but the world cannot be
 * modified.
 */
public final class World {
    private static class WorldServer {
	private final Random r = new Random(System.currentTimeMillis());
	private final String id;
	private final InetAddress address;
	private final double loadTarget;
	private final double pingTarget;

	public WorldServer(String id, InetAddress address, double loadTarget, double pingTarget) {
	    this.id = id;
	    this.address = address;
	    this.loadTarget = loadTarget;
	    this.pingTarget = pingTarget;
	}

	public String getId() {
	    return id;
	}

	public InetAddress getAddress() {
	    return address;
	}

	// standard normal distributions around the target
	public float getLoad() {
	    double v = Math.abs(r.nextGaussian() + loadTarget);
	    // Clamp to 0.0/1.0
	    return v < 0 ? 0.0f : v > 1 ? 1.0f : (float) v;
	}

	public int ping() {
	    // Map the negatives onto the positives: pings can't be < 0.
	    return (int) Math.round(Math.abs(r.nextGaussian() * 100 + pingTarget));
	}
    }

    private static final AtomicInteger nextAddress = new AtomicInteger((10 << 24) + 1);
    private static final Map<String, WorldServer> servers = new HashMap<String, WorldServer>();

    /**
     * Allocate a new server in the world.
     * 
     * @return a new server ready for service
     */
    public static Server allocateNewServer() {
	int tmp = nextAddress.incrementAndGet();
	// Make a nice and funny IP address out of it.
	try {
	    InetAddress a = InetAddress.getByAddress(new byte[] { (byte) (tmp >> 24 & 0xFF),
		    (byte) (tmp >> 16 & 0xFF), (byte) (tmp >> 8 & 0xFF), (byte) (tmp & 0xFF) });

	    Random r = new Random();
	    WorldServer s = new WorldServer(UUID.randomUUID().toString(), a, r.nextFloat(),
		    r.nextInt(700));
	    System.out.println("#Id ="+ s.getId()+ ", s="+s.ping());
	    servers.put(s.getId(), s);

	    // XXX: It takes a while for allocating a server, so this should
	    // probably sleep 10-20s.
	    // But that would also delay the startup of the environment for the
	    // first task, and tasks can be done
	    // in any order. So: leave it at that until we have a solution for
	    // this.
	    return new Server(s.getAddress(), s.getId());
	} catch (UnknownHostException e) {
	    // Cannot happen
	    throw new RuntimeException(e);
	}
    }

    /**
     * Deallocate a server that was previously allocated
     * 
     * @param s
     * @throws NullPointerException
     *             if {@code s} is {@code null}
     */
    public static void deallocateServer(Server s) {
	servers.remove(s.getId());
    }

    /**
     * Get the load of the given server.
     * 
     * @param id
     * @return
     * @throws SecurityException
     *             if the application is not permitted to query the load of the
     *             server.
     */
    public static float getServerLoad(String id) {
	WorldServer s = servers.get(id);
	if (s == null) {
	    throw new SecurityException("Permission denied");
	}
	return s.getLoad();
    }

    /**
     * Get the load of the given server.
     * 
     * @param id
     * @return
     */
    public static int pingServer(String id) {
	WorldServer s = servers.get(id);
	if (s == null) {
	    throw new SecurityException("Permission denied");
	}

	// Note that "pinging" a server of course takes as long as the ping, so
	// we sleep a bit ...
	int v = s.ping();
	try {
	    Thread.sleep(v);
	} catch (InterruptedException e) {
	    // Ignore
	}
	return v;
    }
}
