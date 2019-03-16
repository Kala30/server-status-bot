package com.cogentworks.yuncraft;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ReadyListener extends ListenerAdapter {
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
            }
        }
    }
}
