package org.sithbot.config;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public final class ConfigManager {
    public void setConfig() {
        YamlFile botConfig = new YamlFile("config/config.yml");
        //Check if a config exist if not make one, if so load it
        if (!botConfig.exists()) {
            botConfig.addDefault("Global.Bot-Token", "Put-Your-Token-Here");
            botConfig.addDefault("Global.CleverBot-Token", "Put-Your-CleverBot-Token");
            botConfig.addDefault("Global.Youtube-Token", "Put-Your-Youtube-Token");
            botConfig.addDefault("Perms.Global", "292484423658766346");
            botConfig.addDefault("Perms.Guilds.212738721596833794", "216948392452947969");
            botConfig.options().copyDefaults(true);
            System.out.println("Config was missing, creating automatically");
            System.out.println("Please close this and put your tokens in the config.yml");
            try {
                botConfig.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (true) {
            }
        } else {
            try {
                botConfig.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!botConfig.isSet("Global.Youtube-Token")) {
                botConfig.set("Global.Youtube-Token", "Put-Your-Youtube-Token");
                System.out.println("Found Missing api key field, Amending");
            }
            if (!botConfig.isSet("Global.Bot-Token")) {
                botConfig.set("Global.Bot-Token", "Put-Your-Token-Here");
                System.out.println("Found missing Bot Token field in Config.yml, Amending");
            }
            if (!botConfig.isSet("Global.CleverBot-Token")) {
                botConfig.set("Global.CleverBot-Token", "Put-Your-CleverBot-Token");
                System.out.println("Found missing Clever Bot Token field in Config.yml, Amending");
            }
            if (!botConfig.isSet("Perms.Global")) {
                botConfig.set("Perms.Global", "292484423658766346");
                System.out.println("Found missing Perms field in Config.yml, Amending");
            }
            if (!botConfig.isSet("Perms.Guilds.212738721596833794")) {
                botConfig.set("Perms.Guilds.212738721596833794", "");
                System.out.println("Found missing Perms field in Config.yml, Amending");
            }
            try {
                botConfig.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Config loaded!");
        }
    }

    public YamlFile accessConfig() {
        YamlFile botConfig = new YamlFile("config/config.yml");
        try {
            botConfig.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return botConfig;
    }
}