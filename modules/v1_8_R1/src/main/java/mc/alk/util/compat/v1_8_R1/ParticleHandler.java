package mc.alk.util.compat.v1_8_R1;

import mc.alk.util.handlers.IParticleHandler;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * For craftbukkit 1.8 to 1.8.1 <br/><br/>
 * 
 * http://wiki.vg/Protocol#Particle
 */
public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        try {
            EnumParticle particle = EnumParticle.valueOf(effectType.name());
            // http://wiki.vg/Protocol#Particle
            // PacketPlayOutWorldParticles(EnumParticle enumparticle, boolean flag, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float particleData, int particleCount, int... extraData)
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    particle, false, // Change particle distance from 256 to 65536 ?
                    (float) location.getX(),(float) location.getY(), (float) location.getZ(),
                    offSet.getBlockX(), offSet.getBlockY(), offSet.getBlockZ(),
                    (float) speed, count);

            ((CraftPlayer) player).getHandle().playerConnection
                    .sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
