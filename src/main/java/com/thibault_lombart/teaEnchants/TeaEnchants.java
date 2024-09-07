package com.thibault_lombart.teaEnchants;

import com.thibault_lombart.teaEnchants.Commands.EnchantmentCommand;
import com.thibault_lombart.teaEnchants.Listeners.BreakListener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeaEnchants extends JavaPlugin {

    @Override
    public void onEnable() {

        logoDisplay();
        System.out.println("Enabling TeaEnchants");


        // Add Commands
        this.getCommand("TeaEnchants").setExecutor(new EnchantmentCommand());

        // Add listeners
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
    }

    @Override
    public void onDisable() {

        logoDisplay();
        System.out.println("Disabling TeaEnchants");

    }

    public void logoDisplay() {
        System.out.println("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()");
        System.out.println();
        System.out.println("  _______            ______               _                    _   ");
        System.out.println(" |__   __|          |  ____|             | |                  | |  ");
        System.out.println("    | |  ___   __ _ | |__    _ __    ___ | |__    __ _  _ __  | |_ ");
        System.out.println("    | | / _ \\ / _` ||  __|  | '_ \\  / __|| '_ \\  / _` || '_ \\ | __|");
        System.out.println("    | ||  __/| (_| || |____ | | | || (__ | | | || (_| || | | || |_ ");
        System.out.println("    |_| \\___| \\__,_||______||_| |_| \\___||_| |_| \\__,_||_| |_| \\__|");
        System.out.println("");
        System.out.println("by Thybax                                                          ");
        System.out.println("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()");
    }
}
