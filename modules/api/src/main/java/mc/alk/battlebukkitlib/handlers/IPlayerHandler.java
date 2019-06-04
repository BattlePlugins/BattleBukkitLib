package mc.alk.battlebukkitlib.handlers;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IPlayerHandler {

    void setHealth(Player player, double health);

    double getHealth(Player player);

    double getMaxHealth(Player player);

    Object getScoreboard(Player player);

    void setScoreboard(Player player, Object scoreboard);

    UUID getID(OfflinePlayer player);

    public static final IPlayerHandler NULL_HANDLER = new IPlayerHandler() {

        @Override
        public void setHealth(Player player, double health) {
            /* do nothing */
        }

        @Override
        public double getHealth(Player player) {
            return 0;
        }

        @Override
        public double getMaxHealth(Player player) {
            return 0;
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
    };
}
