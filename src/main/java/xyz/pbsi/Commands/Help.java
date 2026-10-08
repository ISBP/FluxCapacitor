package xyz.pbsi.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pbsi.Interfaces.FluxerCommand;

import java.util.List;

import static xyz.pbsi.Utils.CommandManager.commands;
import static xyz.pbsi.Utils.Constants.prefix;

public class Help implements FluxerCommand {
    private static final Logger log = LoggerFactory.getLogger(Help.class);

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        if(args.isEmpty())
        {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Help Info!");
            StringBuilder commandsList = new StringBuilder();
            int num = 1;
            for(String command : commands.keySet())
            {
                commandsList.append(num).append(". ``").append(prefix).append(" ").append(command).append("``").append("\n");
            }
            eb.setDescription(String.valueOf(commandsList));
            event.getMessage().replyEmbeds(eb.build()).queue();
        }else {
            FluxerCommand command =  commands.get(args.getFirst());
            if(command == null) {
                event.getMessage().reply("Unknown help topic!").queue();
                return;
            }
            sendHelpInfo( command.description(),args.getFirst(), event.getMessage());
        }
    }

    @Override
    public String description() {
        return "Provides help info on the bot and it's various commands! Usage ``" + prefix + " help [<topic>]``";
    }

    private void sendHelpInfo(String info, String topic, Message fluxerMessage)
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Help info on " + topic);
        eb.setDescription(info);
        fluxerMessage.replyEmbeds(eb.build()).queue();
        }
}
