package com.mcaim.worldmanager.commands.subcommands;

import com.mcaim.core.models.Permission;
import com.mcaim.core.util.C;
import com.mcaim.core.util.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class WorldList extends Command {
    public WorldList() {
        super(true, Permission.OP, "list");
    }

    @Override
    protected void onExecute(CommandSender sender, String alias, String[] args) {
        C.msg(sender, "");
        C.msg(sender, "&8* &a&lWorld List &8*");
        C.msg(sender, "");
        Bukkit.getWorlds().forEach(world -> { C.msg(sender, "&8- &f" + world.getName()); });
    }
}
