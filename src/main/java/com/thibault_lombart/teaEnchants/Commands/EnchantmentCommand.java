package com.thibault_lombart.teaEnchants.Commands;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantmentCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack item = player.getInventory().getItemInMainHand();

            if(args.length != 1) {
                player.sendMessage("Veuillez mettre un nom d'enchantement.");
                return false;
            }

            String enchant = CustomEnchants.findEnchantmentIgnoreCase(args[0]);
            if(enchant == null) {
                player.sendMessage("Veuillez choisir un nom d'enchantement.");
                return false;
            }

            if(CustomEnchants.hasEnchantLore(item, enchant)) {
                player.sendMessage("Vous avez déjà l'enchantement sur votre item.");
                return false;
            }

            if(!CustomEnchants.dictionary.get(enchant).contains(item.getType())){
                player.sendMessage("Vous ne pouvez pas mettre cet enchantement sur votre item.");
                return false;
            }

            CustomEnchants.addCustomEnchantLore(item, enchant);
            CustomEnchants.enchantmentEffect(item);

            player.sendMessage("Vous avez enchanté votre item avec l'enchantement "+enchant+" !");
            return true;
        }

        return false;
    }
}
