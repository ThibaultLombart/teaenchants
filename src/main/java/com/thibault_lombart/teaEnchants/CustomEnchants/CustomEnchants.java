package com.thibault_lombart.teaEnchants.CustomEnchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEnchants {

    public static final Map<String,List<Material>> dictionary = new HashMap<String,List<Material>>();

    public static final String MAGNETISM = "Magnetism";
    public static final List<Material> MAGNETISM_ITEMS_ALLOWED = List.of(
            Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
            Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE,
            Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
            Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL,
            Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE
    );

    public static final String SMELTING = "Smelting";
    public static final List<Material> SMELTING_ITEMS_ALLOWED = List.of(
            Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE
    );

    public static final String REPLANTING = "Replanting";
    public static final List<Material> REPLANTING_ITEMS_ALLOWED = List.of(
            Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE
    );

    static {
        dictionary.put(MAGNETISM, MAGNETISM_ITEMS_ALLOWED);
        dictionary.put(SMELTING, SMELTING_ITEMS_ALLOWED);
        dictionary.put(REPLANTING, REPLANTING_ITEMS_ALLOWED);
    }

    // Check EnchantLore
    public static boolean hasEnchantLore(ItemStack item, String enchant) {
        if(item == null) return false;

        if(item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            if(meta == null) return false;

            List<String> lore = meta.getLore();

            if(lore == null) return false;

            return lore.contains(ChatColor.GRAY + enchant);
        }

        return false;
    }

    // Add Custom Enchantment
    public static void addCustomEnchantLore(ItemStack item, String enchant){
        ItemMeta meta = item.getItemMeta();

        if(hasEnchantLore(item, enchant)) return;

        if (meta != null) {

            List<String> lore = meta.getLore();
            if (lore == null) {
                lore = new ArrayList<>();
            }

            lore.add(ChatColor.GRAY + enchant);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

    }

    // Remove Custom Enchantment
    public static void removeCustomEnchantLore(ItemStack item, String enchant){

        if(!hasEnchantLore(item,enchant)) return;

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.remove(enchant);
        meta.setLore(lore);
        item.setItemMeta(meta);


    }

}
