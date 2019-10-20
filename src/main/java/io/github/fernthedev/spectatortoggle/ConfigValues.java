package io.github.fernthedev.spectatortest;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class ConfigValues {

    private String NOT_PLAYER = "&cYou are not a player";
    private String NO_PERM_OTHERS = "&cYou do not have permission to switch others to spectator mode.";
    private String UNABLE_TO_FIND_PLAYER = "&cUnable to find player ${player}";

    private String ENTER_SPECTATOR_MODE = "&aSwitching to spectator mode.";
    private String RETURN_TO_ORIGINAL_STATE = "&aSwitching back to ${mode} mode.";

//    private String ENTER_SPECTATOR_MODE_OTHER = "&aSwitching ${player} to spectator mode.";
//    private String RETURN_TO_ORIGINAL_STATE_OTHER = "&aSwitching ${player} back to ${mode} mode.";

}
