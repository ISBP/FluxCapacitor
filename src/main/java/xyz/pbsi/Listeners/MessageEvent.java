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
import static xyz.pbsi.Utils.Constants.prefix;

public class MessageEvent extends ListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(MessageEvent.class);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();

        //Only executes codes on messages that start with the correct prefix
        if (message.getContentDisplay().startsWith(prefix)) {
            String messageContents = message.getContentRaw();
            //Removes the prefix from the message for parsing
            messageContents = messageContents.replace(prefix + " ", "");
            StringBuilder argsContent = new StringBuilder();
            List<String> args = new java.util.ArrayList<>();
            boolean isCommandIdentifier = true;
            StringBuilder commandIdentifier = new StringBuilder();
            //Splits up the message into the specific command and it's arguments
            for (int i = 0; i < messageContents.length(); i++) {
                char c = messageContents.charAt(i);
                if(isCommandIdentifier && c != ' ')
                {
                    commandIdentifier.append(c);
                    continue;
                }
                if(c != ' '){
                    argsContent.append(c);
                }
                if(c == ' ' || (i + 1 >= messageContents.length())){
                    if (isCommandIdentifier) {
                        isCommandIdentifier = false;
                        continue;
                    }
                    args.add(argsContent.toString());
                    argsContent = new StringBuilder();
                }
            }
            //Gets the command class and executes the code
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
