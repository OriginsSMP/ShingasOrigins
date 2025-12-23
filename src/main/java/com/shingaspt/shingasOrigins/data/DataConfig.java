package com.shingaspt.shingasOrigins.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataConfig {

    private final JavaPlugin plugin;
    private final File dataFile;
    private final Gson gson;

    // Cache
    private final Map<UUID, Boolean> cache = new HashMap<>();

    public DataConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.dataFile = new File(plugin.getDataFolder(), "userData.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        load();
    }

    private void load() {
        try {
            if (!dataFile.exists()) {
                plugin.getDataFolder().mkdirs();
                dataFile.createNewFile();
            }

            Type type = new TypeToken<Map<String, Boolean>>() {}.getType();
            FileReader reader = new FileReader(dataFile);
            Map<String, Boolean> rawData = gson.fromJson(reader, type);
            reader.close();

            if (rawData == null) return;

            // Convert String UUIDs to UUID objects
            for (Map.Entry<String, Boolean> entry : rawData.entrySet()) {
                cache.put(UUID.fromString(entry.getKey()), entry.getValue());
            }

        } catch (Exception e) {
            plugin.getLogger().severe("Failed to load userData.json");
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Map<String, Boolean> rawData = new HashMap<>();

            for (Map.Entry<UUID, Boolean> entry : cache.entrySet()) {
                rawData.put(entry.getKey().toString(), entry.getValue());
            }

            FileWriter writer = new FileWriter(dataFile);
            gson.toJson(rawData, writer);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            plugin.getLogger().severe("Failed to save userData.json");
            e.printStackTrace();
        }
    }

    // Optional helpers
    public boolean get(UUID uuid) {
        return cache.getOrDefault(uuid, false);
    }

    public void set(UUID uuid, boolean value) {
        cache.put(uuid, value);
    }

    public Map<UUID, Boolean> getCache() {
        return cache;
    }

}
