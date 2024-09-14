package com.thibault_lombart.teaEnchants.CustomEnchants;

import com.thibault_lombart.teaEnchants.Utils.InformationsFromConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TreeCutterEnchant {

    public static int numberOfLogCut = 5;

    public static List<ItemStack> handleTreeCutter(Block block, List<ItemStack> drops) {

        if(!InformationsFromConfig.isTreeCutterActivated()) return drops;

        Block blockUp = block.getRelative(0,1,0);
        Block blockDown = block.getRelative(0,-1,0);
        int count = 1;

        while (count < numberOfLogCut && (blockUp != null || blockDown != null)){
            if(blockUp != null){
                if(blockUp.getType() == Material.OAK_LOG || blockUp.getType() == Material.BIRCH_LOG || blockUp.getType() == Material.SPRUCE_LOG || blockUp.getType() == Material.JUNGLE_LOG || blockUp.getType() == Material.ACACIA_LOG || blockUp.getType() == Material.DARK_OAK_LOG){
                    drops.addAll(blockUp.getDrops());
                    blockUp.setType(Material.AIR);
                    blockUp = blockUp.getRelative(0,1,0);
                    count++;
                } else {
                    blockUp = null;
                }
            }

            if(blockDown != null){
                if(blockDown.getType() == Material.OAK_LOG || blockDown.getType() == Material.BIRCH_LOG || blockDown.getType() == Material.SPRUCE_LOG || blockDown.getType() == Material.JUNGLE_LOG || blockDown.getType() == Material.ACACIA_LOG || blockDown.getType() == Material.DARK_OAK_LOG){
                    drops.addAll(blockDown.getDrops());
                    blockDown.setType(Material.AIR);
                    blockDown = blockDown.getRelative(0,-1,0);
                    count++;
                } else {
                    blockDown = null;
                }
            }
        }

        return drops;
    }

}
