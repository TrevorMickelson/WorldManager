package com.mcaim.worldmanager.util;

import com.mcaim.core.configuration.DataFile;
import com.mcaim.core.scheduler.Async;
import com.mcaim.worldmanager.WorldMain;
import com.mcaim.worldmanager.models.CustomWorldType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class WorldConfig {
    private final DataFile dataFile = new DataFile(WorldMain.getInstance(), "worlds.yml");
    private final FileConfiguration config = dataFile.getData();
    private final String WORLD_SECTION = "worlds";

    public void saveWorldToConfig(String name, CustomWorldType type) {
        Async.get().run(() -> {
            String path = getWorldPath(name);
            config.set(path + "type", type.name());
            dataFile.saveData();
        });
    }


    public void loadWorldsFromConfig() {
        if (!config.isSet(WORLD_SECTION))
            return;

        getWorldSection().forEach(key -> {
            if (WorldHelper.canBeLoaded(key)) {
                // Config path
                String path = getWorldPath(key);

                // Loading world
                String type = Objects.requireNonNull(config.getString(path + "type"));
                CustomWorldType worldType = CustomWorldType.valueOf(type.toUpperCase());
                WorldHelper.createWorld(key, worldType);
            }
        });
    }

    private String getWorldPath(String world) {
        return WORLD_SECTION + "." + world + ".";
    }

    private Set<String> getWorldSection() {
        return Objects.requireNonNull(config.getConfigurationSection(WORLD_SECTION)).getKeys(false);
    }
}
