package com.gdt.posp.handler;

import com.gdt.posp.Data;
import com.gdt.posp.factory.CustomItemFactory;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class SimpleEventHandler implements Listener {
    @EventHandler
    public void handleBlockBreakEvent(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
//            e.getPlayer().sendMessage("Обеме");

            if (Math.random() > 0.85) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                ItemStack diamonds = new ItemStack(Material.DIAMOND, 2);

                Location loc = e.getBlock().getLocation();
                e.getBlock().getWorld().dropItemNaturally(loc, CustomItemFactory.create_warpcrystal());
                e.getBlock().getWorld().dropItemNaturally(loc, diamonds);
                e.getBlock().getWorld().spawnParticle(Particle.DOLPHIN, loc, 32, 0.5, 1, 1);
                e.getBlock().getWorld().playSound(loc, Sound.ENTITY_ENDER_EYE_DEATH, 1, 1);

            }
        }
    }
}
