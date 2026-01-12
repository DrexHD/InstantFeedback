package me.drex.instantfeedback.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static me.drex.instantfeedback.InstantFeedback.LOGGER;

public class ConfigManager {

    public static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    public static final Path CONFIG_FILE = CONFIG_DIR.resolve("instantfeedback.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Config config = new Config();

    public static boolean load() {
        LOGGER.info("Loading instantfeedback config");
        if (Files.exists(CONFIG_FILE)) {
            try {
                String data = Files.readString(CONFIG_FILE);
                try {
                    config = GSON.fromJson(data, Config.class);
                    Files.writeString(CONFIG_FILE, GSON.toJson(config));
                    return true;
                } catch (JsonSyntaxException e) {
                    LOGGER.error("Failed to parse instantfeedback config", e);
                }
            } catch (IOException e) {
                LOGGER.error("Failed to load instantfeedback config", e);
            }
        } else {
            try {
                Files.writeString(CONFIG_FILE, GSON.toJson(config));
                return true;
            } catch (IOException e) {
                LOGGER.error("Failed to save instantfeedback config", e);
            }
        }
        return false;
    }

    public static Config config() {
        return config;
    }

}
