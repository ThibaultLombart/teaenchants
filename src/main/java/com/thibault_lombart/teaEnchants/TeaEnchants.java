package com.thibault_lombart.teaEnchants;

import com.thibault_lombart.teaEnchants.Commands.EnchantmentCommand;
import com.thibault_lombart.teaEnchants.Commands.GiveEnchantedBookCommand;
import com.thibault_lombart.teaEnchants.Commands.ReloadCommand;
import com.thibault_lombart.teaEnchants.CustomEnchants.CustomEnchants;
import com.thibault_lombart.teaEnchants.CustomEnchants.SmeltingEnchant;
import com.thibault_lombart.teaEnchants.Listeners.AnvilListener;
import com.thibault_lombart.teaEnchants.Listeners.BreakListener;
import com.thibault_lombart.teaEnchants.Listeners.EntityKillListener;
import com.thibault_lombart.teaEnchants.Listeners.GrinderListener;
import com.thibault_lombart.teaEnchants.TabCompleter.EnchantCommandTabCompletor;
import com.thibault_lombart.teaEnchants.TabCompleter.GiveEnchantedBookCommandTabCompletor;
import com.thibault_lombart.teaEnchants.Utils.InformationsFromConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TeaEnchants extends JavaPlugin {

    public static TeaEnchants plugin;

    public static TeaEnchants getInstance(){
        return TeaEnchants.plugin;
    }

    @Override
    public void onEnable() {

        TeaEnchants.plugin = this;

        saveDefaultConfig();

        reloadConfig();

        String version = Bukkit.getBukkitVersion();

        if (isVersionOrHigher(version)) {
            SmeltingEnchant.setup(1);
        } else {
            SmeltingEnchant.setup(0);
        }

        logoDisplay();
        System.out.println("Enabling TeaEnchants");


        // Add Commands
        this.getCommand("TeaEnchants").setExecutor(new EnchantmentCommand());
        this.getCommand("TeaEnchantsBook").setExecutor(new GiveEnchantedBookCommand());
        this.getCommand("TeaEnchantsReload").setExecutor(new ReloadCommand());

        // Add Tab completor
        this.getCommand("TeaEnchants").setTabCompleter(new EnchantCommandTabCompletor());
        this.getCommand("TeaEnchantsBook").setTabCompleter(new GiveEnchantedBookCommandTabCompletor());

        // Add listeners
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new EntityKillListener(), this);
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new GrinderListener(), this);
    }

    @Override
    public void onDisable() {

        saveConfig();
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
        System.out.println();
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

        if (majorVersion > targetMajorVersion) {
            return true;
        } else if (majorVersion == targetMajorVersion) {
            return minorVersion >= targetMinorVersion;
        } else {
            return false;
        }
    }

    public void reloadConfig() {
        YamlConfiguration config = null;
        File configFile = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        InformationsFromConfig.setup(config);
        CustomEnchants.setup();
    }

}
