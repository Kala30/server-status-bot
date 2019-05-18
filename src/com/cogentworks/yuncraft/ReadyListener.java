package com.cogentworks.yuncraft;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.awt.*;

import static org.bukkit.Bukkit.getLogger;

public class ReadyListener implements EventListener {

    JDA jda;
    Main plugin;

    public ReadyListener(Main plugin) {
        this.plugin = plugin;
        jda = Main.jda;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            TextChannel channel = jda.getTextChannelById(plugin.getConfig().getLong("channel-id"));
            if (channel != null)
                channel.sendMessage(infoEmbed(plugin.getConfig().getString("title"), plugin.getConfig().getString("online-msg"))).queue();
            else
                getLogger().info("Could not find channel id " + plugin.getConfig().getString("channel-id"));
        }
    }

    private static MessageEmbed infoEmbed(String title, String msg) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setColor(new Color(0x4CAF50));
        eb.setDescription(msg);
        return eb.build();
    }
}
