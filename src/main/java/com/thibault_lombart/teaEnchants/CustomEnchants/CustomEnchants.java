package com.thibault_lombart.teaEnchants.CustomEnchants;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class CustomEnchants {

    public static final Map<String,List<Material>> dictionary = new HashMap<String,List<Material>>();

    private static List<String> listEnchants = Arrays.asList(CustomEnchants.MAGNETISM, CustomEnchants.SMELTING, CustomEnchants.REPLANTING);

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


    // Check the enchantment effect
    public static void enchantmentEffect(ItemStack item){
        ItemMeta meta = item.getItemMeta();

        if(meta == null) return;

        if(meta.getEnchants().isEmpty()) {
            meta.addEnchant(Enchantment.SOUL_SPEED, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        } else {
            if(meta.getEnchants().containsKey(Enchantment.SOUL_SPEED) && meta.getEnchants().size() > 1) {
                meta.removeEnchant(Enchantment.SOUL_SPEED);
                meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
            }
        }

        return;
    }


    public static void giveEnchantedBook(Player player, String enchantment){
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);

        ItemMeta meta = enchantedBook.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + enchantment);
        meta.setLore(lore);

        enchantedBook.setItemMeta(meta);

        if(player.getInventory().firstEmpty() != -1){
            player.getInventory().addItem(enchantedBook);
            player.sendMessage("Tu as reçu un livre enchanté avec " + enchantment + ".");
        } else {
            Location playerLocation = player.getLocation();
            player.getWorld().dropItemNaturally(playerLocation, enchantedBook);
            player.sendMessage("Ton inventaire est plein ! Le livre a été déposé par terre.");
        }
    }

    public static String findEnchantmentIgnoreCase(String input) {

        input = input.replaceFirst("^§7","");

        for (String enchantment : listEnchants) {
            if (enchantment.equalsIgnoreCase(input)) {
                return enchantment;
            }
        }
        return null;
    }

}
