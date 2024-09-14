package com.thibault_lombart.teaEnchants.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public class InformationsFromConfig {

    public static FileConfiguration config;

    public static void setup(FileConfiguration config){
        InformationsFromConfig.config = config;
    }

    // get names from config
    public static String getMagnetismName(){
        return InformationsFromConfig.config.getString("magnetism.name");
    }

    public static String getSmeltingName(){
        return InformationsFromConfig.config.getString("smelting.name");
    }

    public static String getReplantingName(){
        return InformationsFromConfig.config.getString("replanting.name");
    }

    public static String getTreeCutterName(){
        return InformationsFromConfig.config.getString("treecutter.name");
    }

    // get activations from config
    public static boolean isMagnetismActivated(){
        return InformationsFromConfig.config.getBoolean("magnetism.activated");
    }

    public static boolean isSmeltingActivated(){
        return InformationsFromConfig.config.getBoolean("smelting.activated");
    }

    public static boolean isReplantingActivated(){
        return InformationsFromConfig.config.getBoolean("replanting.activated");
    }

    public static boolean isTreeCutterActivated(){
        return InformationsFromConfig.config.getBoolean("treecutter.activated");
    }

}
