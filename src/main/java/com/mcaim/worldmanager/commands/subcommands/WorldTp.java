package com.mcaim.worldmanager.commands.subcommands;

import com.mcaim.core.models.Permission;
import com.mcaim.core.util.C;
import com.mcaim.core.util.Command;
import com.mcaim.worldmanager.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class WorldTp extends Command {
    public WorldTp() {
        super(false, Permission.OP, "tp");
        List<String> worlds = WorldHelper.getWorlds();
        registerSubCommand(1, worlds);
        setErrorDisplay("<command> " + worlds);
    }

    @Override
    protected void onExecute(CommandSender sender, String alias, String[] args) {
        Player player = (Player) sender;
        World world = Objects.requireNonNull(Bukkit.getWorld(args[1]));
        Location loc = new Location(world, 0, 100, 0);

        player.setAllowFlight(true);
        player.setFlying(true);
        player.teleport(loc);
        C.msg(player, C.SUCCESS + "You have teleported to world &f" + world.getName() + "&7!");
    }
}
