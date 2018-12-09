package mc.alk.battlebukkitlib.handlers;

import java.awt.Color;

import org.bukkit.inventory.ItemStack;

public interface IInventoryHandler {

    void setItemColor(ItemStack itemStack, Color color);

    String getCustomName(ItemStack itemStack);

    public static final IInventoryHandler NULL_HANDLER = new IInventoryHandler() {

        @Override
        public void setItemColor(ItemStack itemStack, Color color) {
            // do nothing
        }

        @Override
        public String getCustomName(ItemStack itemStack) {
            return "";
        }
    };

}
