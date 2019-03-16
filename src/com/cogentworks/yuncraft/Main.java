package com.cogentworks.yuncraft;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main extends JavaPlugin {

    public static JDA jda;

    @Override
    public void onEnable() {
        saveDefaultConfig();



        try {
            String token = getConfig().getString("token");

            jda = new JDABuilder(token).setGame(Game.playing(getPlayerCount())).build();
            jda.addEventListener(new ReadyListener());
            TextChannel channel = jda.getTextChannelById(getConfig().getString("channel-id"));
            channel.sendMessage(infoEmbed("Yuncraft is online", getServer().getMotd())).queue();

            getServer().getPluginManager().registerEvents(new UpdateListener(jda), this);

        } catch (LoginException e) {
            getLogger().info("JDA Login Failed");
        }

    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

    private static MessageEmbed infoEmbed(String title, String msg) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setColor(new Color(0x4CAF50));
        eb.setDescription(msg);
        return eb.build();
    }

    public String getPlayerCount() {
        return getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers();
    }
}