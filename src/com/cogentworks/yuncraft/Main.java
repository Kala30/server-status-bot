package com.cogentworks.yuncraft;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

import java.awt.*;

import static org.bukkit.Bukkit.getLogger;

public class Main extends JavaPlugin {

    public static JDA jda;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            String token = getConfig().getString("token");

            jda = new JDABuilder(token).setGame(Game.playing(getPlayerCount())).build();
            jda.addEventListener(new ReadyListener(this));
            jda.addEventListener(new MessageListener());

            getServer().getPluginManager().registerEvents(new UpdateListener(jda), this);


        } catch (LoginException e) {
            getLogger().info("JDA Login Failed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        TextChannel channel = jda.getTextChannelById(getConfig().getLong("channel-id"));
        if (channel != null)
            channel.sendMessage(offlineEmbed("Yuncraft", getConfig().getString("offline-msg"))).queue();
        else
            getLogger().info("Could not find channel id " + getConfig().getString("channel-id"));
    }

    public String getPlayerCount() {
        return getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers();
    }

    private static MessageEmbed offlineEmbed(String title, String msg) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setColor(new Color(0xf44336));
        eb.setDescription(msg);
        return eb.build();
    }
}