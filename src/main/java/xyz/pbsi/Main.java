package xyz.pbsi;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import xyz.pbsi.Commands.Help;
import xyz.pbsi.Commands.Ping;
import xyz.pbsi.Listeners.MessageEvent;

import java.util.EnumSet;

import static xyz.pbsi.Utils.CommandManager.registerCommand;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        /* Token */
        // 1. Using environment variable:
        String token = System.getenv("TOKEN");

        EnumSet<GatewayIntent> intents = EnumSet.of(
                // Enables MessageReceivedEvent for guild (also known as servers)
                GatewayIntent.GUILD_MESSAGES,
                // Enables the event for private channels (also known as direct messages)
                GatewayIntent.DIRECT_MESSAGES,
                // Enables access to message.getContentRaw()
                GatewayIntent.MESSAGE_CONTENT,
                // Enables MessageReactionAddEvent for guild
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                // Enables MessageReactionAddEvent for private channels
                GatewayIntent.DIRECT_MESSAGE_REACTIONS);

        try {
            JDA jda = JDABuilder.createLight(token, intents)
                    // On this builder, you are adding all your event listeners and session configuration
                    .addEventListeners(new MessageEvent())
                    // Once you're done configuring your jda instance, call build to start and login the bot.
                    .build();

            jda.awaitReady();
            registerCommand("ping", new Ping());
            registerCommand("help", new Help());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}