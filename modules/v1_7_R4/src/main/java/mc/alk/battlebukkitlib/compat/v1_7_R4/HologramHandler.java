package mc.alk.battlebukkitlib.compat.v1_7_R4;

import java.util.Arrays;
import java.util.List;

import mc.alk.battlebukkitlib.handlers.IHologramHandler;
import mc.alk.battlebukkitlib.objects.Hologram;
import net.minecraft.server.v1_7_R4.EntityHorse;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntityWitherSkull;
import net.minecraft.server.v1_7_R4.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class HologramHandler implements IHologramHandler {

    @Override
    public boolean destroyHologram(Hologram hologram) {
        int[] ints = new int[hologram.getIds().size()];
        for (int j = 0; j < ints.length; j++) {
            ints[j] = hologram.getIds().get(j);
        }
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(ints);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection
                    .sendPacket(packet);
        }
        hologram.setShowing(false);
        return true;
    }

    @Override
    public List<Integer> showLine(Location location, String text) {
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        EntityWitherSkull skull = new EntityWitherSkull(world);
        skull.setLocation(location.getX(), location.getY() + 1 + 55,
                location.getZ(), 0, 0);
        ((CraftWorld) location.getWorld()).getHandle().addEntity(skull);
        EntityHorse horse = new EntityHorse(world);
        horse.setLocation(location.getX(), location.getY() + 55,
                location.getZ(), 0, 0);
        horse.setAge(-1700000);
        horse.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        horse.setCustomNameVisible(true);
        PacketPlayOutSpawnEntityLiving packedt = new PacketPlayOutSpawnEntityLiving(
                horse);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
            nmsPlayer.playerConnection.sendPacket(packedt);
            PacketPlayOutAttachEntity pa = new PacketPlayOutAttachEntity(0,
                    horse, skull);
            nmsPlayer.playerConnection.sendPacket(pa);
        }
        return Arrays.asList(skull.getId(), horse.getId());
    }

}
