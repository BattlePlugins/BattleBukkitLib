package mc.alk.battlebukkitlib.compat.v1_5_R2;

import mc.alk.battlebukkitlib.handlers.ISignHandler;
import net.minecraft.server.v1_5_R2.Packet130UpdateSign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        Location loc = sign.getLocation();
        Packet130UpdateSign packet = new Packet130UpdateSign(loc.getBlock()
                .getX(), loc.getBlock().getY(), loc.getBlock().getZ(), lines);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
