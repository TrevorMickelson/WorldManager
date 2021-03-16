package com.mcaim.worldmanager.commands.subcommands;

import com.mcaim.core.models.Permission;
import com.mcaim.core.util.C;
import com.mcaim.core.util.Command;
import com.mcaim.worldmanager.util.WorldHelper;
import org.bukkit.command.CommandSender;

public class WorldDelete extends Command {
    public WorldDelete() {
        super(true, Permission.OP, "delete");
        registerSubCommand(1, "<name>");
        setErrorDisplay("<command> <name>");
    }

    @Override
    protected void onExecute(CommandSender sender, String alias, String[] args) {
        String worldName = args[1];

        if (!WorldHelper.doesExist(worldName)) {
            C.msg(sender, C.FAIL + "That world does not exist!");
            return;
        }

        if (WorldHelper.canBeLoaded(worldName)) {
            C.msg(sender, C.FAIL + "That world already exists, but isn't loaded! Try typing /World load " + worldName + " if you want to delete it!");
            return;
        }

        WorldHelper.deleteWorld(worldName);
        C.msg(sender, C.SUCCESS + "Successfully deleted world &f" + worldName + "&7!");
    }
}
