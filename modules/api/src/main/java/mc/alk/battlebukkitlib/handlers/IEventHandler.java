package mc.alk.battlebukkitlib.handlers;

import org.bukkit.event.entity.EntityDamageEvent;

public interface IEventHandler {

    void setDamage(EntityDamageEvent event, double damage);

    public static final IEventHandler NULL_HANDLER = new IEventHandler() {

        @Override
        public void setDamage(EntityDamageEvent event, double damage) {
            /* do nothing */
        }
    };
}
