package io.github.fernthedev.spectatortoggle;

import com.github.fernthedev.fernapi.server.spigot.FernSpigotAPI;
import com.github.fernthedev.fernapi.universal.Universal;
import org.bukkit.ChatColor;

public final class SpectatorToggleMain extends FernSpigotAPI {

    private static SpectatorToggleMain instance;

    public static SpectatorToggleMain getInstance() {
        return instance;
    }

    public static ConfigManager getConfigManager() {
        return instance.configManager;
    }

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        super.onEnable();
        // Plugin startup logic
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        configManager = new ConfigManager(getDataFolder());
        instance = this;

//        getLogger().info("Command handler " + Universal.getCommandHandler());
//        getLogger().info("Name: " + new SpectateCommand().getName());
//        getLogger().info("Command: " + Bukkit.getPluginCommand("spec"));
        Universal.getCommandHandler().registerFernCommand(new SpectateCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        // Plugin shutdown logic
        configManager.getPlayerFileConfig().save();
        configManager = null;
        getLogger().info(ChatColor.GREEN + "Shutting down gracefully");
    }
}
