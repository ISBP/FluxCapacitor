package xyz.pbsi.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pbsi.Interfaces.FluxerCommand;

import java.util.List;

import static xyz.pbsi.Utils.CommandManager.commands;

public class MessageEvent extends ListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(MessageEvent.class);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message.getContentDisplay().startsWith("!fc")) {
            String messageContents = message.getContentRaw();
            messageContents = messageContents.replace("!fc ", "");
            StringBuilder argsContent = new StringBuilder();
            List<String> args = new java.util.ArrayList<>();
            boolean isCommandIdentifier = true;
            StringBuilder commandIdentifier = new StringBuilder();
            for(char c: messageContents.toCharArray())
            {
                if(isCommandIdentifier && c != ' ')
                {
                    commandIdentifier.append(c);
                    continue;
                }
                if(c != ' '){
                    argsContent.append(c);
                }else{
                    if (isCommandIdentifier) {
                        isCommandIdentifier = false;
                        continue;
                    };
                    args.add(argsContent.toString());
                    argsContent = new StringBuilder();
                }
            }
            FluxerCommand command =  commands.get(commandIdentifier.toString());
            if(command == null)
            {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Error");
                eb.setDescription("I couldn't find a command for " + commandIdentifier + "!");
                message.replyEmbeds(eb.build()).queue();
                return;
            }
            command.run(event, args);
        }

    }

}
