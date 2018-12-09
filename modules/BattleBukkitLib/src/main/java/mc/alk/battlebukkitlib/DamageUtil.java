package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.DamageHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IDamageHandler;

import org.bukkit.entity.Player;

/**
 * Created for VirtualPlayers.
 */
public class DamageUtil {
    
    private static IDamageHandler handler = DamageHandlerFactory.getNewInstance();
    
    public static void damageEntity(Player player, double dmg) {
        handler.damageEntity(player, dmg);
    }

}
