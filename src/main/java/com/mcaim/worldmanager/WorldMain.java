package com.mcaim.worldmanager;

import com.mcaim.worldmanager.commands.WorldSuperCommand;
import com.mcaim.worldmanager.util.WorldConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldMain extends JavaPlugin {
    private static WorldMain worldMain;
    public static WorldMain getInstance() { return worldMain; }

    private WorldConfig worldConfig;
    public final WorldConfig getWorldConfig() { return worldConfig; }

    @Override
    public void onEnable() {
        worldMain = this;
        worldConfig = new WorldConfig();
        worldConfig.loadWorldsFromConfig();
        new WorldSuperCommand().registerCommand();
    }

    @Override
    public void onDisable() {

    }
}
