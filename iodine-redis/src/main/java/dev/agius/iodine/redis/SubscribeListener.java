package dev.agius.iodine.redis;

import dev.agius.iodine.redis.message.*;

public interface SubscribeListener {
    void receiveMessage(ServerType serverType, String serverUniqueId, InitialUpdateMessage message);
    void receiveMessage(ServerType serverType, String serverUniqueId, PlayerCountUpdateMessage message);
    void receiveMessage(ServerType serverType, String serverUniqueId, PlayerUpdateMessage message);
    void receiveMessage(ServerType serverType, String serverUniqueId, PluginStatus message);
    void receiveMessage(ServerType serverType, String serverUniqueId, PluginUpdateMessage message);
    void receiveMessage(ServerType serverType, String serverUniqueId, ServerUpdateMessage message);
}
