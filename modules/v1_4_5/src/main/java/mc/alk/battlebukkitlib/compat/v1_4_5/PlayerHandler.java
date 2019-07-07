package mc.alk.battlebukkitlib.compat.v1_4_5;

import mc.alk.battlebukkitlib.handlers.IPlayerHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerHandler implements IPlayerHandler {

    @Override
    public void setHealth(Player player, double health) {
        player.setHealth((int) health);
    }

    @Override
    public double getHealth(Player player) {
        return player.getHealth();
    }

    @Override
    public double getMaxHealth(Player player) {
        return player.getMaxHealth();
    }

    @Override
    public Object getScoreboard(Player player) {
        return null;
    }

    @Override
    public void setScoreboard(Player player, Object scoreboard) {
        /* do nothing */
    }

    @Override
    public UUID getID(OfflinePlayer player) {
        return new UUID(0, player.getName().hashCode());
    }

    @Override
    public void sendTitle(Player player, String title, String subtitle, int time) {
        /* do nothing */
    }

    @Override
    public void sendActionBarText(Player player, String actionBarText) {
        /* do nothing */
    }
}
