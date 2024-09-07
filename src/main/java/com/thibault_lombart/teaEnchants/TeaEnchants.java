package com.thibault_lombart.teaEnchants;

import com.thibault_lombart.teaEnchants.Commands.EnchantmentCommand;
import com.thibault_lombart.teaEnchants.CustomEnchants.SmeltingEnchant;
import com.thibault_lombart.teaEnchants.Listeners.BreakListener;
import com.thibault_lombart.teaEnchants.Listeners.EntityKillListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeaEnchants extends JavaPlugin {

    public static TeaEnchants plugin;

    public static TeaEnchants getInstance(){
        return TeaEnchants.plugin;
    }

    @Override
    public void onEnable() {

        String version = Bukkit.getBukkitVersion();

        if (isVersionOrHigher(version)) {
            SmeltingEnchant.setup(1);
        } else {
            SmeltingEnchant.setup(0);
        }

        TeaEnchants.plugin = this;

        logoDisplay();
        System.out.println("Enabling TeaEnchants");


        // Add Commands
        this.getCommand("TeaEnchants").setExecutor(new EnchantmentCommand());

        // Add listeners
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new EntityKillListener(), this);
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

    private boolean isVersionOrHigher(String version) {
        String[] versionParts = version.split("-")[0].split("\\.");
        String[] targetParts = "1.17".split("\\.");

        int majorVersion = Integer.parseInt(versionParts[0]);
        int minorVersion = Integer.parseInt(versionParts[1]);
        int targetMajorVersion = Integer.parseInt(targetParts[0]);
        int targetMinorVersion = Integer.parseInt(targetParts[1]);

        // Compare les versions
        if (majorVersion > targetMajorVersion) {
            return true;
        } else if (majorVersion == targetMajorVersion) {
            return minorVersion >= targetMinorVersion;
        } else {
            return false;
        }
    }


}
