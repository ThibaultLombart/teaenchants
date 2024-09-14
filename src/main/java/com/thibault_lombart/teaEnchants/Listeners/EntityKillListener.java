package com.thibault_lombart.teaEnchants.Listeners;

import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import com.thibault_lombart.teaEnchants.CustomEnchants.MagnetismEnchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EntityKillListener implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {

        if (event.getEntity().getKiller() != null) {
            Player player = (Player) event.getEntity().getKiller();


            assert player != null;

            ItemStack item = player.getInventory().getItemInMainHand();

            if(!item.hasItemMeta())
                return;

            if (CustomEnchants.hasEnchantLore(item, CustomEnchants.getMagnetism())){

                List<ItemStack> drops = (List<ItemStack>) event.getDrops();

                MagnetismEnchant.handleMagnetism(player, drops);

                event.getDrops().clear();
            }

        }

    }
}
