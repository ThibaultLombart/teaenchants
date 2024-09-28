package com.thibault_lombart.teaEnchants.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public class InformationsFromConfig {

    public static FileConfiguration config;

    private static String magnetismName = "Magnetism";
    private static String smeltingName = "Smelting";
    private static String replantingName = "Replanting";
    private static String treeCutterName = "Tree Cutter";
    private static boolean magnetismActivated = false;
    private static boolean smeltingActivated = false;
    private static boolean replantingActivated = false;
    private static boolean treeCutterActivated = false;
    private static int numberOfLogsToCut = 5;
    private static boolean topOrBottom = true;


    public static void setup(FileConfiguration config){
        InformationsFromConfig.config = config;
        InformationsFromConfig.magnetismName = InformationsFromConfig.config.getString("magnetism.name");
        InformationsFromConfig.smeltingName = InformationsFromConfig.config.getString("smelting.name");
        InformationsFromConfig.replantingName = InformationsFromConfig.config.getString("replanting.name");
        InformationsFromConfig.treeCutterName = InformationsFromConfig.config.getString("treecutter.name");
        InformationsFromConfig.magnetismActivated = InformationsFromConfig.config.getBoolean("magnetism.activated");
        InformationsFromConfig.smeltingActivated = InformationsFromConfig.config.getBoolean("smelting.activated");
        InformationsFromConfig.replantingActivated = InformationsFromConfig.config.getBoolean("replanting.activated");
        InformationsFromConfig.treeCutterActivated = InformationsFromConfig.config.getBoolean("treecutter.activated");
        InformationsFromConfig.numberOfLogsToCut = InformationsFromConfig.config.getInt("treecutter.number_of_logs_to_cut");
        InformationsFromConfig.topOrBottom = "top".equalsIgnoreCase(InformationsFromConfig.config.getString("lore"));
    }

    // get names from config
    public static String getMagnetismName(){
        return InformationsFromConfig.magnetismName;
    }

    public static String getSmeltingName(){
        return InformationsFromConfig.smeltingName;
    }

    public static String getReplantingName(){
        return InformationsFromConfig.replantingName;
    }

    public static String getTreeCutterName(){
        return InformationsFromConfig.treeCutterName;
    }

    // get activations from config
    public static boolean isMagnetismActivated(){
        return InformationsFromConfig.magnetismActivated;
    }

    public static boolean isSmeltingActivated(){
        return InformationsFromConfig.smeltingActivated;
    }

    public static boolean isReplantingActivated(){
        return InformationsFromConfig.replantingActivated;
    }

    public static boolean isTreeCutterActivated(){
        return InformationsFromConfig.treeCutterActivated;
    }

    public static int getNumberOfLogsToCut(){
        return InformationsFromConfig.numberOfLogsToCut;
    }

    public static boolean getTopOrBottom(){
        return InformationsFromConfig.topOrBottom;
    }

}
