package dev.agius.iodine.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;

public abstract class RedisController {
    private Jedis jedis;
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private ServerType serverType;

    public RedisController(String host, int port, ServerType serverType) {
        this.jedis = new Jedis(host, port);
        this.serverType = serverType;
    }

    public RedisController(ServerType serverType) {
        this("localhost", 6379, serverType);
    }

    public abstract void publishMessage(Object object);

    public void publishMessage(String uniqueId, Object object) {
        jedis.publish("iodine_updates", gson.toJson(new RedisUpdateMessage(serverType, uniqueId, object)));
    }

}
