package xyz.pbsi.Commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.pbsi.Interfaces.FluxerCommand;
import xyz.pbsi.Listeners.MessageEvent;

import java.util.List;

import static xyz.pbsi.Main.PING;

public class Ping implements FluxerCommand {
    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        // The user who sent the message
        User author = event.getAuthor();
        // This is a special class called a "union", which allows you to perform specialization to more concrete types
        // such as TextChannel or NewsChannel
        MessageChannelUnion channel = event.getChannel();
        // The actual message sent by the user, this can also be a message the bot sent itself, since you *do* receive
        // your own messages after all
        Message message = event.getMessage();

        // Ping pong
        if (message.getContentDisplay().startsWith("!")) {
            if (message.getContentDisplay().contains("ping")) {
                message.addReaction(PING)
                        .queue();
                message.reply("Pong! ``Ping: " + event.getJDA().getGatewayPing() + "ms``")
                        .queue();
            }
        }
    }
}
