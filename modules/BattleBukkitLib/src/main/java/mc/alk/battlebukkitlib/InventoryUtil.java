package mc.alk.battlebukkitlib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mc.alk.battlebukkitlib.factory.InventoryHandlerFactory;
import mc.alk.battlebukkitlib.handlers.IInventoryHandler;

import mc.euro.bukkitadapter.EnchantAdapter;
import mc.euro.bukkitadapter.MaterialAdapter;
import mc.euro.bukkitadapter.enchant.BattleEnchant;
import mc.euro.bukkitadapter.material.BattleMaterial;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class InventoryUtil {

    static final String version = "InventoryUtil 2.2.3";
    static final boolean DEBUG = false;
    static IInventoryHandler handler = InventoryHandlerFactory.getNewInstance();

    private static final Pattern PATTERN_LORE =
            Pattern.compile("lore= ?\"((?s).*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching lore
    private static final Pattern PATTERN_OWNER =
            Pattern.compile("owner= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching lore
    private static final Pattern PATTERN_DISPLAY_NAME =
            Pattern.compile("displayName= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for Display name
    private static final Pattern PATTERN_COLOR =
            Pattern.compile("color= ?([0-9]+),([0-9]+),([0-9]+)",Pattern.CASE_INSENSITIVE); //The pattern for matching lore
    private static final Pattern PATTERN_POSITION =
            Pattern.compile("position= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching position
    private static final Pattern PATTERN_MODEL_DATA =
            Pattern.compile("modelData= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching custom model data
    private static final Pattern PATTERN_UNBREAKABLE =
            Pattern.compile("unbreakable= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching unbreakable
    private static final Pattern PATTERN_EFFECT =
            Pattern.compile("effects= ?\"([^\"]*)\"",Pattern.CASE_INSENSITIVE); //The pattern for matching potion effects

    static class Armor {

        public ArmorLevel level;
        public ArmorType type;

        Armor(ArmorType at, ArmorLevel al) {
            this.level = al;
            this.type = at;
        }
    }

    static class EnchantmentWithLevel {

        public EnchantmentWithLevel() {
        }

        public EnchantmentWithLevel(boolean all) {
            this.all = all;
        }

        public Enchantment enchant;
        public Integer lvl;
        boolean all = false;

        @Override
        public String toString() {
            return (enchant != null ? enchant.getName() : "null") + ":" + lvl;
        }
    }

    public enum ArmorLevel {

        DISGUISE, WOOL, LEATHER, IRON, GOLD, CHAINMAIL, DIAMOND
    }

    public enum ArmorType {

        HELM, CHEST, LEGGINGS, BOOTS
    }

    public static Enchantment getEnchantmentByCommonName(String iname) {
        iname = iname.toUpperCase();

        for (BattleEnchant enchant : BattleEnchant.values()) {
            if (enchant.getName().equalsIgnoreCase(iname))
                return enchant.parseEnchant();

            if (Arrays.asList(enchant.getAliases()).contains(iname))
                return enchant.parseEnchant();
        }
        return null;
    }

    public static String getCommonNameByEnchantment(Enchantment enchant) {
        BattleEnchant battleEnchant = BattleEnchant.fromEnchantment(enchant);
        return battleEnchant.getName();
    }

    static final Map<Material, Armor> armor;

    static {
        armor = new HashMap<Material, Armor>();
        try {
            armor.put(BattleMaterial.SKELETON_SKULL.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));
            armor.put(BattleMaterial.WITHER_SKELETON_SKULL.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));
            armor.put(BattleMaterial.ZOMBIE_HEAD.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));
            armor.put(BattleMaterial.PLAYER_HEAD.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));
            armor.put(BattleMaterial.CREEPER_HEAD.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));
            armor.put(BattleMaterial.DRAGON_HEAD.parseMaterial(), new Armor(ArmorType.HELM, ArmorLevel.DISGUISE));

        } catch (Throwable ex) {
            /* no errors as it's just an old bukkit that doesn't have this Material */
        }

        armor.put(BattleMaterial.WHITE_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.ORANGE_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.MAGENTA_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.LIGHT_BLUE_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.YELLOW_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.LIME_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.PINK_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.GRAY_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.LIGHT_GRAY_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.GRAY_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.CYAN_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.PURPLE_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.BLUE_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.BROWN_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.GREEN_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.RED_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(BattleMaterial.BLACK_WOOL.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.WOOL));
        armor.put(Material.LEATHER_HELMET, new Armor(ArmorType.HELM,
                ArmorLevel.LEATHER));
        armor.put(Material.IRON_HELMET, new Armor(ArmorType.HELM,
                ArmorLevel.IRON));
        armor.put(BattleMaterial.GOLDEN_HELMET.parseMaterial(), new Armor(ArmorType.HELM,
                ArmorLevel.GOLD));
        armor.put(Material.DIAMOND_HELMET, new Armor(ArmorType.HELM,
                ArmorLevel.DIAMOND));
        armor.put(Material.CHAINMAIL_HELMET, new Armor(ArmorType.HELM,
                ArmorLevel.CHAINMAIL));

        armor.put(Material.LEATHER_CHESTPLATE, new Armor(ArmorType.CHEST,
                ArmorLevel.LEATHER));
        armor.put(Material.IRON_CHESTPLATE, new Armor(ArmorType.CHEST,
                ArmorLevel.IRON));
        armor.put(BattleMaterial.GOLDEN_CHESTPLATE.parseMaterial(), new Armor(ArmorType.CHEST,
                ArmorLevel.GOLD));
        armor.put(Material.DIAMOND_CHESTPLATE, new Armor(ArmorType.CHEST,
                ArmorLevel.DIAMOND));
        armor.put(Material.CHAINMAIL_CHESTPLATE, new Armor(ArmorType.CHEST,
                ArmorLevel.CHAINMAIL));

        armor.put(Material.LEATHER_LEGGINGS, new Armor(ArmorType.LEGGINGS,
                ArmorLevel.LEATHER));
        armor.put(Material.IRON_LEGGINGS, new Armor(ArmorType.LEGGINGS,
                ArmorLevel.IRON));
        armor.put(BattleMaterial.GOLDEN_LEGGINGS.parseMaterial(), new Armor(ArmorType.LEGGINGS,
                ArmorLevel.GOLD));
        armor.put(Material.DIAMOND_LEGGINGS, new Armor(ArmorType.LEGGINGS,
                ArmorLevel.DIAMOND));
        armor.put(Material.CHAINMAIL_LEGGINGS, new Armor(ArmorType.LEGGINGS,
                ArmorLevel.CHAINMAIL));

        armor.put(Material.LEATHER_BOOTS, new Armor(ArmorType.BOOTS,
                ArmorLevel.LEATHER));
        armor.put(Material.IRON_BOOTS, new Armor(ArmorType.BOOTS,
                ArmorLevel.IRON));
        armor.put(BattleMaterial.GOLDEN_BOOTS.parseMaterial(), new Armor(ArmorType.BOOTS,
                ArmorLevel.GOLD));
        armor.put(Material.DIAMOND_BOOTS, new Armor(ArmorType.BOOTS,
                ArmorLevel.DIAMOND));
        armor.put(Material.CHAINMAIL_BOOTS, new Armor(ArmorType.BOOTS,
                ArmorLevel.CHAINMAIL));
    }

    public static class PInv {
        public ItemStack[] contents;
        public ItemStack[] armor;
        public PInv() {}
        public PInv(PlayerInventory inventory) {
            contents = inventory.getContents();
            setArmor(inventory);
        }
        public PInv(List<ItemStack> items){
            contents = items.toArray(new ItemStack[items.size()]);
            armor = new ItemStack[0];
        }
        public void setArmor(PlayerInventory inventory){
            this.armor=new ItemStack[4];
            this.armor[ArmorType.HELM.ordinal()] = inventory.getHelmet();
            this.armor[ArmorType.CHEST.ordinal()] = inventory.getChestplate();
            this.armor[ArmorType.LEGGINGS.ordinal()] = inventory.getLeggings();
            this.armor[ArmorType.BOOTS.ordinal()] = inventory.getBoots();
        }
    }

    public static int arrowCount(Player p) {
        return getItemAmount(p.getInventory().getContents(), new ItemStack(
                Material.ARROW, 1));
    }

    public static boolean isEnderChest(InventoryType type) {
        return handler.isEnderChest(type);
    }

    public static int getItemAmountFromInventory(Inventory inv, ItemStack is) {
        return getItemAmount(inv.getContents(), is);
    }

    public static boolean isArmor(ItemStack is) {
        return armor.get(is.getType()) != null;
    }
    public static boolean isRealArmor(ItemStack is) {
        return armor.get(is.getType()) != null && !is.getType().name().contains("WOOL");
    }

    public static boolean hasArmor(Player p) {
        PlayerInventory pi = p.getInventory();
        return ((pi.getBoots() != null && pi.getBoots().getType() != Material.AIR)
                && (pi.getHelmet() != null && pi.getBoots().getType() != Material.AIR)
                && (pi.getLeggings() != null && pi.getBoots().getType() != Material.AIR) && (pi
                .getChestplate() != null && pi.getBoots().getType() != Material.AIR));
    }

    public static ArmorLevel hasArmorSet(Player p) {
        return hasArmorSet(p.getInventory());
    }

    public static ArmorLevel hasArmorSet(Inventory inv) {
        ArmorLevel armorSet[] = new ArmorLevel[4];
        for (ItemStack is : inv) {
            Armor a = armor.get(is);
            if (a == null) {
                continue;
            }
            switch (a.type) {
                case BOOTS:
                    armorSet[0] = a.level;
                    break;
                case LEGGINGS:
                    armorSet[1] = a.level;
                    break;
                case CHEST:
                    armorSet[2] = a.level;
                    break;
                case HELM:
                    armorSet[3] = a.level;
                    break;
            }
        }
        ArmorLevel lvl = null;
        for (ArmorLevel a : armorSet) {
            if (lvl == null) {
                lvl = a;
            } else if (lvl != a) {
                return null;
            }
        }
        return lvl;
    }

    public static ArmorLevel hasArmorSet(List<ItemStack> inv) {
        ArmorLevel armorSet[] = new ArmorLevel[4];
        for (ItemStack is: inv){
            Armor a = armor.get(is.getType());
            if (a == null)
                continue;
            switch (a.type){
                case BOOTS: armorSet[0] = a.level; break;
                case LEGGINGS: armorSet[1] = a.level; break;
                case CHEST: armorSet[2] = a.level; break;
                case HELM: armorSet[3] = a.level; break;
            }
        }
        ArmorLevel lvl = null;
        for (ArmorLevel a: armorSet){
            if (lvl == null)
                lvl = a;
            else if (lvl != a)
                return null;
        }
        return lvl;
    }

    public static int getItemAmount(ItemStack[] items, ItemStack is) {
        int count = 0;
        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }
            if (item.getType() == is.getType()
                    && ((item.getDurability() == is.getDurability() || item
                    .getDurability() == -1))) {
                count += item.getAmount();
            }
        }
        return count;
    }

    public static ItemStack getItemStack(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        name = name.replace(" ", "_");
        name = name.replace(":", ";");

        int dataIndex = name.indexOf(';');
        dataIndex = (dataIndex != -1 ? dataIndex : -1);
        int dataValue = 0;
        if (dataIndex != -1) {
            dataValue = (isInt(name.substring(dataIndex + 1)) ? Integer
                    .parseInt(name.substring(dataIndex + 1)) : 0);
            name = name.substring(0, dataIndex);
        }

        dataValue = dataValue < 0 ? 0 : dataValue;
        BattleMaterial battleMat = BattleMaterial.fromString(name + ":" + dataValue);
        if (battleMat != null && battleMat != BattleMaterial.AIR)
            return battleMat.parseItem();

        Material mat = getMat(name);
        if (mat != null && mat != Material.AIR) {
            return new ItemStack(mat, 0, (short) dataValue);
        }

        return null;
    }

    public static boolean isInt(String i) {
        try {
            Integer.parseInt(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String i) {
        try {
            Float.parseFloat(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // / Get the Material
    public static Material getMat(String name) {
        // Use .matchMaterial since in versions 1.12 and under, this also checks ID
        Material mat = Material.matchMaterial(name);
        if (mat == null)
            mat = MaterialAdapter.getMaterial(name);

        return mat;
    }

    // / This allows for abbreviations to work, useful for sign etc
    /// This allows for abbreviations to work, useful for sign etc
    /*
    public static int getMaterialID(String name) {
        name = name.toUpperCase();
        /// First try just getting it from the Material Name
        Material mat = MaterialAdapter.getMaterial(name);

        if (mat != null)
            return mat.getId();
        /// Might be an abbreviation, or a more complicated
        int temp = Integer.MAX_VALUE;
        mat = null;
        name = name.replaceAll("\\s+", "").replaceAll("_", "");
        for (Material m : Material.values()) {
            if (m.name().replaceAll("_", "").startsWith(name)) {
                if (m.name().length() < temp) {
                    mat = m;
                    temp = m.name().length();
                }
            }
        }
        return mat != null ? mat.getId() : -1;
    }
    */

    public static boolean hasItem(Player p, ItemStack itemType) {
        PlayerInventory inv = p.getInventory();
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() == itemType.getType()) {
                return true;
            }
        }
        for (ItemStack is : inv.getArmorContents()) {
            if (is != null && is.getType() == itemType.getType()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAllItems(Player p, List<ItemStack> items) {
        for (ItemStack is : items){
            if (!hasItem(p,is))
                return false;
        }
        return true;
    }

    public static boolean hasAnyItem(Player p) {
        PlayerInventory inv = p.getInventory();
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() != Material.AIR) {
                // System.out.println("item=" + is);
                return true;
            }
        }
        for (ItemStack is : inv.getArmorContents()) {
            if (is != null && is.getType() != Material.AIR) {
                // System.out.println("item=" + is);
                return true;
            }
        }
        return false;
    }

    public static void addItemToInventory(Player player, ItemStack itemStack) {
        addItemToInventory(player, itemStack, itemStack.getAmount(), true,
                false, null);
    }

    public static void addItemToInventory(Player player, ItemStack itemStack,
            int stockAmount, boolean update) {
        addItemToInventory(player, itemStack, stockAmount, update, false, null);
    }

    public static void addItemsToInventory(Player p, List<ItemStack> items,
            boolean ignoreCustomHelmet) {
        addItemsToInventory(p, items, ignoreCustomHelmet, null);
    }

    @SuppressWarnings("deprecation")
    public static void addItemsToInventory(Player p, List<ItemStack> items,
            boolean ignoreCustomHelmet, Color color) {
        for (ItemStack is : items) {
            InventoryUtil.addItemToInventory(p, is.clone(), is.getAmount(),
                    false, ignoreCustomHelmet, color);
        }
        try {
            p.updateInventory();
        } catch (Exception e) {
        }
    }

    public static void addItemToInventory(Player player, ItemStack itemStack,
            int stockAmount, boolean update, boolean ignoreCustomHelmet) {
        addItemToInventory(player, itemStack, stockAmount, update,
                ignoreCustomHelmet, null);
    }

    @SuppressWarnings("deprecation")
    public static void addItemToInventory(Player player, ItemStack itemStack,
            int stockAmount, boolean update, boolean ignoreCustomHelmet,
            Color color) {
        PlayerInventory inv = player.getInventory();
        Material itemType = itemStack.getType();
        if (armor.containsKey(itemType)) {
            addArmorToInventory(inv, itemStack, stockAmount,
                    ignoreCustomHelmet, color);
        } else {
            addItemToInventory(inv, itemStack, stockAmount);
        }
        if (update) {
            try {
                player.updateInventory();
            } catch (Exception e) {
            }
        }
    }

    private static void addArmorToInventory(PlayerInventory inv,
            ItemStack itemStack, int stockAmount, boolean ignoreCustomHelmet,
            Color color) {
        Material itemType = itemStack.getType();
        final boolean isHelmet = armor.get(itemType).type == ArmorType.HELM;
		// / no item: add to armor slot
        // / item better: add old to inventory, new to armor slot
        // / item notbetter: add to inventory
        Armor a = armor.get(itemType);
        final ItemStack oldArmor = getArmorSlot(inv, a.type);
        boolean empty = (oldArmor == null || oldArmor.getType() == Material.AIR);
        boolean better = empty ? true : armorSlotBetter(
                armor.get(oldArmor.getType()), a);

        if (color != null && a.level == ArmorLevel.LEATHER) {
            handler.setColor(itemStack, color);
        }
        if (empty || better) {
            switch (armor.get(itemType).type) {
                case HELM:
                    if (empty || (better && !ignoreCustomHelmet)) {
                        inv.setHelmet(itemStack);
                    }
                    break;
                case CHEST:
                    inv.setChestplate(itemStack);
                    break;
                case LEGGINGS:
                    inv.setLeggings(itemStack);
                    break;
                case BOOTS:
                    inv.setBoots(itemStack);
                    break;
            }
        }
        if (!empty) {
            if (better && !(isHelmet && ignoreCustomHelmet)) {
                addItemToInventory(inv, oldArmor, oldArmor.getAmount());
            } else {
                addItemToInventory(inv, itemStack, stockAmount);
            }
        }
    }

    private static boolean armorSlotBetter(Armor oldArmor, Armor newArmor) {
        if (oldArmor == null || newArmor == null) // / technically we could
        // throw an exception.. but
        // nah
        {
            return false;
        }
        return oldArmor.level.ordinal() < newArmor.level.ordinal();
    }

    private static ItemStack getArmorSlot(PlayerInventory inv,
            ArmorType armorType) {
        switch (armorType) {
            case HELM:
                return inv.getHelmet();
            case CHEST:
                return inv.getChestplate();
            case LEGGINGS:
                return inv.getLeggings();
            case BOOTS:
                return inv.getBoots();
        }
        return null;
    }

    // /Adds item to inventory
    public static void addItemToInventory(Inventory inv, ItemStack is, int left) {
        int maxStackSize = is.getType().getMaxStackSize();
        if (left <= maxStackSize) {
            is.setAmount(left);
            inv.addItem(is);
            return;
        }

        if (maxStackSize != 64) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            for (int i = 0; i < Math.ceil(left / maxStackSize); i++) {
                if (left < maxStackSize) {
                    is.setAmount(left);
                    items.add(is);
                    return;
                } else {
                    is.setAmount(maxStackSize);
                    items.add(is);
                }
            }
            Object[] iArray = items.toArray();
            for (Object o : iArray) {
                inv.addItem((ItemStack) o);
            }
        } else {
            inv.addItem(is);
        }
    }

    public static void closeInventory(Player p) {
        try {
            p.closeInventory();
        } catch (Exception closeInventoryError) {
            // / This almost always throws an NPE, but does its job so ignore
        }
    }

    public static void clearInventory(Player p) {
        try {
            PlayerInventory inv = p.getInventory();
            closeInventory(p);
            if (inv != null) {
                inv.clear();
                inv.setArmorContents(null);
                inv.setItemInHand(null);
            }
            p.updateInventory();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static void clearInventory(Player p, boolean skipHead) {
        if (!skipHead){
            clearInventory(p);
            return;
        }
        try{
            PlayerInventory inv = p.getInventory();
            closeInventory(p);
            if (inv != null){
                inv.clear();
                if (inv.getHelmet()!=null && isRealArmor(inv.getHelmet())) inv.setHelmet(null);
                inv.setBoots(null);
                inv.setChestplate(null);
                inv.setLeggings(null);
                inv.setItemInHand(null);
            }
            p.updateInventory();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getCommonName(ItemStack is) {
        Material mat = is.getType();
        int datavalue = is.getDurability();
        if (datavalue > 0) {
            return mat.toString() + ":" + datavalue;
        }
        return mat.toString();
    }

    public static String getCustomName(ItemStack item) {
        return handler.getDisplayName(item);
    }

    @SuppressWarnings("deprecation")
    public static void addItemsToInventory(Player p, List<ItemStack> items) {
        for (ItemStack is : items) {
            InventoryUtil.addItemToInventory(p, is.clone(), is.getAmount(),
                    false);
        }
        try {
            p.updateInventory();
        } catch (Exception e) {
        }
    }

    public static ItemStack parseItemShort(String str) throws Exception {
        // System.out.println("string = " + str);
        str = str.replaceAll("[}{]", "");
        str = str.replaceAll("=", " ");
        if (DEBUG) {
            System.out.println("item=" + str);
        }
        ItemStack is = null;
        try {
            String split[] = str.split(" ");
            is = InventoryUtil.getItemStack(split[0].trim());
            is.setAmount(Integer.valueOf(split[split.length - 1]));
            for (int i = 1; i < split.length - 1; i++) {
                EnchantmentWithLevel ewl = getEnchantment(split[i].trim());
                try {
                    is.addEnchantment(ewl.enchant, ewl.lvl);
                } catch (IllegalArgumentException iae) {
                    Logger.getLogger("minecraft").warning(
                            ewl + " can not be applied to the item " + str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("minecraft").severe("Couldnt parse item=" + str);
            throw new Exception("parse item was bad");
        }
        return is;
    }

    public static EnchantmentWithLevel getEnchantment(String str) {
        if (str.equalsIgnoreCase("all")) {
            return new EnchantmentWithLevel(true);
        }
        Enchantment e = null;
        str = str.replaceAll(",", ":");
        int index = str.indexOf(':');
        index = (index != -1 ? index : -1);
        int lvl = -1;
        if (index != -1) {
            try {
                lvl = Integer.parseInt(str.substring(index + 1));
            } catch (Exception err) {
            }
            str = str.substring(0, index);
        }

        // System.out.println("String = <" + str +">   " + lvl);
        try {
            e = EnchantAdapter.getEnchantment(str);
        } catch (Exception err) {
        }
        if (e == null) {
            e = Enchantment.getByName(str);
        }
        if (e == null) {
            e = getEnchantmentByCommonName(str);
        }
        if (e == null) {
            return null;
        }
        EnchantmentWithLevel ewl = new EnchantmentWithLevel();
        ewl.enchant = e;
        if (lvl < e.getStartLevel()) {
            lvl = e.getStartLevel();
        }
        if (lvl > e.getMaxLevel()) {
            lvl = e.getMaxLevel();
        }
        ewl.lvl = lvl;
        return ewl;
    }

    public static boolean addEnchantments(ItemStack is, String[] args) {
        Map<Enchantment, Integer> encs = new HashMap<Enchantment, Integer>();
        for (String s : args) {
            EnchantmentWithLevel ewl = getEnchantment(s);
            if (ewl != null) {
                if (ewl.all) {
                    return addAllEnchantments(is);
                }
                encs.put(ewl.enchant, ewl.lvl);
            }
        }
        addEnchantments(is, encs);
        return true;
    }

    public static void addEnchantments(ItemStack is,
            Map<Enchantment, Integer> enchantments) {
        for (Enchantment e : enchantments.keySet()) {
            if (e.canEnchantItem(is)) {
                is.addUnsafeEnchantment(e, enchantments.get(e));
            }
        }
    }

    public static boolean addAllEnchantments(ItemStack is) {
        for (Enchantment enc : Enchantment.values()) {
            if (enc.canEnchantItem(is)) {
                is.addUnsafeEnchantment(enc, enc.getMaxLevel());
            }
        }
        return true;
    }

    /**
     * For Serializing an item or printing
     *
     * @param is
     * @return
     */
    public static String getItemStringShort(ItemStack is) {
        StringBuilder sb = new StringBuilder();
        sb.append(is.getType().toString() + ":" + is.getData().getData() + " ");
        Map<Enchantment, Integer> encs = is.getEnchantments();
        for (Enchantment enc : encs.keySet()) {
            sb.append(enc.getName() + ":" + encs.get(enc) + " ");
        }
        sb.append(is.getAmount());
        return sb.toString();
    }

    public static boolean hasEnchantedItem(Player p) {
        PlayerInventory inv = p.getInventory();
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() != Material.AIR) {
                Map<Enchantment, Integer> enc = is.getEnchantments();
                if (enc != null && !enc.isEmpty()) {
                    return true;
                }
            }
        }
        for (ItemStack is : inv.getArmorContents()) {
            if (is != null && is.getType() != Material.AIR) {
                Map<Enchantment, Integer> enc = is.getEnchantments();
                if (enc != null && !enc.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasItems(Player p, Collection<ItemStack> items) {
        PlayerInventory inv = p.getInventory();
        for (ItemStack is : items) {
            int amount = getItemAmountFromInventory(inv, is);
            // System.out.println("Checking " + is +"   amount = " + amount);
            if (amount < is.getAmount()) {
                return false;
            }
        }
        return true;
    }

    public static void removeItems(Player p, Collection<ItemStack> items) {
        // PlayerInventory inv = p.getInventory();
        for (ItemStack is : items) {
            removeItem(p.getInventory(), is);
        }
    }

    public static int first(Inventory inv, ItemStack is1) {
        if (is1 == null) {
            return -1;
        }
        ItemStack[] inventory = inv.getContents();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack is2 = inventory[i];
            if (is2 == null) {
                continue;
            }
            if (is1.getType() == is2.getType()
                    && is1.getDurability() == is2.getDurability()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is nearly a direct copy of the removeItem from CraftBukkit The
     * difference is my ItemStack == ItemStack comparison (found in first())
     * there I change it to go by itemid and datavalue as opposed to itemid and
     * quantity
     *
     * @param inv
     * @param items
     * @return
     */
    public static HashMap<Integer, ItemStack> removeItem(Inventory inv,
            ItemStack... items) {
        HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            int toDelete = item.getAmount();

            while (true) {
				// System.out.println("inv= " + inv + "   " + items.length +
                // "    item=" + item);
                int first = first(inv, item);
				// System.out.println("first= " + first);

                // Drat! we don't have this type in the inventory
                if (first == -1) {
                    item.setAmount(toDelete);
                    leftover.put(i, item);
                    break;
                } else {
                    ItemStack itemStack = inv.getItem(first);
                    int amount = itemStack.getAmount();

                    if (amount <= toDelete) {
                        toDelete -= amount;
                        // clear the slot, all used up
                        inv.setItem(first, null);
                    } else {
                        // split the stack and store
                        itemStack.setAmount(amount - toDelete);
                        inv.setItem(first, itemStack);
                        toDelete = 0;
                    }
                }

                // Bail when done
                if (toDelete <= 0) {
                    break;
                }
            }
        }
        return leftover;
    }

    public static boolean isItem(String str){
        try {
            ItemStack item = parseItem(str);
            if (item == null)
                item = parseItemShort(str);
            if (item != null)
                return true;

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSameMaterial(ArmorLevel lvl, ItemStack is) {
        Armor a = armor.get(is.getType());
        return a != null && a.level == lvl;
    }

    public static ItemStack parseItem(String str) throws Exception{
        /// items in yaml get stored like this {leather_chest=fireprot:5 1}
        /// so need to remove the {} and the first '='
        if (str.contains("{"))
            str = str.replaceFirst("=", " ");
        str = str.replaceAll("[}{]", "");

        if (DEBUG) Bukkit.getLogger().info("item=" + str);

        String ownerName = parseOwner(str);
        if(ownerName != null) //We have lore, so strip it.
            str = PATTERN_OWNER.matcher(str).replaceFirst("");

        String displayName = parseDisplayName(str);
        if(displayName != null) //We have lore, so strip it.
            str = PATTERN_DISPLAY_NAME.matcher(str).replaceFirst("");

        Color c = parseColor(str);
        if (c != null) /// we have color, so strip it
            str = PATTERN_COLOR.matcher(str).replaceFirst("");

        Integer pos = parsePosition(str);
        if (pos != null) /// we have position, so strip it
            str = PATTERN_POSITION.matcher(str).replaceFirst("");

        Integer modelData = parseModelData(str);
        if (modelData != null)
            str = PATTERN_MODEL_DATA.matcher(str).replaceFirst("");

        Boolean unbreakable = parseUnbreakable(str);
        if (unbreakable != null)
            str = PATTERN_UNBREAKABLE.matcher(str).replaceFirst("");

        List<PotionEffect> effects = parseEffects(str);
        if (effects != null)  // we have the effect, so strip it
            str = PATTERN_EFFECT.matcher(str).replaceFirst("");

        /// Parse Lore (thanks to Netherfoam)
        List<String> lore = parseLore(str);
        if(lore != null) //We have lore, so strip it.
            str = PATTERN_LORE.matcher(str).replaceFirst("");

        ItemStack is;
        String split[] = str.split(" +");
        is = InventoryUtil.getItemStack(split[0].trim());

        if (is == null)
            return null;
        int amt;
        if (split.length > 1){
            try {
                amt = Integer.valueOf(split[split.length-1]);
            } catch (Exception e){
                amt = 1;
            }
        } else {
            amt = 1;
        }
        is.setAmount(amt);

        if (lore != null && !lore.isEmpty())
            handler.setLore(is,lore);

        if (c != null)
            handler.setColor(is, c);

        if (displayName != null)
            handler.setDisplayName(is,displayName);

        if (ownerName != null)
            handler.setOwnerName(is,ownerName);

        if (modelData != null)
            handler.setCustomModelData(is, modelData);

        if (unbreakable != null)
            handler.setUnbreakable(is, unbreakable);

        if (effects != null) {
            for (PotionEffect effect : effects) {
                handler.addCustomEffect(is, effect);
            }
        }

        for (int i = 1; i < split.length-1;i++){
            EnchantmentWithLevel ewl = getEnchantment(split[i].trim());
            if (ewl == null){
                throw new IllegalArgumentException(" enchantment " + split[i].trim() +" does not exist");
            }
            try {
                is.addUnsafeEnchantment(ewl.enchant, ewl.lvl);
            } catch (IllegalArgumentException iae){
                Bukkit.getLogger().warning(ewl+" can not be applied to the item " + str);
            }
        }

        return is;
    }

    public static Integer parsePosition(String str) {
        Matcher matcher = PATTERN_POSITION.matcher(str);
        if (!matcher.find())
            return null;

        return Integer.valueOf(matcher.group(1));
    }

    public static Boolean parseUnbreakable(String str) {
        Matcher matcher = PATTERN_UNBREAKABLE.matcher(str);
        if (!matcher.find())
            return null;

        return Boolean.valueOf(matcher.group(1));
    }

    public static Integer parseModelData(String str) {
        Matcher matcher = PATTERN_MODEL_DATA.matcher(str);
        if (!matcher.find())
            return null;

        return Integer.valueOf(matcher.group(1));
    }

    public static List<PotionEffect> parseEffects(String str) {
        Matcher matcher = PATTERN_EFFECT.matcher(str);
        if (!matcher.find())
            return null;

        List<PotionEffect> effects = new ArrayList<PotionEffect>();
        for (String eff : matcher.group(1).replace(" ", "").split(",")) {
            effects.add(EffectUtil.parseArg(eff, 0, 120));
        }
        return effects;
    }

    public static Color parseColor(String str) {
        Matcher matcher = PATTERN_COLOR.matcher(str);
        if (!matcher.find())
            return null;

        return new Color(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)));
    }

    public static String parseOwner(String str) {
        Matcher matcher = PATTERN_OWNER.matcher(str);
        if (!matcher.find())
            return null;

        return matcher.group(1);
    }

    public static String parseDisplayName(String str) {
        Matcher matcher = PATTERN_DISPLAY_NAME.matcher(str);
        if (!matcher.find())
            return null;

        return ChatColor.translateAlternateColorCodes('&', matcher.group(1));
    }

    public static LinkedList<String> parseLore(String str) {
        try {
            Matcher matcher = PATTERN_LORE.matcher(str);
            if (matcher.find()) {
                //Replace color codes
                String part = ChatColor.translateAlternateColorCodes('&', matcher.group(1));
                //Now we can split it.
                String[] lines = part.split("([;\\n]|\\\\n)");
                //DEBUG
                if(DEBUG)
                    Bukkit.getLogger().info(Arrays.toString(lines));
                //Create a new list
                LinkedList<String> lore = new LinkedList<String>();
                //Add all the sections to the list
                Collections.addAll(lore, lines);
                //Success!
                return lore;
            }
        } catch(Exception e){
           e.printStackTrace(); //Damn.
        }
        return null;
    }

    public static boolean isSameItem(ItemStack item1, ItemStack item2) {
        return compareItem(item1, item2) == 0;
    }

    public static int compareItem(ItemStack item1, ItemStack item2) {
        if (item1 == null && item2 == null)
            return 0;
        if (item1 == null)
            return 1;
        if (item2 == null)
            return -1;
        Integer i = item1.getType().getId();
        Integer i2 = item2.getType().getId();
        if (i== Material.AIR.getId() && i2 == Material.AIR.getId()) return 0;
        if (i == Material.AIR.getId()) return 1;
        if (i2 == Material.AIR.getId()) return -1;

        int c = i.compareTo(i2);
        if (c!= 0)
            return c;
        Short s= item1.getDurability();
        c = s.compareTo(item2.getDurability());
        if (c!= 0)
            return c;
        i = item1.getAmount();
        c = i.compareTo(item2.getAmount());
        if (c!= 0)
            return c;
        Map<Enchantment, Integer> e1 = item1.getEnchantments();
        Map<Enchantment, Integer> e2 = item1.getEnchantments();
        i = e1.size();
        c = i.compareTo(e2.size());
        if (c!=0)
            return c;
        for (Enchantment e: e1.keySet()){
            if (!e2.containsKey(e))
                return -1;
            i = e1.get(e);
            i2 = e2.get(e);
            c = i.compareTo(i2);
            if (c != 0)
                return c;
        }
        return 0;
    }

    public static boolean isSameItems(List<ItemStack> items, PlayerInventory inv, boolean woolTeams) {
        ItemStack[] contents =inv.getContents();
        ItemStack[] armor = inv.getArmorContents();
        /// This is a basic check to make sure we have the same number of items, and same total durability
        /// Even with the 3 loops b/c there is no creation or sorting this is orders of magnitude faster
        /// and takes almost no time.
        int nitems1 =0, nitems2=0;
        int dura1=0, dura2=0;

        for (ItemStack is: items){
            if (is == null || is.getType() == Material.AIR)
                continue;
            nitems1 += is.getAmount();
            dura1 += is.getDurability()*is.getAmount();
        }
        for (ItemStack is: contents){
            if (is == null || is.getType() == Material.AIR)
                continue;
            nitems2 += is.getAmount();
            dura2 += is.getDurability()*is.getAmount();
        }
        if (!woolTeams){
            for (ItemStack is: armor){
                if (is == null || is.getType() == Material.AIR)
                    continue;
                nitems2 += is.getAmount();
                dura2 += is.getDurability()*is.getAmount();
            }
        } else {
            ItemStack is = inv.getHelmet();
            if (is != null && is.getType() != Material.AIR && InventoryUtil.isRealArmor(is)){
                nitems2 += is.getAmount();
                dura2 += is.getDurability()*is.getAmount();
            }
            is = inv.getBoots();
            if (is != null && is.getType() != Material.AIR){
                nitems2 += is.getAmount();
                dura2 += is.getDurability()*is.getAmount();
            }
            is = inv.getLeggings();
            if (is != null && is.getType() != Material.AIR){
                nitems2 += is.getAmount();
                dura2 += is.getDurability()*is.getAmount();
            }
            is = inv.getChestplate();
            if (is != null && is.getType() != Material.AIR){
                nitems2 += is.getAmount();
                dura2 += is.getDurability()*is.getAmount();
            }
        }
        if (DEBUG) Bukkit.getLogger().info("nitems1  " + nitems1 +":" + nitems2+"      " + dura1 +"  : " + dura2);
        if (nitems1 != nitems2 || dura1 != dura2)
            return false;
//        List<ItemStack> pitems = new ArrayList<ItemStack>();
//
//		/// Now that the basic check is over, the more intensive one starts
//		//// I could check size right now if it werent for "air" and null blocks in inventories
//		List<ItemStack> pitems = new ArrayList<ItemStack>();
//		pitems.addAll(Arrays.asList(contents));
//		if (woolTeams){ /// ignore helmet maybe
//			if (inv.getHelmet() != null && InventoryUtil.isRealArmor(inv.getHelmet())) pitems.add(inv.getHelmet());
//
//			if (inv.getBoots() != null) pitems.add(inv.getBoots());
//			if (inv.getLeggings() != null) pitems.add(inv.getLeggings());
//			if (inv.getChestplate() != null) pitems.add(inv.getChestplate());
//		} else {
//			pitems.addAll(Arrays.asList(armor));
//		}
//		List<ItemStack> stacks = new ArrayList<ItemStack>();
//		for (ItemStack is: items){
//			if (is == null || is.getType() == Material.AIR)
//				continue;
//            final int max = 64;
//			if (DEBUG)Log.info(" iss   " + is.getAmount() +"   " + is.getMaxStackSize() +"    " + is +" max="+max);
//            if (is.getAmount() > max){
//				is = is.clone();
//				while (is.getAmount() > max){
//					is.setAmount(is.getAmount() - max);
//					ItemStack is2 = new ItemStack(is);
//					is2.setAmount(max);
//					stacks.add(is2);
//				}
//			}
//			stacks.add(is);
//		}
//		items = stacks;
//		Collections.sort(items, new ItemComparator());
//		Collections.sort(pitems, new ItemComparator());
//		int idx = 0;
//		ItemStack is1, is2;
//
//		while (idx< items.size() && idx<pitems.size()){
//			is1 = items.get(idx);
//			is2 = pitems.get(idx);
//			if (DEBUG) Log.info(idx  +" : " + is1 +"  " + is2);
//			if ((is1==null || is1.getType() == Material.AIR) && (is2 == null || is2.getType() == Material.AIR))
//				return true;
//			if ((is1==null || is1.getType() == Material.AIR) || (is2 == null || is2.getType() == Material.AIR))
//				return false;
//			/// Alright, now that we dont have to worry about null or air
//			if (!is1.equals(is2))
//				return false;
//			idx++;
//		}
//		/// Arrays are similar up until the smallest array
//		/// If any array has more elements that are not null, then they are not equal
//		for (int i=idx;i<items.size();i++){
//			is1 = items.get(i);
//			if (is1 != null && is1.getType() != Material.AIR)
//				return false;
//		}
//		for (int i=idx;i<pitems.size();i++){
//			is2 = pitems.get(i);
//			if (is2 != null && is2.getType() != Material.AIR)
//				return false;
//		}
        return true;
    }

    public static void addToInventory(Player p, PInv pinv) {
        try{
            PlayerInventory inv = p.getPlayer().getInventory();
            for (ArmorType armor : ArmorType.values()){
                final int ord = armor.ordinal();
                if (ord >= pinv.armor.length)
                    break;
                final ItemStack newArmor = pinv.armor[ord];
                if (newArmor == null || newArmor.getType()== Material.AIR)
                    continue;
                final ItemStack oldArmor = getArmorSlot(inv,armor);
                boolean empty = (oldArmor == null || oldArmor.getType() == Material.AIR);
                if (empty) {
                    switch(armor){
                        case HELM: inv.setHelmet(newArmor); break;
                        case CHEST: inv.setChestplate(newArmor); break;
                        case LEGGINGS: inv.setLeggings(newArmor); break;
                        case BOOTS: inv.setBoots(newArmor); break;
                    }
                } else {
                    addItemToInventory(p, newArmor, newArmor.getAmount(),false,false);
                }
            }
            inv.setContents(pinv.contents);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        try {p.getPlayer().updateInventory(); } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * For Serializing an item or printing
     * @param is ItemStack
     * @return String
     */
    public static String getItemString(ItemStack is) {
        StringBuilder sb = new StringBuilder();
        if (is.getDurability() > 0){
            sb.append(is.getType().toString()).append(":").append(is.getDurability()).append(" ");
        } else {
            sb.append(is.getType().toString()).append(" ");
        }

        Map<Enchantment,Integer> encs = is.getEnchantments();
        for (Enchantment enc : encs.keySet()){
            sb.append(enc.getName()).append(":").append(encs.get(enc)).append(" ");
        }

        Color color = handler.getColor(is);
        if (color != null)
            sb.append("color=").append(color.getRed()).append(",").
                    append(color.getGreen()).append(",").append(color.getBlue()).append(" ");
        String str = handler.getDisplayName(is);
        if (str != null && !str.isEmpty())
            sb.append("displayName=\"").append(str).append("\" ");

        str = handler.getOwnerName(is);
        if (str != null && !str.isEmpty())
            sb.append("owner=\"").append(str).append("\" ");

        int modelData = handler.getCustomModelData(is);
        if (modelData > 0)
            sb.append("modelData=\"").append(modelData).append("\" ");

        boolean unbreakable = handler.isUnbreakable(is);
        if (unbreakable)
            sb.append("unbreakable=\"").append(unbreakable).append("\" ");

        List<PotionEffect> effects = handler.getCustomEffects(is);
        if (effects != null && !effects.isEmpty()) {
            sb.append("effects=\"" + EffectUtil.getEnchantString(effects)).append("\" ");
        }

        // Parse lore last
        List<String> lore = handler.getLore(is);
        if (lore != null && !lore.isEmpty()){
            sb.append("lore=\"").append(StringUtils.join(lore, "\\n")).append("\" ");
        }

        sb.append(is.getAmount());
        return sb.toString();
    }

    public static List<ItemStack> getItemList(Player player){
        List<ItemStack> items = new ArrayList<ItemStack>();
        for (ItemStack item: player.getInventory().getArmorContents()){
            if (item != null && item.getType() != Material.AIR){
                items.add(item);}
        }
        for (ItemStack item: player.getInventory().getContents()){
            if (item != null && item.getType() != Material.AIR){
                items.add(item);}
        }
        return items;
    }

    public static ArrayList<ItemStack> getItemList(ConfigurationSection cs, String nodeString) {
        if (cs == null || cs.getList(nodeString) == null)
            return null;
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        try {
            String str = null;
            for (Object o : cs.getList(nodeString)){
                try {
                    str = o.toString();
                    ItemStack is = InventoryUtil.parseItem(str);
                    if (is != null){
                        items.add(is);
                    } else {
                        Bukkit.getLogger().severe(cs.getCurrentPath() +"."+nodeString + " couldnt parse item " + str);
                    }
                } catch (IllegalArgumentException ex) {
                    Bukkit.getLogger().severe(cs.getCurrentPath() +"."+nodeString + " couldnt parse item " + str);
                } catch (Exception ex){
                    Bukkit.getLogger().severe(cs.getCurrentPath() +"."+nodeString + " couldnt parse item " + str);
                }
            }
        } catch (Exception ex){
            Bukkit.getLogger().severe(cs.getCurrentPath() +"."+nodeString + " could not be parsed in config.yml");
            ex.printStackTrace();
        }
        return items;
    }

    public static void dropItems(Player player) {
        Location loc = player.getLocation();
        World w = loc.getWorld();
        PlayerInventory inv = player.getInventory();
        for (ItemStack is: inv.getArmorContents()){
            if (is == null || is.getType() == Material.AIR)
                continue;
            w.dropItemNaturally(loc, is);
        }
        for (ItemStack is: inv.getContents()){
            if (is == null || is.getType() == Material.AIR)
                continue;
            w.dropItemNaturally(loc, is);
        }
    }
}
