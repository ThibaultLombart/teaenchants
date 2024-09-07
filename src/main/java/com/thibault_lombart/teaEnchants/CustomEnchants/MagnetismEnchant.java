package com.thibault_lombart.teaEnchants.CustomEnchants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class MagnetismEnchant {

    public static void handleMagnetism(Player player, List<ItemStack> drops) {

        System.out.println("Magnetism Handled");

        for (ItemStack drop : drops) {
            HashMap<Integer, ItemStack> remaining = player.getInventory().addItem(drop);

            if (!remaining.isEmpty()) {
                for (ItemStack leftover : remaining.values()) {
                    player.getWorld().dropItemNaturally(player.getLocation(), leftover);
                }
            }
        }
    }


}
