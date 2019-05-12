package mc.alk.battlebukkitlib.compat.v1_7_R4;

import mc.alk.battlebukkitlib.handlers.ISignHandler;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.7.10
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        player.sendSignChange(sign.getLocation(), lines);
    }

}
