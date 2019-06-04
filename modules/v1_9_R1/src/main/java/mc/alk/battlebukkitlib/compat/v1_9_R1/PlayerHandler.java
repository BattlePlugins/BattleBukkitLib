package mc.alk.battlebukkitlib.compat.v1_9_R1;

import mc.alk.battlebukkitlib.handlers.IPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

public class PlayerHandler implements IPlayerHandler {

    @Override
    public void setHealth(Player player, double health) {
        final double oldHealth = player.getHealth();
        if (oldHealth > health){
            EntityDamageEvent event = new EntityDamageEvent(player,  EntityDamageEvent.DamageCause.CUSTOM, oldHealth-health );
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()){
                player.setLastDamageCause(event);
                final double dmg = Math.max(0,oldHealth - event.getDamage());
                player.setHealth(dmg);
            }
        } else if (oldHealth < health){
            EntityRegainHealthEvent event = new EntityRegainHealthEvent(player, health-oldHealth, EntityRegainHealthEvent.RegainReason.CUSTOM);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()){
                final double regen = Math.min(oldHealth + event.getAmount(),player.getMaxHealth());
                player.setHealth(regen);
            }
        }
    }

    @Override
    public double getHealth(Player player) {
        return player.getHealth();
    }

    @Override
    public double getMaxHealth(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    @Override
    public Object getScoreboard(Player player) {
        return player.getScoreboard();
    }

    @Override
    public void setScoreboard(Player player, Object scoreboard) {
        if (!(scoreboard instanceof Scoreboard))
            return;

        player.setScoreboard((Scoreboard) scoreboard);
    }

    @Override
    public UUID getID(OfflinePlayer player) {
        return player.getUniqueId();
    }
}
