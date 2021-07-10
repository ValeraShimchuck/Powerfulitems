package com.pfitems;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Crafts crafts;
    private static NamespacedKey key = NamespacedKey.minecraft("auto_melting");
    public static CustomEnchantment ench = new CustomEnchantment(key);
    @Override
    public void onEnable() {
        /*try{
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) byKeyField.get(null);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            if(byKey.containsKey(key)) byKey.remove(key);
            if(byName.containsKey(getName())) byName.remove(getName());
        } catch (Exception ignored) {
            //e.printStackTrace();
        }
        try{
            try{
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                Enchantment.registerEnchantment(ench);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        crafts = new Crafts(this);
        getLogger().info("PFI enabled");
        Bukkit.getPluginManager().registerEvents(new Handler(this),this);
        getCommand("checkPFI").setExecutor(new Commands(this));
        crafts.craftPowerCore();
        crafts.craftPowerPickaxe();
    }
}
