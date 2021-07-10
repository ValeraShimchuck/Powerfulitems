package com.pfitems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Crafts {
    private final Main plugin;
    public Crafts(Main plugin) {
        this.plugin = plugin;
    }
    public void craftPowerCore(){
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED+"Core Of Power");
        List<String> lore = new ArrayList<>();
        lore.add("Core Of Power");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(plugin, "CoreOfPower");
        ShapedRecipe s = new ShapedRecipe(key,item);
        s.shape(new String[]{"ABA","BCB","ABA"});
        s.setIngredient('A',Material.EMERALD_BLOCK);
        s.setIngredient('B',Material.DIAMOND_BLOCK);
        s.setIngredient('C',Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(s);
    }

    public void craftPowerPickaxe(){
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta = pickaxe.getItemMeta();
        pickMeta.setDisplayName(ChatColor.GOLD+"Pickaxe Of Power");
        List<String> pickLore = new ArrayList<>();
        pickLore.add("Pickaxe Of Power");
        pickMeta.setLore(pickLore);
        pickMeta.addEnchant(Enchantment.DURABILITY, 3, true);
        pickaxe.setItemMeta(pickMeta);

        NamespacedKey key = new NamespacedKey(plugin, "PickaxeOfPower");
        ShapedRecipe s = new ShapedRecipe(key,pickaxe);
        s.shape(new String[]{"ABA"," C ", " C "});
        s.setIngredient('A', Material.EMERALD_BLOCK);
        s.setIngredient('B', Material.ENDER_EYE);
        s.setIngredient('C',Material.STICK);
        Bukkit.getServer().addRecipe(s);


    }
    public ItemStack itemCoreOfPower(){
        ItemStack core = new ItemStack(Material.ENDER_EYE);
        ItemMeta coreMeta = core.getItemMeta();
        coreMeta.setDisplayName(ChatColor.RED+"Core Of Power");
        List<String> lore = new ArrayList<>();
        lore.add("Core Of Power");
        coreMeta.setLore(lore);
        coreMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        core.setItemMeta(coreMeta);
        return core;
    }
    public ItemStack itemPickaxeOfPower(){
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta = pickaxe.getItemMeta();
        pickMeta.setDisplayName(ChatColor.GOLD+"Pickaxe Of Power");
        List<String> pickLore = new ArrayList<>();
        pickLore.add("Pickaxe Of Power");
        pickMeta.setLore(pickLore);
        pickMeta.addEnchant(Enchantment.DURABILITY, 3, true);
        pickaxe.setItemMeta(pickMeta);
        return pickaxe;
    }
}
