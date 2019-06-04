package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.EventHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IEventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventUtil {

    private static IEventHandler handler = EventHandlerFactory.getNewInstance();

    public static void setDamage(EntityDamageEvent event, double damage) {
        handler.setDamage(event, damage);
    }
}
