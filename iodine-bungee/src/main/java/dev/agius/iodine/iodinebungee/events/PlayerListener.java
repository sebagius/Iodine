package dev.agius.iodine.iodinebungee.events;

import dev.agius.iodine.redis.message.PlayerCountUpdateMessage;
import dev.agius.iodine.redis.message.PlayerUpdateMessage;
import dev.agius.iodine.redis.RedisController;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {
    private RedisController redisController;

    public PlayerListener(RedisController redisController) {
        this.redisController = redisController;
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        redisController.publishMessage(new PlayerCountUpdateMessage(1));
    }

    @EventHandler
    public void onPlayerServerConnection(ServerConnectedEvent event) {
        redisController.publishMessage(new PlayerUpdateMessage(
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId().toString(),
                event.getServer().getInfo().getName())
        );
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        redisController.publishMessage(new PlayerCountUpdateMessage(-1));
    }
}
