package xyz.pbsi.Interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface FluxerCommand {
    void run(MessageReceivedEvent event, List<String> args);

}
