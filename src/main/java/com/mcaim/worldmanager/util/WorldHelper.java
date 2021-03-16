package com.mcaim.worldmanager.util;

import com.mcaim.worldmanager.WorldMain;
import com.mcaim.worldmanager.models.CustomWorldType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorldHelper {
    public static boolean doesExist(String world) {
        return Bukkit.getWorld(world) != null;
    }

    public static List<String> getWorlds() {
        return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
    }

    public static boolean canBeLoaded(String world) {
        return new File(Bukkit.getWorldContainer(), world).exists();
    }

    public static void createWorld(String name, CustomWorldType worldType) {
        WorldCreator worldCreator = new WorldCreator(name);

        switch (worldType) {
            case NORMAL:
            case THE_END:
            case NETHER:
                worldCreator.environment(World.Environment.valueOf(worldType.name()));
                worldCreator.type(WorldType.NORMAL);
                break;

            case FLAT:
                worldCreator.type(WorldType.FLAT);
                break;

            case VOID:
                worldCreator.generator(new EmptyChunkGenerator());
                break;
        }

        // Creating world
        worldCreator.createWorld();

        // Storing and saving world
        WorldMain.getInstance().getWorldConfig().saveWorldToConfig(name, worldType);
    }

    public static void deleteWorld(String name) {
        Bukkit.unloadWorld(name, true);
        new File(Bukkit.getWorldContainer() + "/" + name).delete();
    }
}
