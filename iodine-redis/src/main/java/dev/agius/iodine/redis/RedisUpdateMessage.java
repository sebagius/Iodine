package dev.agius.iodine.redis;

import com.google.gson.annotations.SerializedName;

/**
 * The internal message structure for server updates
 */
public class RedisUpdateMessage {
    @SerializedName("server_type")
    public ServerType serverType;

    @SerializedName("server_uuid")
    public String serverUniqueId;

    @SerializedName("message_type")
    public String messageType;

    public Object message;

    /**
     *
     * @param serverType the type of server sending the message
     * @param serverUniqueId the server's unique id
     * @param message the message being sent
     */
    public RedisUpdateMessage(ServerType serverType, String serverUniqueId, String messageType, Object message) {
        this.serverType = serverType;
        this.serverUniqueId = serverUniqueId;
        this.message = message;
        this.messageType = messageType;
    }
}
