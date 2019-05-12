package mc.alk.battlebukkitlib.compat.v1_8_R2;

import mc.alk.battlebukkitlib.handlers.ISignHandler;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.8.3
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        player.sendSignChange(sign.getLocation(), lines);
    }

}
