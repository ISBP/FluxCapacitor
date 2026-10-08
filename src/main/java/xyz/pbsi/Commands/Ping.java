package xyz.pbsi.Commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pbsi.Interfaces.FluxerCommand;
import xyz.pbsi.Listeners.MessageEvent;

import java.util.List;

import static xyz.pbsi.Utils.Constants.prefix;


public class Ping implements FluxerCommand {
    private static final Logger log = LoggerFactory.getLogger(Ping.class);

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        Emoji PING = Emoji.fromFormatted("❗");

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

    @Override
    public String description() {
        return "Displays the latency between the bot and Fluxer servers! Usage ``" + prefix + " ping``";
    }
}
