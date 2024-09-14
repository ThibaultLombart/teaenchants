package com.thibault_lombart.teaEnchants.TabCompleter;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.stream.Collectors;

public class EnchantCommandTabCompletor implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return CustomEnchants.getListEnchants().stream().filter(s -> s.startsWith(args[0])).map(s -> s.replaceAll(" ", "")).collect(Collectors.toList());
        }
        return null;
    }
}
