package mc.alk.util.compat.v1_3_2;

import java.awt.Color;

import mc.alk.util.handlers.IInventoryHandler;
import net.minecraft.server.NBTTagCompound;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler implements IInventoryHandler {

    @Override
    public void setItemColor(ItemStack itemStack, Color color) {/* do nothing */

    }

    @Override
    public String getCustomName(ItemStack item) {
        if (!(item instanceof CraftItemStack)) {
            item = new CraftItemStack(item);
        }

        CraftItemStack cis = (CraftItemStack) item;
        // / Check for custom display names (that aren't empty)
        if (cis.getHandle() != null) {
            NBTTagCompound tag = cis.getHandle().getTag();
            if (tag != null) {
                NBTTagCompound display = tag.getCompound("display");
                if (display != null && display.getString("Name") != null
                        && !display.getString("Name").isEmpty()) {
                    return display.getString("Name");
                }
            }
        }
        return item.getType().name().toLowerCase();
    }
}
