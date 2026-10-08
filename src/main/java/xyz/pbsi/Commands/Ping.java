package xyz.pbsi.Commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pbsi.Interfaces.FluxerCommand;

import java.util.List;

import static xyz.pbsi.Utils.Constants.prefix;


public class Ping implements FluxerCommand {
    private static final Logger log = LoggerFactory.getLogger(Ping.class);

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        Emoji PING = Emoji.fromFormatted("❗");
        Message message = event.getMessage();

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
