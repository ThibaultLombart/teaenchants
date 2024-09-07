package com.thibault_lombart.teaEnchants.CustomEnchants;

import com.thibault_lombart.teaEnchants.TeaEnchants;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ReplantingEnchant {

    private static final Set<Material> PLANT_BLOCKS = new HashSet<>(Arrays.asList(
            Material.WHEAT,
            Material.CARROTS,
            Material.POTATOES,
            Material.BEETROOTS,
            Material.NETHER_WART,
            Material.COCOA
    ));

    public static boolean isBreakablePlant(Block block) {
        return PLANT_BLOCKS.contains(block.getType());
    }

    public static List<ItemStack> handleReplanting(List<ItemStack> drops, Block block){
        List<ItemStack> newDrops = new ArrayList<ItemStack>();

        Material blockType = block.getType();
        Material seedMaterial = null;

        switch (blockType) {
            case WHEAT:
                seedMaterial = Material.WHEAT_SEEDS;
                break;
            case CARROTS:
                seedMaterial = Material.CARROT;
                break;
            case NETHER_WART:
                seedMaterial = Material.NETHER_WART;
                break;
            case POTATOES:
                seedMaterial = Material.POTATO;
                break;
            case BEETROOTS:
                seedMaterial = Material.BEETROOT_SEEDS;
                break;
            case COCOA:
                seedMaterial = Material.COCOA;
                break;
            default:
                return drops;
        }

        boolean seedUsed = false;
        for (ItemStack drop : drops) {
            if (!seedUsed && drop.getType() == seedMaterial && drop.getAmount() > 0) {
                drop.setAmount(drop.getAmount() - 1);
                seedUsed = true;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(blockType);
                        block.getState().update(true);
                    }
                }.runTaskLater(TeaEnchants.getInstance(), 1L);

            }

            if (drop.getAmount() > 0) {
                newDrops.add(drop);
            }
        }

        return newDrops;
    }
}
