package mc.alk.battlebukkitlib.compat.v1_7_R2;

import mc.alk.battlebukkitlib.handlers.ISignHandler;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.7.5-R0.1
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        player.sendSignChange(sign.getLocation(), lines);
    }

    @Override
    public Block getAttachedBlock(Sign sign) {
        org.bukkit.material.Sign signMaterial = (org.bukkit.material.Sign) sign.getData();
        Block attachedBlock = sign.getBlock().getRelative(signMaterial.getAttachedFace());
        return attachedBlock;
    }
}
