package io.github.fernthedev.spectatortoggle;

import com.github.fernthedev.config.gson.GsonConfig;
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
        registerValues(folder);
        configValues = new GsonConfig<>(new ConfigValues(), new File(folder, "config.fern.json"));

    }

    private void registerValues(File folder) {
        playerFileConfig = new GsonConfig<>(new SpectatorPlayerListFile(), new File(folder, "playerdata.fern.json"));
    }

    public ConfigValues getConfigValues() {
        configValues.load();
        return configValues.getConfigData();
    }


    @Nullable
    public SpectatorPlayerData getPlayer(@NonNull UUID uuid) {
        if(playerFileConfig == null) registerValues(SpectatorToggleMain.getInstance().getDataFolder());

        playerFileConfig.load();

        if(playerFileConfig.getConfigData().getPlayerDataMap() == null) {
            registerValues(SpectatorToggleMain.getInstance().getDataFolder());
            playerFileConfig.save();
        }

//        System.out.println(playerFileConfig.getConfigData().getPlayerDataMap());

        for (UUID uuid1 : playerFileConfig.getConfigData().getPlayerDataMap().keySet()) {
//            System.out.println("Checking " + uuid + " uuid and uuid1" + uuid1 + " and data " + playerFileConfig.getConfigData().getPlayerDataMap().get(uuid1));
            if(uuid.equals(uuid1)) {
                return playerFileConfig.getConfigData().getPlayerDataMap().get(uuid1);
            }
        }
        return null;
    }

    public void removePlayer(@NonNull UUID uuid) {
        playerFileConfig.load();
        for (UUID uuid1 : playerFileConfig.getConfigData().getPlayerDataMap().keySet()) {
            if(uuid.equals(uuid1)) {
                playerFileConfig.getConfigData().getPlayerDataMap().remove(uuid1);
                playerFileConfig.save();
                return;
            }
        }
    }

    public SpectatorPlayerData addPlayer(@NonNull Player player) {
        SpectatorPlayerData spectatorPlayerData = new SpectatorPlayerData(new JSONLocationWrapper(player.getLocation()), player.getGameMode());

        playerFileConfig.load();
        playerFileConfig.getConfigData().getPlayerDataMap().put(player.getUniqueId(), spectatorPlayerData);
        playerFileConfig.save();

        return spectatorPlayerData;
    }

}
