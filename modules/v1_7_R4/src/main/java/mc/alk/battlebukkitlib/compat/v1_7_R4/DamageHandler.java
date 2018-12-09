package mc.alk.battlebukkitlib.compat.v1_7_R4;

import mc.alk.battlebukkitlib.handlers.IDamageHandler;

import net.minecraft.server.v1_7_R4.DamageSource;
import net.minecraft.server.v1_7_R4.EntityPlayer;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DamageHandler implements IDamageHandler {

    @Override
    public void damageEntity(Player player, double dmg) {
        EntityPlayer entity = ((CraftPlayer)player).getHandle();
        entity.damageEntity(DamageSource.GENERIC, (float) dmg);
    }

}
