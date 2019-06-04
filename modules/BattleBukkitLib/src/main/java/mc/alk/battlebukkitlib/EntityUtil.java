package mc.alk.battlebukkitlib;

import mc.alk.battlebukkitlib.factory.EntityHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IEntityHandler;
import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

public class EntityUtil {

    private static IEntityHandler handler = EntityHandlerFactory.getNewInstance();

    public static void setCollarColor(Wolf wolf, DyeColor color) {
        handler.setCollarColor(wolf, color);
    }
}
