package mc.alk.util.compat.v1_6_R3;

import mc.alk.util.handlers.ISignHandler;
import net.minecraft.server.v1_6_R3.Packet130UpdateSign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.6.4-R0.1
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        Location loc = sign.getLocation();
        Packet130UpdateSign packet = new Packet130UpdateSign(loc.getBlock()
                .getX(), loc.getBlock().getY(), loc.getBlock().getZ(), lines);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
