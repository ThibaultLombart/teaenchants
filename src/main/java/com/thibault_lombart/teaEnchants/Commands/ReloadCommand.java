package com.thibault_lombart.teaEnchants.Commands;

import com.thibault_lombart.teaEnchants.TeaEnchants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TeaEnchants.getInstance().reloadConfig();
        sender.sendMessage("TeaEnchants - Reload Completed");
        return true;
    }

}
