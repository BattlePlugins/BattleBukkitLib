package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.SignHandlerFactory;
import mc.alk.battlebukkitlib.handlers.ISignHandler;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class SignUtil {

    static ISignHandler handler = SignHandlerFactory.getNewInstance();

    public static void sendLines(Player player, Sign sign, String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 15) {
                lines[i] = lines[i].substring(0, 15);
            }
        }
        handler.sendLines(player, sign, lines);
    }

    public static Block getAttatchedBlock(Sign sign) {
        return handler.getAttachedBlock(sign);
    }
}
