package io.github.fernthedev.spectatortoggle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.bukkit.GameMode;

@AllArgsConstructor
@Data
@Getter
public class SpectatorPlayerData {

    private GLocation lastLocation;
    private GameMode lastGameMode;

}
