package mc.alk.util.compat.v1_12_R1;

import mc.alk.util.handlers.IHologramHandler;
import mc.alk.util.objects.Hologram;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;


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
            PacketPlayOutAttachEntity pa = new PacketPlayOutAttachEntity(horse, skull);
            
            
            // An alternate way to construct a PacketPlayOutAttachEntity object
            // (in case the internal NMS changes, we can use this alternate way).
//            ByteBuf buffer = Unpooled.buffer(); // io.netty.buffer.*
//            buffer.writeInt(horse.getId());
//            buffer.writeInt(skull.getId());
//            PacketDataSerializer data = new PacketDataSerializer(buffer);
//            PacketPlayOutAttachEntity pe = new PacketPlayOutAttachEntity();
//            try {
//                pe.a(data); // a() is add(), b() is get()
//            } catch (IOException ex) {
//                Logger.getLogger(HologramHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            nmsPlayer.playerConnection.sendPacket(pa);
        }
        return Arrays.asList(skull.getId(), horse.getId());
    }

}
