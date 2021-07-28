package dev.agius.iodine.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;

/**
 * An abstract implementation of the RedisController used by both the plugins and web server
 */
public abstract class RedisController {
    private Jedis jedis;
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private ServerType serverType;

    /**
     * Initialise the Redis Controller with the specified host, port and server type
     * @param host the redis host
     * @param port the redis port
     * @param serverType the type of server
     */
    public RedisController(String host, int port, ServerType serverType) {
        this.jedis = new Jedis(host, port);
        this.serverType = serverType;
    }

    /**
     * Initialise the Redis Controller with the default redis connection (localhost:6379) and the specified Server Type
     * @param serverType the type of server
     */
    public RedisController(ServerType serverType) {
        this("localhost", 6379, serverType);
    }

    /**
     * Publish an object
     * @param object the object to publish
     */
    public abstract void publishMessage(Object message, String messageType);

    /**
     * Publishes an object directly to the redis server in json form
     * @param uniqueId the server's unique id
     * @param object the object to publish
     */
    public void publishMessage(String uniqueId, String messageType, Object object) {
        jedis.publish("iodine_updates", gson.toJson(new RedisUpdateMessage(serverType, uniqueId, messageType, object)));
    }

    /**
     * Subscribes to the update channel with the correct listener
     * @param listener the listener
     */
    public void subscribe(SubscribeListener listener) {

    }

}
