package io.github.fernthedev.spectatortoggle;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Data
public class SpectatorPlayerListFile {

    private Map<UUID, SpectatorPlayerData> playerDataMap = new HashMap<>();

}
