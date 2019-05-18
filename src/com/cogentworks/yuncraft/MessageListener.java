package com.cogentworks.yuncraft;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!") && !event.getAuthor().isBot()) {
            Message message = event.getMessage();
            String[] args = message.getContentRaw().replaceFirst("!", "").split(" ");
            MessageChannel channel = event.getChannel();

            switch (args[0]) {
                case "ping":
                    channel.sendMessage("Pong!").queue();
                    break;
                case "help":
                    channel.sendMessage(helpEmbed()).queue();
                    break;
                case "list":
                    channel.sendMessage(infoEmbed(
                            getServer().getOnlinePlayers().size() + "/" + getServer().getMaxPlayers() + "players online",
                            playerList()
                    )).queue();
                    break;
            }
        }
    }

    private static MessageEmbed infoEmbed(String title, String msg) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setColor(new Color(0x4CAF50));
        eb.setDescription(msg);
        return eb.build();
    }

    private static MessageEmbed helpEmbed() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Help");
        eb.setColor(new Color(0x009688));
        eb.addField("Online Players", "`!list`", false);
        eb.setFooter("Created by Kala30", "https://avatars2.githubusercontent.com/u/13771555");
        return eb.build();
    }

    private String playerList() {

        Collection players = getServer().getOnlinePlayers();

        Object[] playerArray = players.toArray();
        String[] nameList = new String[playerArray.length];

        for (int i = 0; i < playerArray.length; i++) {
            nameList[i] = ((Player)playerArray[i]).getDisplayName();
        }
        return Arrays.toString(nameList).replace("[", "").replace("]","");
    }
}
