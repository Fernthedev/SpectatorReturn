package io.github.fernthedev.spectatortest;

import com.github.fernthedev.gson.GsonConfig;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.UUID;

public class ConfigManager {

    @Getter
    private GsonConfig<SpectatorPlayerListFile> playerFileConfig;

    private GsonConfig<ConfigValues> configValues;

    public ConfigManager(File folder) {
        playerFileConfig = new GsonConfig<>(new SpectatorPlayerListFile(), new File(folder, "playerdata.fern.json"));
        configValues = new GsonConfig<>(new ConfigValues(), new File(folder, "config.fern.json"));

    }

    public ConfigValues getConfigValues() {
        configValues.load();
        return configValues.getConfigData();
    }


    @Nullable
    public SpectatorPlayerData getPlayer(@NonNull UUID uuid) {
        playerFileConfig.load();
        for (UUID uuid1 : playerFileConfig.getConfigData().getPlayerDataMap().keySet()) {
            if(uuid == uuid1) {
                return playerFileConfig.getConfigData().getPlayerDataMap().get(uuid1);
            }
        }
        return null;
    }

    public void removePlayer(@NonNull UUID uuid) {
        playerFileConfig.load();
        for (UUID uuid1 : playerFileConfig.getConfigData().getPlayerDataMap().keySet()) {
            if(uuid == uuid1) {
                playerFileConfig.getConfigData().getPlayerDataMap().remove(uuid1);
                playerFileConfig.save();
                return;
            }
        }
    }

    public SpectatorPlayerData addPlayer(@NonNull Player player) {
        SpectatorPlayerData spectatorPlayerData = new SpectatorPlayerData(player.getLocation(), player.getGameMode());

        playerFileConfig.load();
        playerFileConfig.getConfigData().getPlayerDataMap().put(player.getUniqueId(), spectatorPlayerData);
        playerFileConfig.save();

        return spectatorPlayerData;
    }

}
