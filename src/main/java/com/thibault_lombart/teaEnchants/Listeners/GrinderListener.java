package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GrinderListener implements Listener {

    @EventHandler
    public void onGrinder(PrepareGrindstoneEvent event) {
        GrindstoneInventory inventory = event.getInventory();

        ItemStack firstItem = inventory.getItem(0);
        ItemStack secondItem = inventory.getItem(1);

        if(firstItem == null && secondItem == null) return;

        if(firstItem == null || secondItem == null) {
            ItemStack resultItem;
            if(firstItem != null) {
                resultItem = firstItem.clone();
            } else {
                resultItem = secondItem.clone();
            }

            ItemMeta meta = resultItem.getItemMeta();

            if(!meta.getEnchants().isEmpty()) meta.removeEnchantments();

            resultItem.setItemMeta(meta);

            if(meta.hasLore()) {
                meta.setLore(null);
            }
            resultItem.setItemMeta(meta);

            event.setResult(resultItem);
        } else {

            if(firstItem.getType() != secondItem.getType()) return;

            ItemStack resultItem = firstItem.clone();
            ItemMeta meta = resultItem.getItemMeta();

            if(!meta.getEnchants().isEmpty()) meta.removeEnchantments();

            resultItem.setItemMeta(meta);

            Damageable meta1 = (Damageable) firstItem.getItemMeta();
            Damageable meta2 = (Damageable) secondItem.getItemMeta();

            int maxDurability1 = firstItem.getType().getMaxDurability();

            int durability1 = maxDurability1 - meta1.getDamage();
            int durability2 = maxDurability1 - meta2.getDamage();

            if(durability1 + durability2 > maxDurability1) {
                durability1 = maxDurability1;
            } else {
                durability1 = durability1 + durability2;
            }

            Damageable meta3 = (Damageable) resultItem.getItemMeta();
            meta3.setDamage(maxDurability1 - durability1);
            resultItem.setItemMeta(meta3);

            if(meta3.hasLore()) {
                meta3.setLore(null);
            }
            resultItem.setItemMeta(meta3);

            event.setResult(resultItem);
        }




    }
}
