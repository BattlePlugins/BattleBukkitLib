package mc.alk.util.compat.v1_10_R1;

import mc.alk.util.handlers.IParticleHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * For craftbukkit 1.10.x <br/><br/>
 * 
 * http://wiki.vg/Protocol#Particle
 */
public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType, Location location, Vector offSet, int speed, int count) {
        player.spawnParticle(
                Particle.valueOf(effectType.name()), 
                location, 
                count, 
                offSet.getX(), 
                offSet.getY(), 
                offSet.getZ(), 
                speed);
    }

}
