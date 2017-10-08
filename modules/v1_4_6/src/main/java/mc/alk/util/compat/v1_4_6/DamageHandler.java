package mc.alk.util.compat.v1_4_6;

import mc.alk.util.handlers.IDamageHandler;

import net.minecraft.server.v1_4_6.DamageSource;
import net.minecraft.server.v1_4_6.EntityPlayer;

import org.bukkit.craftbukkit.v1_4_6.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DamageHandler implements IDamageHandler {

    @Override
    public void damageEntity(Player player, double dmg) {
        EntityPlayer entity = ((CraftPlayer)player).getHandle();
        entity.damageEntity(DamageSource.GENERIC, (int) dmg);
    }

}
