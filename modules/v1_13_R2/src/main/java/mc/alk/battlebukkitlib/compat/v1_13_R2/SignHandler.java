package mc.alk.battlebukkitlib.compat.v1_13_R2;

import mc.alk.battlebukkitlib.handlers.ISignHandler;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.13.2
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        player.sendSignChange(sign.getLocation(), lines);
    }

    @Override
    public Block getAttachedBlock(Sign sign) {
        BlockData blockData = sign.getBlockData();
        if (!(blockData instanceof Directional))
            return null;

        Directional directional = (Directional) blockData;
        return sign.getBlock().getRelative(directional.getFacing().getOppositeFace());
    }
}
