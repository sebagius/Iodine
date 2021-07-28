package dev.agius.iodine.redis.message;

import com.google.gson.annotations.SerializedName;

public class ServerUpdateMessage {
    private final String type = "SERVER_UPDATE";

    @SerializedName("server_name")
    public String serverName;

    @SerializedName("server_permission")
    public String serverPermission;

    @SerializedName("server_restricted")
    public Boolean serverRestricted;

    @SerializedName("server_host")
    public String serverHost;

    @SerializedName("server_port")
    public int serverPort;

    public ServerUpdateMessage(String serverName,
                               String serverPermission,
                               Boolean serverRestricted,
                               String serverHost,
                               int serverPort) {
        this.serverName = serverName;
        this.serverPermission = serverPermission;
        this.serverRestricted = serverRestricted;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }
}
