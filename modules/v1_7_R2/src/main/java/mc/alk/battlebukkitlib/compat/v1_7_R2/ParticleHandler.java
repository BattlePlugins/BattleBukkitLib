package mc.alk.battlebukkitlib.compat.v1_7_R2;

import mc.alk.battlebukkitlib.handlers.IParticleHandler;
import net.minecraft.server.v1_7_R2.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * For craftbukkit 1.7.5-R0.1
 */
public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        try {
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    effectType.getParticleName(), 
                    (float) location.getX(),
                    (float) location.getY(), 
                    (float) location.getZ(),
                    (float) offSet.getX(), 
                    (float) offSet.getY(), 
                    (float) offSet.getZ(),
                    (float) speed, 
                    count);

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
