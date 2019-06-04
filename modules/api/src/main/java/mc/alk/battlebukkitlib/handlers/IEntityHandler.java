package mc.alk.battlebukkitlib.handlers;

import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

public interface IEntityHandler {

    void setCollarColor(Wolf wolf, DyeColor color);

    public static final IEntityHandler NULL_HANDLER = new IEntityHandler() {

        @Override
        public void setCollarColor(Wolf wolf, DyeColor color) {
            /* do nothing */
        }
    };
}
