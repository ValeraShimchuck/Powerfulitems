package com.pfitems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantment extends Enchantment {
    public CustomEnchantment(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Авто-плавка";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        if(enchantment.equals(Enchantment.SILK_TOUCH)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        if(itemStack.getType().equals(Material.DIAMOND_PICKAXE) || itemStack.getType().equals(Material.GOLDEN_PICKAXE) || itemStack.getType().equals(Material.IRON_PICKAXE) || itemStack.getType().equals(Material.STONE_PICKAXE) || itemStack.getType().equals(Material.WOODEN_PICKAXE) || itemStack.getType().equals(Material.BOOK)) return true;
        else return false;
    }
}
