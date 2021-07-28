package dev.agius.iodine.iodineweb;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * This is the Entrypoint for the program when it's run as a spigot plugin
 */
public class SpigotEntry extends JavaPlugin {

    private IodineWeb iodineWeb;
    private YamlConfiguration configuration;

    /**
     * Runs when the plugin is enabled on the server
     */
    @Override
    public void onEnable() {
        int port = configuration.getInt("web-port", 8085);

        iodineWeb = new IodineWeb(port);

        getServer().getScheduler().runTaskAsynchronously(this, ()-> {
            iodineWeb.bootstrapWeb();
        });
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
            try (InputStream in = SpigotEntry.class.getClassLoader().getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configuration = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
    }
}
