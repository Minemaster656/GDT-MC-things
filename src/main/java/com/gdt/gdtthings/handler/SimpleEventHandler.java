package com.gdt.gdtthings.handler;


import com.gdt.gdtthings.Main;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class SimpleEventHandler implements Listener {
    @EventHandler
    public void handleBlockBreakEvent(BlockBreakEvent e) {
        Location loc = e.getBlock().getLocation();
        Player plr = e.getPlayer();
//        if (e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
////            e.getPlayer().sendMessage("Обеме");
//
//            if (Math.random() > 0.85) {
//                e.setCancelled(true);
//                e.getBlock().setType(Material.AIR);
//                ItemStack diamonds = new ItemStack(Material.DIAMOND, 2);
//
//                Location loc = e.getBlock().getLocation();
//                e.getBlock().getWorld().dropItemNaturally(loc, CustomItemFactory.create_warpcrystal());
//                e.getBlock().getWorld().dropItemNaturally(loc, diamonds);
//                e.getBlock().getWorld().spawnParticle(Particle.DOLPHIN, loc, 32, 0.5, 1, 1);
//                e.getBlock().getWorld().playSound(loc, Sound.ENTITY_ENDER_EYE_DEATH, 1, 1);
//
//            }
//        }
        if (!plr.isSneaking()){
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            areaBreaking(loc, plr, 2, e);
        }
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE) {
            areaBreaking(loc, plr, 1, e);
        }}
    }

    public void areaBreaking(Location loc, Player plr, int mining_area, BlockBreakEvent e) {
//        if (plr.getItemInHand().getDurability() < plr.getItemInHand().()/10 & plr.getGameMode() != GameMode.CREATIVE){
//            e.getBlock().getWorld().playSound(loc, Sound.BLOCK_BONE_BLOCK_PLACE, 1, 1);
//            plr.sendActionBar("§4Инструмент вот-вот сломается!");
//        }
        e.getBlock().getWorld().playSound(loc, Sound.BLOCK_BASALT_BREAK, 1, 2);
//        Main.plugin.getServer().broadcastMessage("DX: "+ Math.abs(loc.getBlockX() - plr.getLocation().getBlockX())+" DZ: "+Math.abs(loc.getBlockZ() - plr.getLocation().getBlockZ()));
        if (plr.getLocation().getBlockY() > loc.getBlockY() || plr.getLocation().getBlockY() + mining_area < loc.getBlockY()) {
//            e.setCancelled(true);
//                e.getBlock().getWorld().getBlockAt(loc).breakNaturally(plr.getInventory().getItemInMainHand());
            for (int x = loc.getBlockX() - mining_area; x <= loc.getBlockX() + mining_area; x++) {
                for (int z = loc.getBlockZ() - mining_area; z <= loc.getBlockZ() + mining_area; z++) {
                    Location tloc = new Location(loc.getWorld(), x, loc.getBlockY(), z);
                    breakBlockAt(tloc, plr);
                }
            }

        } else if (Math.abs(loc.getBlockX() - plr.getLocation().getBlockX()) > Math.abs(loc.getBlockZ() - plr.getLocation().getBlockZ())) {

            e.setCancelled(true);
//            breakBlockAt(loc, plr);
            int x = loc.getBlockX();
            for (int z = loc.getBlockZ() - mining_area; z <= loc.getBlockZ() + mining_area; z++) {
                for (int y = loc.getBlockY() - 1; y <= loc.getBlockY() + (2 * mining_area) - 1; y++) {
                    breakBlockAt(new Location(loc.getWorld(), x, y, z), plr);
                }
            }

        } else if (Math.abs(loc.getBlockX() - plr.getLocation().getBlockX()) < Math.abs(loc.getBlockZ() - plr.getLocation().getBlockZ())) {
            e.setCancelled(true);
//            breakBlockAt(loc, plr);
            int z = loc.getBlockZ();
            for (int x = loc.getBlockX() - mining_area; x <= loc.getBlockX() + mining_area; x++) {
                for (int y = loc.getBlockY() - 1; y <= loc.getBlockY() + (2 * mining_area) - 1; y++) {
                    breakBlockAt(new Location(loc.getWorld(), x, y, z), plr);
                }
            }
        } else {
            e.setCancelled(true);
            breakBlockAt(loc, plr);
        }
    }

    public void breakBlockAt(Location loc, Player plr) {
        Material bm = loc.getWorld().getBlockAt(loc).getBlockData().getMaterial();
//        if (loc.getWorld().getBlockAt(loc).isValidTool(plr.getInventory().getItemInMainHand()) & bm.getHardness() >= 0) {
//                !(bm == Material.BEDROCK | bm == Material.BARRIER | bm == Material.COMMAND_BLOCK |
//                        bm == Material.REPEATING_COMMAND_BLOCK | bm == Material.CHAIN_COMMAND_BLOCK |
//                        bm == Material.JIGSAW | bm == Material.STRUCTURE_BLOCK | bm == Material.STRUCTURE_VOID |
//                        bm == Material.LIGHT| bm == Material.END_PORTAL | bm == Material.END_GATEWAY |
//                        bm == Material.END_PORTAL_FRAME)
        boolean isBreakable = !(bm == Material.BEDROCK | bm == Material.BARRIER | bm == Material.COMMAND_BLOCK |
                bm == Material.REPEATING_COMMAND_BLOCK | bm == Material.CHAIN_COMMAND_BLOCK |
                bm == Material.JIGSAW | bm == Material.STRUCTURE_BLOCK | bm == Material.STRUCTURE_VOID |
                bm == Material.LIGHT | bm == Material.END_PORTAL | bm == Material.END_GATEWAY |
                bm == Material.END_PORTAL_FRAME);
        if ((plr.getGameMode() == GameMode.ADVENTURE || plr.getGameMode() == GameMode.SURVIVAL) & bm.isBlock() & isBreakable & bm.getHardness() >= 0) {
            loc.getWorld().getBlockAt(loc).breakNaturally(plr.getInventory().getItemInMainHand());
//            try {
//                plr.getInventory().getItemInMainHand().damage(1, plr);
//            } catch (Exception e) {}
//            try {
//                plr.damageItemStack(plr.getInventory().getItemInMainHand(), 1);
//            } catch (Exception ex1) {
//                    ex1.printStackTrace();

//            try {

            short durability = plr.getInventory().getItemInMainHand().getDurability();
//            ItemMeta meta = plr.getInventory().getItemInMainHand().getItemMeta();
//                    MaterialData md = plr.getInventory().getItemInMainHand().getData();


            if (plr.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.DURABILITY)) {
                if (Math.random() * (plr.getInventory().getItemInMainHand().getEnchantments().get(Enchantment.DURABILITY) + 1) < 1) {
                    plr.getItemInHand().setDurability((short) (durability + 1));

                }
            } else if (!plr.getInventory().getItemInMainHand().getItemMeta().isUnbreakable()) {
                plr.getItemInHand().setDurability((short) (durability + 1));
            }
//            } catch (Exception ex) {
////                        ex.printStackTrace();
//            }


        //TODO: ПОФИКСИТЬ СРАНОЕ ПОВРЕЖДЕНИЕ ПРЕДМЕТОВ НА СПИГОТЕ 1.20.1
    } else if(plr.getGameMode()==GameMode.CREATIVE)

    {
        loc.getWorld().getBlockAt(loc).setType(Material.AIR);
    }

//        }
}


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //Give the player a chestplate with the enchantment when they join
        Player player = e.getPlayer();

    }

}
