package xyz.pbsi.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.pbsi.Interfaces.FluxerCommand;

import java.util.List;

public class Help implements FluxerCommand {
    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        if(args.isEmpty())
        {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Help");
            eb.setDescription("1. `!fc ping`\n2. `!fc help`");
            event.getMessage().replyEmbeds(eb.build()).queue();
        }else {
            event.getMessage().reply(args.getFirst()).queue();
        }
    }
}
