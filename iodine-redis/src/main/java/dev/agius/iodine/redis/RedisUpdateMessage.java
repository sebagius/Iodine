package dev.agius.iodine.redis;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RedisUpdateMessage {
    @SerializedName("server_type")
    public ServerType serverType;

    @SerializedName("server_uuid")
    public String serverUniqueId;

    public Object message;

    public RedisUpdateMessage(ServerType serverType, String serverUniqueId, Object message) {
        this.serverType = serverType;
        this.serverUniqueId = serverUniqueId;
        this.message = message;
    }
}
