package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.CombineEnchants;
import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepareEnchantedBookAnvil(PrepareAnvilEvent event) {
        AnvilInventory anvilInventory = event.getInventory();
        ItemStack firstItem = anvilInventory.getItem(0);
        ItemStack secondItem = anvilInventory.getItem(1);

        if(firstItem != null && secondItem != null && firstItem.getType() == Material.ENCHANTED_BOOK && secondItem.getType() == Material.ENCHANTED_BOOK) {
            event.setResult(CombineEnchants.combineBooks(firstItem, secondItem));
        }
    }

    @EventHandler
    public void onPrepareToolBookAnvil(PrepareAnvilEvent event) {
        AnvilInventory anvilInventory = event.getInventory();
        ItemStack firstItem = anvilInventory.getItem(0);
        ItemStack secondItem = anvilInventory.getItem(1);

        if(firstItem != null && secondItem != null && (firstItem.getType() == secondItem.getType() || secondItem.getType() == Material.ENCHANTED_BOOK)) {
            ItemStack resultItem = firstItem.clone();

            Map<Enchantment, Integer> enchants = secondItem.getEnchantments();

            for (Map.Entry<Enchantment, Integer> enchant : enchants.entrySet()) {
                Enchantment enchantment = enchant.getKey();
                int level = enchant.getValue();
                if (enchantment.canEnchantItem(resultItem)) {
                    if (resultItem.containsEnchantment(enchantment)) {
                        int currentLevel = resultItem.getEnchantmentLevel(enchantment);
                        resultItem.addEnchantment(enchantment, Math.max(currentLevel, level));
                    } else {
                        resultItem.addEnchantment(enchantment, level);
                    }
                }
            }

            ItemMeta meta = secondItem.getItemMeta();
            List<String> lore = meta.getLore();

            if(lore != null) {
                for (String s : lore) {
                    String enchant = CustomEnchants.findEnchantmentIgnoreCase(s);
                    if(enchant != null && CustomEnchants.dictionary.get(enchant).contains(resultItem.getType())) {
                        CustomEnchants.addCustomEnchantLore(resultItem, enchant);
                        CustomEnchants.enchantmentEffect(resultItem);
                    }
                }
            }

            if(firstItem.getType() == secondItem.getType() && firstItem.getItemMeta() instanceof Damageable && secondItem.getItemMeta() instanceof Damageable){
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
            }

            event.setResult(resultItem);

        }
    }



}
