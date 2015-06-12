package mc.alk.util.compat.v1_7_R3;

import mc.alk.util.handlers.IParticleHandler;
import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * For craftbukkit 1.7.9
 */
public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        try {
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    effectType.getParticleName(), (float) location.getX(),
                    (float) location.getY(), (float) location.getZ(),
                    offSet.getBlockX(), offSet.getBlockY(), offSet.getBlockZ(),
                    speed, count);

            ((CraftPlayer) player).getHandle().playerConnection
                    .sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
