package mc.alk.battlebukkitlib.compat.v1_8_R3;

import mc.alk.battlebukkitlib.handlers.IDamageHandler;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityPlayer;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DamageHandler implements IDamageHandler {

    @Override
    public void damageEntity(Player player, double dmg) {
        EntityPlayer entity = ((CraftPlayer)player).getHandle();
        entity.damageEntity(DamageSource.GENERIC, (float) dmg);
    }

}
