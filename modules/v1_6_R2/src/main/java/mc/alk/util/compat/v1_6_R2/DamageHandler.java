package mc.alk.util.compat.v1_6_R2;

import mc.alk.util.handlers.IDamageHandler;

import net.minecraft.server.v1_6_R2.DamageSource;
import net.minecraft.server.v1_6_R2.EntityPlayer;

import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DamageHandler implements IDamageHandler {

    @Override
    public void damageEntity(Player player, double dmg) {
        EntityPlayer entity = ((CraftPlayer)player).getHandle();
        entity.damageEntity(DamageSource.GENERIC, (float) dmg);
    }

}
