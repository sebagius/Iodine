package dev.agius.iodine.iodineweb;

import static spark.Spark.port;
import static spark.Spark.stop;

/**
 * Responsible for initialising and handling initial web server connections
 */
public class IodineWeb {

    private final int port;

    /**
     * Initialises the object with the specified port
     * @param port the port for the webserver
     */
    public IodineWeb(int port) {
        this.port = port;
    }

    /**
     * Initialised the object with a port of 8085
     */
    public IodineWeb() {
        this(8085);
    }

    /**
     * Bootstrap the web server
     */
    public void bootstrapWeb() {
        port(port);

    }

    /**
     * Safely shutdown the web server
     */
    public void safeShutdown() {
        stop();
    }
}
