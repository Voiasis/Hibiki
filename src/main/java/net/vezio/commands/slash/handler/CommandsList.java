package net.vezio.commands.slash.handler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.vezio.commands.slash.fun.slashSay;
import net.vezio.commands.slash.meOnly.slashEmbed;
import net.vezio.commands.slash.tools.slashActinfo;
import net.vezio.commands.slash.tools.slashAvatar;
import net.vezio.commands.slash.tools.slashBanner;
import net.vezio.commands.slash.tools.slashChannelinfo;
import net.vezio.commands.slash.tools.slashServerinfo;
import net.vezio.tools.BotLog;

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
        CommandsList.add(new slashActinfo().getName(), new slashActinfo());
        CommandsList.add(new slashAvatar().getName(), new slashAvatar());
        CommandsList.add(new slashBanner().getName(), new slashBanner());
        CommandsList.add(new slashServerinfo().getName(), new slashServerinfo());
        CommandsList.add(new slashChannelinfo().getName(), new slashChannelinfo());
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