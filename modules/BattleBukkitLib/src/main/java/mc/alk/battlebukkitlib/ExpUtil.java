package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.ExpHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IExpHandler;
import org.bukkit.entity.Player;

/**
 * 
 * @author Nikolai
 */
public class ExpUtil {

    private static IExpHandler handler = ExpHandlerFactory.getNewInstance();

    public static int getExpToLevel(final int level) {
        return handler.getExpToLevel(level);
    }

    public static int getExpAtLevel(final int level) {
        return handler.getExpAtLevel(level);
    }

    /**
     * Never use player.getTotalExperience(), use this method instead.
     * player.getTotalExperience() shows XP that has been spent on enchants.
     *
     * player.getExp() is the percentage toward the next level (between 0 & 1).
     */
    public static int getTotalExperience(final Player player) {
        int exp = (int) Math.round(getExpAtLevel(player.getLevel()) * player.getExp());
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    /**
     * Set the total amount of experience for a player
     *
     * @param player
     * @param exp
     */
    public static void setTotalExperience(Player player, int exp) {
        player.setTotalExperience(0);
        player.setLevel(0);
        player.setExp(0);
        if (exp > 0) {
            player.giveExp(exp);
        }
    }

    /**
     * Give experience to a player
     *
     * @param player
     * @param exp
     */
    public static void giveExperience(Player player, int exp) {
        final int currentExp = getTotalExperience(player);
        player.setTotalExperience(0);
        player.setLevel(0);
        player.setExp(0);
        final int newexp = currentExp + exp;
        if (newexp > 0) {
            player.giveExp(newexp);
        }
    }
}
