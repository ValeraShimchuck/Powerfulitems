package com.pfitems;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Handler implements Listener {
    private Random rand;
    private final Main plugin;
    public Handler(Main plugin){this.plugin = plugin;}

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        //ItemStack item = new ItemStack(Material.BOOK);
        //item.addUnsafeEnchantment(plugin.ench,1);
        //p.getWorld().dropItem(p.getLocation(),item);
    }
    @EventHandler
    public void craftEvent(PrepareItemCraftEvent e){
        //plugin.getLogger().info("pass1");
        ItemStack[] matrix = e.getInventory().getMatrix();

        if(e.getInventory().getResult() != null){
            plugin.getLogger().info("pass1");
            if(e.getInventory().getResult().equals(plugin.crafts.itemPickaxeOfPower())){
                plugin.getLogger().info("pass2");
                for(ItemStack item: matrix){
                    if(item != null)if(item.getType().equals(Material.ENDER_EYE)){
                        plugin.getLogger().info("pass3");
                        if(!item.equals(plugin.crafts.itemCoreOfPower())){
                            e.getInventory().setResult(null);
                        }
                    }
                    //plugin.getLogger().info(String.valueOf(item.getType()));

                }
            }
        }


    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.getPlayer().getInventory().getItemInMainHand();
        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        if(mainHand.getType().equals(Material.DIAMOND_PICKAXE)){
            ItemMeta pickaxeMeta = mainHand.getItemMeta();
            List<String> pickLore = new ArrayList<>();
            pickLore.add("Pickaxe Of Power");
            if(pickaxeMeta.getDisplayName().equals(ChatColor.GOLD+"Pickaxe Of Power") && pickaxeMeta.getLore().equals(pickLore)){
                Location loc = e.getBlock().getLocation();
                //Location[] blocksLoc = {loc.add(-1,0,-1),loc.add(-1,0,0),loc.add(-1,0,1),loc.add(0,0,-1),loc.add(0,0,0),loc.add(0,0,1),loc.add(1,0,-1),loc.add(1,0,0),loc.add(1,0,1)};
                //Set<Block> blocks = new HashSet<Block>();
                plugin.getLogger().info("--------------------------------------");
                rand = new Random();
                toBreak(e.getBlock(),e.getPlayer(),pickaxeMeta);
                plugin.getLogger().info("--------------------------------------");

                //plugin.getLogger().info(String.valueOf(blocks));
                mainHand.setDurability((short) (mainHand.getDurability()+1));
                e.setCancelled(true); // temp
            }
        }
    }
    private void toBreak(Block b,Player p, ItemMeta meta){
        if(vert(p)) Vertical(b,meta);
        else if(horiX(p)) HorizontalX(b,meta);
        else if(horiZ(p)) HorizontalZ(b,meta);
    }
    private Boolean vert(Player p){
        Location loc = p.getLocation();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        if(pitch < -40 || pitch > 40) return true;
        else return false;
    }
    private Boolean horiX(Player p){
        Location loc = p.getLocation();
        float yaw = loc.getYaw();
        float y = toDegree(yaw);
        if(y > 45 && y < 135 || y > 225 && y < 315) {
            return true;
        }
        return false;
    }
    private Boolean horiZ(Player p){
        Location loc = p.getLocation();
        float yaw = loc.getYaw();
        float y = toDegree(yaw);
        if(y > 315 || y < 45 || y > 135 && y < 225) {
            return true;
        }
        return false;
    }
    private Float toDegree(float yaw){
        if(yaw <= 360 && yaw >= 0) return yaw;
        while(!(yaw <= 360) || !(yaw >= 0)){
            if(yaw > 360)yaw = yaw - 360;
            if(yaw < 0)yaw = yaw + 360;
        }
        return yaw;
    }

    private void Vertical(Block center, ItemMeta meta){
        for(int x=-1; x<2;x++)
            for(int z=-1; z<2;z++){
                Block b = center.getRelative(x,0,z);
                postEnch(meta,b);
                }
            }
    private void HorizontalX(Block center, ItemMeta meta){
        for(int y=-1; y<2;y++)
            for(int z=-1; z<2;z++){
                Block b = center.getRelative(0,y,z);
                postEnch(meta,b);
            }
    }
    private void HorizontalZ(Block center, ItemMeta meta){
        for(int y=-1; y<2;y++)
            for(int x=-1; x<2;x++){
                Block b = center.getRelative(x,y,0);
                postEnch(meta,b);
            }
    }
    private void postEnch(ItemMeta meta, Block b){
        Map<Enchantment, Integer> enchants = meta.getEnchants();
        //plugin.getLogger().info("pass");
        if(enchants.containsKey(Enchantment.SILK_TOUCH)) dropSilkItems(b);
        else if(enchants.containsKey(Enchantment.LOOT_BONUS_BLOCKS)){
            Integer enchLvl = enchants.get(Enchantment.LOOT_BONUS_BLOCKS);
            dropLootItems(b,enchLvl);
        }else dropItemsBlock(b);
    }
    private void dropItemsBlock(Block b){
        //plugin.getLogger().info("pass");
        if(!b.isLiquid() && !b.getType().equals(Material.BEDROCK)){
            //plugin.getLogger().info("pass");
            Collection<ItemStack> items = b.getDrops();
            //plugin.getLogger().info(String.valueOf(items));
            for(ItemStack item: items){
                b.getWorld().dropItem(b.getLocation(),item);
            }
            b.setType(Material.AIR);
    }
    }
    private void dropSilkItems(Block b){
        if(!b.isLiquid() && !b.getType().equals(Material.BEDROCK) && !b.getType().equals(Material.AIR)){
            //plugin.getLogger().info("pass");
            Material material = b.getType();
            ItemStack item = new ItemStack(material,1);
            //plugin.getLogger().info(String.valueOf(items));
            b.getWorld().dropItem(b.getLocation(),item);

            b.setType(Material.AIR);
        }
    }
    private void dropLootItems(Block b, Integer i){
        Collection<Material> lootItemBlock = new ArrayList<>();
        lootItemBlock.add(Material.COAL_ORE); lootItemBlock.add(Material.DIAMOND_ORE); lootItemBlock.add(Material.EMERALD_ORE); lootItemBlock.add(Material.LAPIS_ORE); lootItemBlock.add(Material.NETHER_QUARTZ_ORE); lootItemBlock.add(Material.REDSTONE_ORE);
        if(!b.isLiquid() && !b.getType().equals(Material.BEDROCK)){
            //plugin.getLogger().info("pass");
            Collection<ItemStack> items = b.getDrops();
            if(lootItemBlock.contains(b.getType())){
                //Random rand = new Random();
                for(ItemStack item: items){

                    if(i == 1){
                        plugin.getLogger().info("Luck 1");
                        if(rand.nextInt(3) == 0){
                            ItemStack nItem = item;
                            nItem.setAmount(nItem.getAmount()+1);
                            b.getWorld().dropItem(b.getLocation(),nItem);
                        }else b.getWorld().dropItem(b.getLocation(),item);
                    }
                    else if(i==2){
                        plugin.getLogger().info("Luck 2");
                        if(rand.nextInt(2)== 0){
                            if(rand.nextInt(2)==0){
                                ItemStack nItem = item;
                                nItem.setAmount(nItem.getAmount()+2);
                                b.getWorld().dropItem(b.getLocation(),nItem);
                            }else{
                                ItemStack nItem = item;
                                nItem.setAmount(nItem.getAmount()+1);
                                b.getWorld().dropItem(b.getLocation(),nItem);
                            }
                        }else b.getWorld().dropItem(b.getLocation(),item);
                    }
                    else if(i >=3){
                        plugin.getLogger().info("Luck 3");
                        if(rand.nextInt(5) > 1){
                            Integer randNum = rand.nextInt(3);
                            if(randNum == 0){
                                ItemStack nItem = item;
                                nItem.setAmount(nItem.getAmount()+1);
                                b.getWorld().dropItem(b.getLocation(),nItem);
                                plugin.getLogger().info(String.valueOf(nItem)+" 20%");
                            }
                            if(randNum == 1){
                                ItemStack nItem = item;
                                nItem.setAmount(nItem.getAmount()+2);
                                b.getWorld().dropItem(b.getLocation(),nItem);
                                plugin.getLogger().info(String.valueOf(nItem)+" 20%");
                            }
                            if(randNum == 2){
                                ItemStack nItem = item;
                                nItem.setAmount(nItem.getAmount()+3);
                                b.getWorld().dropItem(b.getLocation(),nItem);
                                plugin.getLogger().info(String.valueOf(nItem)+" 20%");
                            }


                        } else {b.getWorld().dropItem(b.getLocation(),item);
                            plugin.getLogger().info("1 40%");
                        }
                    }else b.getWorld().dropItem(b.getLocation(),item);
                    //b.getWorld().dropItem(b.getLocation(),item);

                }
            }else for(ItemStack item: items){
                b.getWorld().dropItem(b.getLocation(),item);
            }
            b.setType(Material.AIR);
        }
    }

}
