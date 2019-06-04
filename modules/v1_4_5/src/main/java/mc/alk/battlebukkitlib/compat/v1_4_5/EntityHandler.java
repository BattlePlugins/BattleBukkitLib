package mc.alk.battlebukkitlib.compat.v1_4_5;

import mc.alk.battlebukkitlib.handlers.IEntityHandler;
import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

public class EntityHandler implements IEntityHandler {

    @Override
    public void setCollarColor(Wolf wolf, DyeColor color) {
        wolf.setCollarColor(color);
    }
}
