package dev.agius.iodine.redis.message;

import com.google.gson.annotations.SerializedName;

public class PlayerUpdateMessage {
    private final String type = "PLAYER_UPDATE";

    @SerializedName("player_name")
    public String playerName;

    @SerializedName("player_uuid")
    public String playerUuid;

    @SerializedName("player_server")
    public String playerServer;

    public PlayerUpdateMessage(String playerName, String playerUuid, String playerServer) {
        this.playerName = playerName;
        this.playerUuid = playerUuid;
        this.playerServer = playerServer;
    }
}
