package com.thibault_lombart.teaEnchants.CustomEnchants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CombineEnchants {

    public static ItemStack combineBooks(ItemStack book1, ItemStack book2) {

        if (book1.getType() != Material.ENCHANTED_BOOK || book2.getType() != Material.ENCHANTED_BOOK) {
            throw new IllegalArgumentException("They are not books.");
        }

        ItemStack combinedBook = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta combinedMeta = (EnchantmentStorageMeta) combinedBook.getItemMeta();

        EnchantmentStorageMeta meta1 = (EnchantmentStorageMeta) book1.getItemMeta();
        meta1.getStoredEnchants().forEach((enchantment, level) -> {
            combinedMeta.addStoredEnchant(enchantment, level, true);
        });

        EnchantmentStorageMeta meta2 = (EnchantmentStorageMeta) book2.getItemMeta();
        meta2.getStoredEnchants().forEach((enchantment, level) -> {
            if (combinedMeta.hasStoredEnchant(enchantment)) {
                int existingLevel = combinedMeta.getStoredEnchantLevel(enchantment);
                combinedMeta.addStoredEnchant(enchantment, Math.max(existingLevel, level), true);
            } else {
                combinedMeta.addStoredEnchant(enchantment, level, true);
            }
        });

        ItemMeta metaLore1 = book1.getItemMeta();
        ItemMeta metaLore2 = book2.getItemMeta();
        List<String> lore1 = metaLore1.hasLore() ? metaLore1.getLore() : null;
        List<String> lore2 = metaLore2.hasLore() ? metaLore2.getLore() : null;

        if (lore1 != null) {
            combinedMeta.setLore(lore1);
        }
        if (lore2 != null) {
            if (combinedMeta.hasLore()) {
                List<String> lore3 = combinedMeta.getLore();
                for (int i = 0; i < lore2.size(); i++) {
                    if(!lore3.contains(lore2.get(i))) {
                        lore3.add(lore2.get(i));
                    }
                }
                combinedMeta.setLore(lore3);
            } else {
                combinedMeta.setLore(lore2);
            }
        }

        combinedBook.setItemMeta(combinedMeta);

        return combinedBook;
    }

}
