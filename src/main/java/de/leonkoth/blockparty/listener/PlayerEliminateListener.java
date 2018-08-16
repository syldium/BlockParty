package de.leonkoth.blockparty.listener;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.arena.Arena;
import de.leonkoth.blockparty.arena.ArenaState;
import de.leonkoth.blockparty.event.PlayerEliminateEvent;
import de.leonkoth.blockparty.locale.Locale;
import de.leonkoth.blockparty.player.PlayerInfo;
import de.leonkoth.blockparty.player.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerEliminateListener implements Listener {

    private BlockParty blockParty;

    public PlayerEliminateListener(BlockParty blockParty) {
        this.blockParty = blockParty;

        Bukkit.getPluginManager().registerEvents(this, blockParty.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEliminate(PlayerEliminateEvent event) {

        Arena arena = event.getArena();
        Player player = event.getPlayer();
        PlayerInfo playerInfo = event.getPlayerInfo();

        if(arena.isEnableLightnings()) {
            player.getWorld().strikeLightningEffect(player.getLocation());
        }

        playerInfo.setPlayerState(PlayerState.SPECTATING);
        player.teleport(arena.getLobbySpawn());
        arena.broadcast(Locale.GAME_ELIMINATED, false, (PlayerInfo) null, "%PLAYER%", playerInfo.getName());

        if (arena.getArenaState() == ArenaState.INGAME || arena.getArenaState() == ArenaState.WINNERPHASE) {
            arena.getPhaseHandler().getGamePhase().eliminate(playerInfo);
        }
    }

}
