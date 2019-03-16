package com.cogentworks.yuncraft;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class UpdateListener implements Listener {

    JDA jda;

    public UpdateListener(JDA jda) {
        this.jda = jda;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        jda.getPresence().setGame(Game.playing(getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent e) {
        jda.getPresence().setGame(Game.playing(getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers()));
    }
}
