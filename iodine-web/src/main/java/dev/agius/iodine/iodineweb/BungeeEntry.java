package dev.agius.iodine.iodineweb;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

/**
 * This is the Entrypoint for the program when it's run as a bungeecord plugin
 */
public class BungeeEntry extends Plugin {
    private IodineWeb iodineWeb;
    private Configuration configuration;

    /**
     * Runs when the plugin is enabled on the server
     */
    @Override
    public void onEnable() {
        loadConfig();
        int port = configuration.getInt("web-port", 8085);

        iodineWeb = new IodineWeb(port);

        getProxy().getScheduler().runAsync(this, ()-> iodineWeb.bootstrapWeb());
    }

    /**
     * Runs when the plugin is disabled on the server
     */
    @Override
    public void onDisable() {
        iodineWeb.safeShutdown();
    }

    /**
     * Loads config whether it be from the disk or export it from resources
     */
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
}
