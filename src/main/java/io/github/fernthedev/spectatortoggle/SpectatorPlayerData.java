package io.github.fernthedev.spectatortest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;

@AllArgsConstructor
@Data
@Getter
public class SpectatorPlayerData {

    private Location lastLocation;
    private GameMode lastGameMode;

}
