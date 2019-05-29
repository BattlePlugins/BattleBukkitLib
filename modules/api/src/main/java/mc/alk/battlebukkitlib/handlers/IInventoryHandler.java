package mc.alk.battlebukkitlib.handlers;

import java.awt.Color;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface IInventoryHandler {

    void setColor(ItemStack itemStack, Color color);

    Color getColor(ItemStack itemStack);

    List<PotionEffect> getCustomEffects(ItemStack itemStack);

    void addCustomEffect(ItemStack itemStack, PotionEffect effect);

    void removeCustomEffect(ItemStack itemStack, PotionEffectType effectType);

    void setLore(ItemStack itemStack, List<String> lore);

    List<String> getLore(ItemStack itemStack);

    void setDisplayName(ItemStack itemStack, String displayName);

    String getDisplayName(ItemStack itemStack);

    void setOwnerName(ItemStack itemStack, String ownerName);

    String getOwnerName(ItemStack itemStack);

    void setUnbreakable(ItemStack itemStack, boolean unbreakable);

    boolean isUnbreakable(ItemStack itemStack);

    void setCustomModelData(ItemStack itemstack, int data);

    int getCustomModelData(ItemStack itemStack);

    boolean isEnderChest(InventoryType type);

    public static final IInventoryHandler NULL_HANDLER = new IInventoryHandler() {

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
        public void setLore(ItemStack itemStack, List<String> lore) {
            /* do nothing */
        }

        @Override
        public List<String> getLore(ItemStack itemStack) {
            return null;
        }

        @Override
        public void setDisplayName(ItemStack itemStack, String displayName) {
            /* do nothing */
        }

        @Override
        public String getDisplayName(ItemStack itemStack) {
            return null;
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
    };
}
