package mc.alk.battlebukkitlib.handlers;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public interface ISignHandler {

    void sendLines(Player player, Sign sign, String[] lines);

    Block getAttachedBlock(Sign sign);

    public static final ISignHandler NULL_HANDLER = new ISignHandler() {

        @Override
        public void sendLines(Player player, Sign sign, String[] lines) {
            // do nothing
        }

        @Override
        public Block getAttachedBlock(Sign sign) {
            return null;
        }
    };

}
