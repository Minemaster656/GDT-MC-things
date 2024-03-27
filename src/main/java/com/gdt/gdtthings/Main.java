package com.gdt.gdtthings;

import com.gdt.gdtthings.handler.SimpleEventHandler;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin implements Listener {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

//        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new SimpleEventHandler(), this);

//        getServer().getPluginCommand("idk").setExecutor(new IDK());
//        getServer().getPluginCommand("posp").setExecutor(new POSpCommand());
    }

    @Override
    public @NotNull ComponentLogger getComponentLogger() {
        return super.getComponentLogger();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
//        getServer().getLogger().info("POSP disabled");
    }
    @EventHandler
    public void handleJoinEvent(PlayerJoinEvent event){
        HandlerList handlerList = event.getHandlerList();
        Player player = event.getPlayer();

        player.sendMessage("§1Привет!");
        getServer().dispatchCommand(getServer().getConsoleSender(), "tellraw @a[name=MichaAI] {\"text\":\"Ты клоун!\"}");

        handlerList.unregister((Listener) this);
    }

}
