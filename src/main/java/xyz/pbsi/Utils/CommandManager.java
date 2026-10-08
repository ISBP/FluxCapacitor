package xyz.pbsi.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pbsi.Interfaces.FluxerCommand;

import java.util.HashMap;

public class CommandManager {
    private static final Logger log = LoggerFactory.getLogger(CommandManager.class);
    public static HashMap<String, FluxerCommand> commands = new HashMap<>();
    public static void registerCommand(String command, FluxerCommand fluxerCommand){
        log.info("Registered Command {}!", command);
        commands.put(command, fluxerCommand);
    }
}
