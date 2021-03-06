package dev.agius.iodine.redis.message;

import com.google.gson.annotations.SerializedName;

public class InitialUpdateMessage {
    @SerializedName("max_players")
    public int maxPlayers;

    @SerializedName("online_players")
    public int onlinePlayers;

    @SerializedName("server_amount")
    public int serverAmount;

    @SerializedName("proxy_name")
    public String proxyName;

    @SerializedName("proxy_version")
    public String proxyVersion;

    @SerializedName("game_version")
    public String gameVersion;

    @SerializedName("game_protocol")
    public int gameProtocol;

    @SerializedName("plugin_version")
    public String pluginVersion;

    public InitialUpdateMessage(int maxPlayers,
                                int onlinePlayers,
                                int serverAmount,
                                String proxyName,
                                String proxyVersion,
                                String gameVersion,
                                int gameProtocol,
                                String pluginVersion) {
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
        this.serverAmount = serverAmount;
        this.proxyName = proxyName;
        this.proxyVersion = proxyVersion;
        this.gameVersion = gameVersion;
        this.gameProtocol = gameProtocol;
        this.pluginVersion = pluginVersion;
    }
}
