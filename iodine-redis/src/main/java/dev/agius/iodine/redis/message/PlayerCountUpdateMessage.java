package dev.agius.iodine.redis.message;

import com.google.gson.annotations.SerializedName;

public class PlayerCountUpdateMessage {
    private final String type = "PLAYER_COUNT";

    @SerializedName("online_players")
    public int onlinePlayers;

    public PlayerCountUpdateMessage(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }
}
