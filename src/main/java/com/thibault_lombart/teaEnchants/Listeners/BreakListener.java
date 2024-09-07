package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import com.thibault_lombart.teaEnchants.CustomEnchants.MagnetismEnchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        List<ItemStack> drops = (List<ItemStack>) event.getBlock().getDrops(item);

        if(!item.hasItemMeta())
            return;

        System.out.println("TEST 1 PUTAIN");
        System.out.println(CustomEnchants.hasEnchantLore(item, CustomEnchants.MAGNETISM));
        System.out.println(item.getItemMeta().getLore().toString());

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.MAGNETISM)){

            System.out.println("MAGNETISM");

            event.setDropItems(false);

            MagnetismEnchant.handleMagnetism(player, drops);
        }
    }


}
