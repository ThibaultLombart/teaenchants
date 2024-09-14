package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.*;
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

        event.setDropItems(false);

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.getTreecutter())){
            drops = TreeCutterEnchant.handleTreeCutter(event.getBlock(), drops);
        }

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.getSmelting())){
            drops = SmeltingEnchant.handleSmelting(drops);
        }

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.getReplanting()) && ReplantingEnchant.isBreakablePlant(event.getBlock())){
            drops = ReplantingEnchant.handleReplanting(drops,event.getBlock());
        }

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.getMagnetism())){
            MagnetismEnchant.handleMagnetism(player, drops);
        } else {
            for (ItemStack drop : drops) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
        }
    }


}
