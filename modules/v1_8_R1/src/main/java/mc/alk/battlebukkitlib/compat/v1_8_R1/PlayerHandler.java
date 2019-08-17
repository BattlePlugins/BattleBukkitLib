package mc.alk.battlebukkitlib.compat.v1_8_R1;

import mc.alk.battlebukkitlib.handlers.IPlayerHandler;
import net.minecraft.server.v1_8_R1.ChatComponentText;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
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
        return player.getMaxHealth();
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

    @Override
    public void sendTitle(Player player, String title, String subtitle, int time) {
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, new ChatComponentText(ChatColor.translateAlternateColorCodes('&', title)));
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, new ChatComponentText(ChatColor.translateAlternateColorCodes('&', subtitle)));
        PacketPlayOutTitle timesPacket = new PacketPlayOutTitle(time, time, time);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timesPacket);
    }

    @Override
    public void sendActionBarText(Player player, String actionBarText) {
        PacketPlayOutChat actionBarPacket = new PacketPlayOutChat(new ChatComponentText(ChatColor.translateAlternateColorCodes('&', actionBarText)), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(actionBarPacket);
    }
}
