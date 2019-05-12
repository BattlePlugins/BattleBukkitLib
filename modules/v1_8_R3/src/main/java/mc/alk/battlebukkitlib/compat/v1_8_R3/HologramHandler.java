package mc.alk.battlebukkitlib.compat.v1_8_R3;

import java.util.Arrays;
import java.util.List;

import mc.alk.battlebukkitlib.handlers.IHologramHandler;
import mc.alk.battlebukkitlib.objects.Hologram;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
        EntityArmorStand stand = new EntityArmorStand(world);
        stand.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        stand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        stand.setCustomNameVisible(true);
        PacketPlayOutSpawnEntityLiving packedt = new PacketPlayOutSpawnEntityLiving(stand);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
            nmsPlayer.playerConnection.sendPacket(packedt);

        }
        return Arrays.asList(stand.getId());
    }

}
