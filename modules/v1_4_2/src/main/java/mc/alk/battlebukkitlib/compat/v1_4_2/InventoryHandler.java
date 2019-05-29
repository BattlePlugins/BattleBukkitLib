package mc.alk.battlebukkitlib.compat.v1_4_2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mc.alk.battlebukkitlib.handlers.IInventoryHandler;
import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;

import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InventoryHandler implements IInventoryHandler {

    @Override
    public void setColor(ItemStack itemStack, Color color) {
        /* do nothing */
    }

    @Override
    public Color getColor(ItemStack itemStack) {
        return null;
    }

    @Override
    public List<PotionEffect> getCustomEffects(ItemStack itemStack) {
        return null;
    }

    @Override
    public void addCustomEffect(ItemStack itemStack, PotionEffect effect) {
        /* do nothing */
    }

    @Override
    public void removeCustomEffect(ItemStack itemStack, PotionEffectType effectType) {
        /* do nothing */
    }

    @Override
    public void setLore(ItemStack itemStack, List<String> lores) {
        if (!(itemStack instanceof CraftItemStack)) {
            itemStack = new CraftItemStack(itemStack);
        }

        CraftItemStack cis = (CraftItemStack) itemStack;
        if (cis.getHandle() == null) {
            return;
        }

        NBTTagCompound tag = cis.getHandle().getTag();
        if (tag == null) {
            return;
        }

        NBTTagCompound display = tag.getCompound("display");
        NBTTagList list = new NBTTagList("Lore");
        for (String lore : lores) {
            list.add(new NBTTagString("", lore));
        }

        display.set("Lore", list);
        cis.getHandle().getTag().setCompound("display", tag);
    }

    @Override
    public List<String> getLore(ItemStack itemStack) {
        if (!(itemStack instanceof CraftItemStack)) {
            itemStack = new CraftItemStack(itemStack);
        }

        List<String> lores = new ArrayList<String>();
        CraftItemStack cis = (CraftItemStack) itemStack;
        if (cis.getHandle() == null) {
            return lores;
        }

        NBTTagCompound tag = cis.getHandle().getTag();
        if (tag == null) {
            return lores;
        }

        NBTTagCompound display = tag.getCompound("display");
        if (display == null || display.getList("Lore") == null) {
            return lores;
        }

        NBTTagList list = display.getList("Lore");
        for (int i = 0; i < list.size(); i++) {
            lores.add(list.get(i).toString());
        }
        return lores;
    }

    @Override
    public void setDisplayName(ItemStack itemStack, String displayName) {
        if (!(itemStack instanceof CraftItemStack)) {
            itemStack = new CraftItemStack(itemStack);
        }

        CraftItemStack cis = (CraftItemStack) itemStack;
        if (cis.getHandle() == null) {
            return;
        }

        NBTTagCompound tag = cis.getHandle().getTag();
        if (tag == null) {
            return;
        }

        NBTTagCompound display = tag.getCompound("display");
        display.setString("Name", ChatColor.translateAlternateColorCodes('&', displayName));
        cis.getHandle().getTag().setCompound("display", tag);
    }

    @Override
    public String getDisplayName(ItemStack itemStack) {
        if (!(itemStack instanceof CraftItemStack)) {
            itemStack = new CraftItemStack(itemStack);
        }

        CraftItemStack cis = (CraftItemStack) itemStack;
        // Check for custom display names (that aren't empty)
        if (cis.getHandle() == null) {
            return itemStack.getType().name().toLowerCase();
        }

        NBTTagCompound tag = cis.getHandle().getTag();
        if (tag == null) {
            return itemStack.getType().name().toLowerCase();
        }

        NBTTagCompound display = tag.getCompound("display");
        if (display == null || display.getString("Name") == null || display.getString("Name").isEmpty()) {
            return display.getString("Name");
        }
        return itemStack.getType().name().toLowerCase();
    }

    @Override
    public void setOwnerName(ItemStack itemStack, String ownerName) {
        /* do nothing */
    }

    @Override
    public String getOwnerName(ItemStack itemStack) {
        return null;
    }

    @Override
    public void setUnbreakable(ItemStack itemStack, boolean unbreakable) {
        /* do nothing */
    }

    @Override
    public boolean isUnbreakable(ItemStack itemStack) {
        return false;
    }

    @Override
    public void setCustomModelData(ItemStack itemstack, int data) {
        /* do nothing */
    }

    @Override
    public int getCustomModelData(ItemStack itemstack) {
        return 0;
    }

    @Override
    public boolean isEnderChest(InventoryType type) {
        return false;
    }
}
