package mc.alk.battlebukkitlib.compat.v1_7_R3;

import mc.alk.battlebukkitlib.handlers.ISignHandler;
import net.minecraft.server.v1_7_R3.PacketPlayOutUpdateSign;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.7.9
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        Location loc = sign.getLocation();
        PacketPlayOutUpdateSign packet = new PacketPlayOutUpdateSign(loc
                .getBlock().getX(), loc.getBlock().getY(), loc.getBlock()
                .getZ(), lines);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
