package com.thibault_lombart.teaEnchants.CustomEnchants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmeltingEnchant {

    private static final Map<Material,Material> SMELTING_ITEMS = new HashMap<Material,Material>();

    public static void setup(int version){
        if(version == 1){
            SMELTING_ITEMS.put(Material.RAW_IRON, Material.IRON_INGOT);
            SMELTING_ITEMS.put(Material.RAW_GOLD, Material.GOLD_INGOT);
            SMELTING_ITEMS.put(Material.RAW_COPPER, Material.COPPER_INGOT);
        } else {
            SMELTING_ITEMS.put(Material.IRON_ORE, Material.IRON_INGOT);
            SMELTING_ITEMS.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        }
    }

    public static List<ItemStack> handleSmelting(List<ItemStack> drops){
        List<ItemStack> newDrops = new ArrayList<ItemStack>();

        for (ItemStack drop : drops) {
            Material type = drop.getType();
            if (SMELTING_ITEMS.containsKey(type)) {
                Material smeltedMaterial = SMELTING_ITEMS.get(type);
                int quantity = drop.getAmount();
                newDrops.add(new ItemStack(smeltedMaterial, quantity));
            } else {
                newDrops.add(drop);
            }
        }

        return newDrops;
    }

}
