package net.voiasis.commands.slash.handler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.voiasis.commands.slash.fun.slashSay;
import net.voiasis.commands.slash.meOnly.slashEmbed;
import net.voiasis.commands.slash.music.slashMusicAdd;
import net.voiasis.commands.slash.music.slashMusicJump;
import net.voiasis.commands.slash.music.slashMusicNext;
import net.voiasis.commands.slash.music.slashMusicPause;
import net.voiasis.commands.slash.music.slashMusicPlay;
import net.voiasis.commands.slash.music.slashMusicPlaytime;
import net.voiasis.commands.slash.music.slashMusicQueue;
import net.voiasis.commands.slash.music.slashMusicRepeat;
import net.voiasis.commands.slash.music.slashMusicSearch;
import net.voiasis.commands.slash.music.slashMusicSeek;
import net.voiasis.commands.slash.music.slashMusicShuffle;
import net.voiasis.commands.slash.music.slashMusicStop;
import net.voiasis.commands.slash.music.slashMusicVolume;
import net.voiasis.commands.slash.tools.slashActinfo;
import net.voiasis.commands.slash.tools.slashAvatar;
import net.voiasis.commands.slash.tools.slashBanner;
import net.voiasis.commands.slash.tools.slashChannelinfo;
import net.voiasis.commands.slash.tools.slashHelp;
import net.voiasis.commands.slash.tools.slashLinear;
import net.voiasis.commands.slash.tools.slashServerinfo;
import net.voiasis.tools.BotLog;

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
        CommandsList.add(new slashLinear().getName(), new slashLinear());
        CommandsList.add(new slashHelp().getName(), new slashHelp());

        //Music
        CommandsList.add(new slashMusicAdd().getName(), new slashMusicAdd());
        CommandsList.add(new slashMusicShuffle().getName(), new slashMusicShuffle());
        CommandsList.add(new slashMusicStop().getName(), new slashMusicStop());
        CommandsList.add(new slashMusicNext().getName(), new slashMusicNext());
        CommandsList.add(new slashMusicVolume().getName(), new slashMusicVolume());
        CommandsList.add(new slashMusicJump().getName(), new slashMusicJump());
        CommandsList.add(new slashMusicQueue().getName(), new slashMusicQueue());
        CommandsList.add(new slashMusicPause().getName(), new slashMusicPause());
        CommandsList.add(new slashMusicPlay().getName(), new slashMusicPlay());
        CommandsList.add(new slashMusicPlaytime().getName(), new slashMusicPlaytime());
        CommandsList.add(new slashMusicRepeat().getName(), new slashMusicRepeat());
        CommandsList.add(new slashMusicSearch().getName(), new slashMusicSearch());
        CommandsList.add(new slashMusicSeek().getName(), new slashMusicSeek());
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
        BotLog.log("Guild Commands added.", "CommandsList", 1);
    }
    public static void registerCommandsGlobal(JDA jda) {
        addCommandsToList();
        CommandListUpdateAction ListOfCommands = jda.updateCommands();
        for (CommandBuilder command : getCommands().values()) {
            ListOfCommands.addCommands(command.commandData);
        }
        ListOfCommands.queue();
        BotLog.log("Global Commands added.", "CommandsList", 1);
    }
}