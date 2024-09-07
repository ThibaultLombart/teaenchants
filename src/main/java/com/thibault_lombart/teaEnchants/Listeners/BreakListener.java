package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import com.thibault_lombart.teaEnchants.CustomEnchants.MagnetismEnchant;
import com.thibault_lombart.teaEnchants.CustomEnchants.SmeltingEnchant;
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

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.SMELTING)){
            drops = SmeltingEnchant.handleSmelting(drops);
        }

        if (CustomEnchants.hasEnchantLore(item, CustomEnchants.MAGNETISM)){

            MagnetismEnchant.handleMagnetism(player, drops);
        } else {
            for (ItemStack drop : drops) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
        }
    }


}
