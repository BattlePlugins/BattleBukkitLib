package mc.alk.util.handlers;

import java.awt.Color;

import org.bukkit.inventory.ItemStack;

public interface IInventoryHandler {

	void setItemColor(ItemStack itemStack, Color color);

	String getCustomName(ItemStack itemStack);
}
