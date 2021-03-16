package com.mcaim.worldmanager.commands.subcommands;

import com.mcaim.core.models.Permission;
import com.mcaim.core.util.C;
import com.mcaim.core.util.Command;
import com.mcaim.core.util.Util;
import com.mcaim.worldmanager.models.CustomWorldType;
import com.mcaim.worldmanager.util.WorldHelper;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class WorldLoad extends Command {
    public WorldLoad() {
        super(true, Permission.OP, "load");
        registerSubCommand(1, "<name>");
        registerSubCommand(2, Util.enumsToStrings(CustomWorldType.class));
        setErrorDisplay("<command> <name> " + Arrays.toString(CustomWorldType.values()));
    }

    @Override
    protected void onExecute(CommandSender sender, String alias, String[] args) {
        String worldName = args[1];
        CustomWorldType worldType = CustomWorldType.valueOf(args[2].toUpperCase());

        if (WorldHelper.doesExist(worldName)) {
            C.msg(sender, C.FAIL + "That world already exists!");
            return;
        }

        if (!WorldHelper.canBeLoaded(worldName)) {
            C.msg(sender, C.FAIL + "That world can't be loaded! It doesn't exist in the directory!");
            return;
        }

        WorldHelper.createWorld(worldName, worldType);
        C.msg(sender, C.SUCCESS + "World &f" + worldName + " &7has been successfully loaded!");
    }
}
