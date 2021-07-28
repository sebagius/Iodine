package dev.agius.iodine.iodinebungee;

import dev.agius.iodine.redis.message.*;
import dev.agius.iodine.redis.RedisController;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.UUID;

public class IodineBungee extends Plugin {

    private RedisController redisController;
    private Configuration configuration;

    @Override
    public void onEnable() {
        loadConfig();
        String uniqueId = configuration.getString("_iodine_uuid", null);
        if(uniqueId == null) {
            String generatedUniqueId = UUID.randomUUID().toString();
            configuration.set("_iodine_uuid", generatedUniqueId);
            uniqueId = generatedUniqueId;
            saveConfig();
        }

        redisController = new BungeeRedisController(this, uniqueId);

        redisController.publishMessage(new PluginUpdateMessage(PluginStatus.ENABLE));

        redisController.publishMessage(new InitialUpdateMessage(
                getProxy().getConfig().getPlayerLimit(),
                getProxy().getOnlineCount(),
                getProxy().getServers().size(),
                getProxy().getName(),
                getProxy().getVersion(),
                getProxy().getGameVersion(),
                getProxy().getProtocolVersion())
        );

        getProxy().getServers().forEach((s, serverInfo) -> {
            InetSocketAddress addr = (InetSocketAddress) serverInfo.getSocketAddress();
            redisController.publishMessage(new ServerUpdateMessage(
                    serverInfo.getName(),
                    serverInfo.getPermission(),
                    serverInfo.isRestricted(),
                    addr.getHostName(),
                    addr.getPort())
            );
        });

        getProxy().getPlayers().forEach(proxiedPlayer -> {
            redisController.publishMessage(new PlayerUpdateMessage(proxiedPlayer.getName(), proxiedPlayer.getUniqueId().toString(), proxiedPlayer.getServer().getInfo().getName()));
        });
    }

    @Override
    public void onLoad() {
        if(redisController != null)
            redisController.publishMessage(new PluginUpdateMessage(PluginStatus.LOAD));
    }

    @Override
    public void onDisable() {
        if(redisController != null)
            redisController.publishMessage(new PluginUpdateMessage(PluginStatus.DISABLE));
    }

    private void loadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
