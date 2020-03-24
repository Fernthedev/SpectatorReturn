package io.github.fernthedev.spectatortoggle;

import com.github.fernthedev.fernapi.universal.Universal;
import com.github.fernthedev.fernapi.universal.api.CommandSender;
import com.github.fernthedev.fernapi.universal.api.IFConsole;
import com.github.fernthedev.fernapi.universal.api.IFPlayer;
import com.github.fernthedev.fernapi.universal.api.UniversalCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpectateCommand extends UniversalCommand {

    /**
     * Construct a new command.
     *
     */
    public SpectateCommand() {
        super("spec", Constants.USE_COMMAND, "spectate", "noclip", "sp");
    }

    /**
     * Called when executing the command
     *
     * @param sender The source
     * @param args   The arguments provided
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        // Player is acting on themselves
        if(args.length == 0) {
            if(sender instanceof IFConsole) {
                sendMessage(sender, getConfigValues().getNOT_PLAYER());
                return;
            }

            Player player = Universal.getMethods().convertFPlayerToPlayer((IFPlayer<Player>) sender);

            spectate(sender, player);
        } else {
            // Player is acting on other

            if(sender.hasPermission(Constants.USE_COMMAND_OTHERS)) {
                sendMessage(sender, getConfigValues().getNO_PERM_OTHERS());
                return;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null) {
                sendMessage(sender, getConfigValues().getUNABLE_TO_FIND_PLAYER().replace("${player}", args[0]));
                return;
            }

            spectate(sender, player);
        }
    }

    public void spectate(CommandSender sender, Player player) {
        SpectatorPlayerData playerData = SpectatorToggleMain.getConfigManager().getPlayer(player.getUniqueId());

        if(playerData == null) {
            SpectatorToggleMain.getConfigManager().addPlayer(player);
            player.setGameMode(GameMode.SPECTATOR);
            sendMessage(sender, getConfigValues().getENTER_SPECTATOR_MODE());
        } else {
            player.teleport(playerData.getLastLocation().toLocation());
            player.setGameMode(playerData.getLastGameMode());
            String mode = playerData.getLastGameMode().name();

            sendMessage(sender, getConfigValues().getRETURN_TO_ORIGINAL_STATE().replace("${mode}", mode));

            SpectatorToggleMain.getConfigManager().removePlayer(player.getUniqueId());
        }
    }

    /**
     * Is used for tab completion. DO NOT RETURN NULL, INSTEAD RETURN AN EMPTY LIST
     *
     * @param source      The command source
     * @param currentArgs The arguments currently provided
     * @return The arguments suggested. CANNOT BE NULL, INSTEAD RETURN AN EMPTY LIST
     */
    @Override
    public List<String> suggest(CommandSender source, String[] currentArgs) {

        if (!source.hasPermission(Constants.USE_COMMAND_OTHERS)) return super.suggest(source, currentArgs); // Returns an empty list if player does not have permission to use the command on others

        List<? extends Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

        return search(currentArgs[0], players.stream().map(HumanEntity::getName).collect(Collectors.toList())); // Turns player list into a list of names of players.
    }

    private ConfigValues getConfigValues() {
        return SpectatorToggleMain.getConfigManager().getConfigValues();
    }
}
