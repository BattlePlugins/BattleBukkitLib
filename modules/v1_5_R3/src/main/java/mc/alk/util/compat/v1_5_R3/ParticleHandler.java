package mc.alk.util.compat.v1_5_R3;

import mc.alk.util.ReflectionUtilities;
import mc.alk.util.handlers.IParticleHandler;
import net.minecraft.server.v1_5_R3.Packet63WorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleHandler implements IParticleHandler {

    @Override
    public void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        try {
            Packet63WorldParticles packet = new Packet63WorldParticles();
            ReflectionUtilities.setValue(packet, "a",
                    effectType.getParticleName());
            ReflectionUtilities.setValue(packet, "b", (float) location.getX());
            ReflectionUtilities.setValue(packet, "c", (float) location.getY());
            ReflectionUtilities.setValue(packet, "d", (float) location.getZ());
            ReflectionUtilities.setValue(packet, "e", offSet.getBlockX());
            ReflectionUtilities.setValue(packet, "f", offSet.getBlockY());
            ReflectionUtilities.setValue(packet, "g", offSet.getBlockZ());
            ReflectionUtilities.setValue(packet, "h", speed);
            ReflectionUtilities.setValue(packet, "i", count);
            ((CraftPlayer) player).getHandle().playerConnection
                    .sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
