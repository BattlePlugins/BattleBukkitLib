package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.ParticleHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IParticleHandler;
import mc.alk.battlebukkitlib.handlers.IParticleHandler.ParticleEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleUtil {

    private static IParticleHandler handler = ParticleHandlerFactory.getNewInstance();

    public static void sendEffect(Player player, ParticleEffects effectType,
            Location location, Vector offSet, int speed, int count) {
        handler.sendEffect(player, effectType, location, offSet, speed, count);
    }
}
