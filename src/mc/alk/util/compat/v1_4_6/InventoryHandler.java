package mc.alk.util.compat.v1_4_6;

import java.awt.Color;

import mc.alk.util.handlers.IInventoryHandler;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class InventoryHandler implements IInventoryHandler
{

	@Override
	public void setItemColor(ItemStack itemStack, Color color)
	{
		org.bukkit.Color bukkitColor = getBukkitColor(color);
		LeatherArmorMeta lam = (LeatherArmorMeta) itemStack.getItemMeta();
		lam.setColor(bukkitColor);
		itemStack.setItemMeta(lam);
	}

	public static org.bukkit.Color getBukkitColor(Color color)
	{
		return org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(),
				color.getBlue());
	}

	@Override
	public String getCustomName(ItemStack itemStack)
	{
		ItemMeta im = itemStack.getItemMeta();

		if (im == null)
		{
			return itemStack.getType().name().toLowerCase();
		}
		String displayName = im.getDisplayName();
		return displayName == null || displayName.isEmpty() ? itemStack
				.getType().name().toLowerCase() : displayName;
	}
}
