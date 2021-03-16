package com.mcaim.worldmanager.commands;

import com.mcaim.core.models.Permission;
import com.mcaim.core.util.Command;
import com.mcaim.worldmanager.commands.subcommands.*;
import org.bukkit.command.CommandSender;

public class WorldSuperCommand extends Command {

    public WorldSuperCommand() {
        super(true, Permission.OP, "world");
        registerSubCommand(new WorldCreate(), "Create a world", 0);
        registerSubCommand(new WorldDelete(), "Delete a world", 0);
        registerSubCommand(new WorldList(), "View worlds", 0);
        registerSubCommand(new WorldTp(), "Tp to a world", 0);
        registerSubCommand(new WorldLoad(), "Load a world", 0);
        setPlayerTabCompletion(0);
    }

    @Override
    protected void onExecute(CommandSender sender, String alias, String[] args) {
        displaySubCommands(sender, "&a");
    }
}
