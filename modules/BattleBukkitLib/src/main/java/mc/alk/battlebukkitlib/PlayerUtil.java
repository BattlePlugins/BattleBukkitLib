package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.PlayerHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IPlayerHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtil {

    static IPlayerHandler handler = PlayerHandlerFactory.getNewInstance();

    public static void setHealth(Player player, double health) {
        handler.setHealth(player, health);
    }

    public static double getHealth(Player player) {
        return handler.getHealth(player);
    }

    public static double getMaxHealth(Player player) {
        return handler.getMaxHealth(player);
    }

    public static Object getScoreboard(Player player) {
        return handler.getScoreboard(player);
    }

    public static void setScoreboard(Player player, Object scoreboard) {
        handler.setScoreboard(player, scoreboard);
    }

    public static UUID getID(OfflinePlayer player) {
        return handler.getID(player);
    }

    public static void sendTitle(Player player, String title, String subtitle, int time) {
        handler.sendTitle(player, title, subtitle, time);
    }

    public static void sendActionBarText(Player player, String actionBarText) {
        handler.sendActionBarText(player, actionBarText);
    }
}
