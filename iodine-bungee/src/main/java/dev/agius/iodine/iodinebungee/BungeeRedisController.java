package dev.agius.iodine.iodinebungee;

import dev.agius.iodine.redis.RedisController;
import dev.agius.iodine.redis.ServerType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.TaskScheduler;

public class BungeeRedisController extends RedisController {
    private final IodineBungee iodineBungee;
    private final String uniqueId;

    public BungeeRedisController(IodineBungee iodineBungee, String uniqueId) {
        super(ServerType.BUNGEECORD);
        this.iodineBungee = iodineBungee;
        this.uniqueId = uniqueId;
    }

    @Override
    public void publishMessage(Object message) {
        iodineBungee.getProxy().getScheduler().runAsync(iodineBungee, () -> {
            super.publishMessage(uniqueId, message);
        });
    }
}
