package dev.agius.iodine.redis.message;

import com.google.gson.annotations.SerializedName;

public class PluginUpdateMessage {

    private final String type = "PLUGIN_UPDATE";

    @SerializedName("plugin_status")
    public PluginStatus pluginStatus;

    public PluginUpdateMessage(PluginStatus pluginStatus) {
        this.pluginStatus = pluginStatus;
    }
}
