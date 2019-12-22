package mc.alk.battlebukkitlib.compat.v1_7_R1;

import java.awt.Color;
import java.util.List;

import mc.alk.battlebukkitlib.handlers.IInventoryHandler;

import net.minecraft.server.v1_7_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InventoryHandler implements IInventoryHandler {

    @Override
    public void setColor(ItemStack itemStack, Color color) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof LeatherArmorMeta)) {
            return;
        }

        org.bukkit.Color bukkitColor = getBukkitColor(color);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        armorMeta.setColor(bukkitColor);
        itemStack.setItemMeta(armorMeta);
    }

    @Override
    public Color getColor(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof LeatherArmorMeta)) {
            return null;
        }

        LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        return new Color(armorMeta.getColor().getRed(), armorMeta.getColor().getGreen(), armorMeta.getColor().getBlue());
    }

    @Override
    public List<PotionEffect> getCustomEffects(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof PotionMeta)) {
            return null;
        }

        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        return potionMeta.getCustomEffects();
    }

    @Override
    public void addCustomEffect(ItemStack itemStack, PotionEffect effect) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof PotionMeta)) {
            return;
        }

        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.addCustomEffect(effect, true);
        itemStack.setItemMeta(potionMeta);
    }

    @Override
    public void removeCustomEffect(ItemStack itemStack, PotionEffectType effectType) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof PotionMeta)) {
            return;
        }

        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.removeCustomEffect(effectType);
        itemStack.setItemMeta(potionMeta);
    }

    @Override
    public void setLore(ItemStack itemStack, List<String> lore) {
        if (itemStack.getItemMeta() == null) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    @Override
    public List<String> getLore(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) {
            return null;
        }

        return itemStack.getItemMeta().getLore();
    }

    @Override
    public void setDisplayName(ItemStack itemStack, String displayName) {
        if (itemStack.getItemMeta() == null) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
    }

    @Override
    public String getDisplayName(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) {
            return null;
        }

        return itemStack.getItemMeta().getDisplayName();
    }

    @Override
    public void setOwnerName(ItemStack itemStack, String ownerName) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof SkullMeta)) {
            return;
        }

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(ownerName);
        itemStack.setItemMeta(skullMeta);
    }

    @Override
    public String getOwnerName(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null || !(itemStack.getItemMeta() instanceof SkullMeta)) {
            return null;
        }

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        return skullMeta.getOwner();
    }

    @Override
    public void setUnbreakable(ItemStack itemStack, boolean unbreakable) {
        net.minecraft.server.v1_7_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("Unbreakable", unbreakable);
        nmsStack.setTag(tag);
    }

    @Override
    public boolean isUnbreakable(ItemStack itemStack) {
        net.minecraft.server.v1_7_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsStack.getTag();
        return tag != null && tag.getBoolean("Unbreakable");
    }

    @Override
    public void setCustomModelData(ItemStack itemstack, int data) {

    }

    @Override
    public int getCustomModelData(ItemStack itemstack) {
        return 0;
    }

    @Override
    public boolean isEnderChest(InventoryType type) {
        return type == InventoryType.ENDER_CHEST;
    }

    private org.bukkit.Color getBukkitColor(Color color){
        return org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }
}
