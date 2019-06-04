package mc.alk.battlebukkitlib.compat.v1_2_5;

import mc.alk.battlebukkitlib.handlers.IEventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventHandler implements IEventHandler {

    @Override
    public void setDamage(EntityDamageEvent event, double damage) {
        event.setDamage((int) damage);
    }
}
