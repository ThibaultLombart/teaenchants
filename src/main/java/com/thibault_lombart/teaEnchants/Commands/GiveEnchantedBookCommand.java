package com.thibault_lombart.teaEnchants.Commands;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class GiveEnchantedBookCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length != 2) {
                player.sendMessage("Veuillez suivre le schema.");
                return false;
            }

            Player player2 = Bukkit.getPlayer(args[0]);
            if(player2 == null) {
                player.sendMessage("Veuillez mettre un pseudo correct.");
                return false;
            }

            String enchant = CustomEnchants.findEnchantmentIgnoreCase(args[1]);
            if(enchant == null) {
                player.sendMessage("Veuillez choisir un nom d'enchantement.");
                return false;
            }

            CustomEnchants.giveEnchantedBook(player2, enchant);
            return true;

        }

        return false;
    }
}
