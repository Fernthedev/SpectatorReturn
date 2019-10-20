package io.github.fernthedev.spectatortest;

import com.github.fernthedev.fernapi.server.spigot.FernSpigotAPI;
import com.github.fernthedev.fernapi.universal.Universal;
import lombok.Getter;
import org.bukkit.ChatColor;

public final class SpectatorToggleMain extends FernSpigotAPI {

    private static SpectatorToggleMain instance;

    public static SpectatorToggleMain getInstance() {
        return instance;
    }

    public static ConfigManager getConfigManager() {
        return instance.configManager;
    }

    @Getter
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
