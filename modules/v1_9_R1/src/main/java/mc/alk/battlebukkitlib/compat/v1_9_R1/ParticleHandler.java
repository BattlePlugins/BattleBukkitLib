package mc.alk.battlebukkitlib.compat.v1_9_R1;

import mc.alk.battlebukkitlib.handlers.IParticleHandler;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * For craftbukkit 1.8.7 <br/><br/>
 * 
 * http://wiki.vg/Protocol#Particle
 */
public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        player.spawnParticle(Particle.valueOf(effectType.name()), location, count, offSet.getX(), offSet.getY(), offSet.getZ(), speed);
    }

}
