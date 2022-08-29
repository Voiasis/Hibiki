package net.realvezio.commands.slash.handler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.realvezio.commands.slash.fun.slashSay;
import net.realvezio.commands.slash.meOnly.slashEmbed;
import net.realvezio.tools.BotLog;

import java.util.HashMap;

public class CommandsList {
    private static final HashMap<String, CommandBuilder> commands = new HashMap<>();

    public static void add(String name, CommandBuilder command) {
        commands.put(name, command);
    }

    public static HashMap<String, CommandBuilder> getCommands() {

        return commands;
    }

    public static void addCommandsToList() {
        CommandsList.add(new slashSay().getName(), new slashSay());
        CommandsList.add(new slashEmbed().getName(), new slashEmbed());
    }
    public static void registerCommands(JDA api, String GUILD_ID) {
        Guild mainGuild = api.getGuildById(GUILD_ID);
        assert mainGuild != null;
        addCommandsToList();
        CommandListUpdateAction ListOfCommands = mainGuild.updateCommands();
        for (CommandBuilder command : getCommands().values()) {
            ListOfCommands.addCommands(command.commandData);
        }
        ListOfCommands.queue();
        BotLog.log("Commands added.", "CommandsList", 1);
    }
}