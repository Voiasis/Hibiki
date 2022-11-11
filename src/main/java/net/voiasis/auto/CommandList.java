package net.voiasis.auto;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.voiasis.tools.BotConfig;

public class CommandList {
    public static void commands(ButtonInteractionEvent event) {
        String cid = event.getComponentId();
        switch (cid) {
            case "main:1" :
            mainPage(event);
            break;
            case "fun:1" :
            funOne(event);
            break;
            case "music:1" :
            musicOne(event);
            break;
            case "music:2" :
            musicTwo(event);
            break;
            case "music:3" :
            musicThree(event);
            break;
            case "tools:1" :
            toolsOne(event);
            break;
            case "tools:2" :
            toolsTwo(event);
            break;
            case "tools:3" :
            toolsThree(event);
            break;
            case "mod:1" :
            if (!event.isFromGuild()) {
                event.reply("Only moderators can access this page within the server.").setEphemeral(true).queue();
            } else {
                GuildChannel channel = event.getGuild().getGuildChannelById("1010933102949965904");
                if (!event.getMember().hasAccess(channel)) {
                    event.reply("Only moderators can access this page.").setEphemeral(true).queue();
                } else {
                    if (!event.getChannel().getId().equals("1010933102949965905")) {
                        event.reply("Please move to <#1010933102949965905> before accessing this page.").setEphemeral(true).queue();
                    } else {
                        modOne(event);
                    }
                }
            }
            break;
            case "mod:2" :
            modTwo(event);
            break;
            case "mod:3" :
            modThree(event);
            break;
            case "mod:4" :
            modFour(event);
            break;

            //slash commands
            case "slash:main:1" :
            mainPageSlash(event);
            break;
            case "slash:fun:1" :
            funOneSlash(event);
            break;
            case "slash:music:1" :
            musicOneSlash(event);
            break;
            case "slash:music:2" :
            musicTwoSlash(event);
            break;
            case "slash:music:3" :
            musicThreeSlash(event);
            break;
            case "slash:tools:1" :
            toolsOneSlash(event);
            break;
            case "slash:tools:2" :
            toolsTwoSlash(event);
            break;
            case "slash:tools:3" :
            toolsThreeSlash(event);
            break;
            case "slash:mod:1" :
            if (!event.isFromGuild()) {
                event.reply("Only moderators can access this page within the server.").setEphemeral(true).queue();
            } else {
                GuildChannel channel = event.getGuild().getGuildChannelById("1010933102949965904");
                if (!event.getMember().hasAccess(channel)) {
                    event.reply("Only moderators can access this page.").setEphemeral(true).queue();
                } else {
                    if (!event.getChannel().getId().equals("1010933102949965905")) {
                        event.reply("Please move to <#1010933102949965905> before accessing this page.").setEphemeral(true).queue();
                    } else {
                        modOneSlash(event);
                    }
                }
            }
            break;
            case "slash:mod:2" :
            modTwoSlash(event);
            break;
            case "slash:mod:3" :
            modThreeSlash(event);
            break;
            case "slash:mod:4" :
            modFourSlash(event);
            break;
        }
    }
    private static void mainPage(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Command Lists");
        embed.setDescription("Fun - <:fun:1040424423213252698>\nMusic - <:music:1040431422634205264>\nTools - <:settings:1010953881963986944>\nModeration - <:bans:1010954107135217794>" +
        "\n\n**Notes:\n**< > - Required\r\n[ ] - Optional\r\n<:textchannels:1010953804050616342> - Channel\r\n<:mention:1010953969155178509> - User\r\n<:idblock:1010954027120480307> - ID");
        embed.setFooter("Programmed by Voiasis#0363");
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("fun:1", Emoji.fromCustom("fun", Long.parseLong("1040424423213252698"), false)),
            Button.primary("music:1", Emoji.fromCustom("music", Long.parseLong("1040431422634205264"), false)),
            Button.primary("tools:1", Emoji.fromCustom("settings", Long.parseLong("1010953881963986944"), false)),
            Button.danger("mod:1", Emoji.fromCustom("bans", Long.parseLong("1010954107135217794"), false)))
        .queue();
    }
    private static void funOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:fun:1040424423213252698> Fun");
        embed.setFooter("Page 1 of 1");
        embed.addField(prefix + "neko", "Shows random neko picture.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void musicOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 1 of 3");
        embed.addField(prefix + "add <link>", "Adds music to the playback queue.", false);
        embed.addField(prefix + "jump <seconds>", "Jump forward in the current track.", false);
        embed.addField(prefix + "queue", "Shows the playback queue.", false);
        embed.addField(prefix + "next", "Skips tracks.", false);
        embed.addField(prefix + "pause", "Pauses playback.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("tools:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void musicTwo(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 2 of 3");
        embed.addField(prefix + "play <link>", "Play given track or playlist now.", false);
        embed.addField(prefix + "playtime", "Time left in currently playing track.", false);
        embed.addField(prefix + "repeat", "Repeat the current playback queue.", false);
        embed.addField(prefix + "search <search>", "Search and play top YouTube result.", false);
        embed.addField(prefix + "seek <time?>", "Seek to the specified position. Currently broken?", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("tools:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void musicThree(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 3 of 3");
        embed.addField(prefix + "shuffle", "Shuffles the playback queue.", false);
        embed.addField(prefix + "stop", "Stops playback and disconnects.", false);
        embed.addField(prefix + "volume", "Change playback volume.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("tools:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void toolsOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 1 of 3");
        embed.addField(prefix + "actinfo [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Lists users current activities.", false);
        embed.addField(prefix + "avatar [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Shows users profile picture.", false);
        embed.addField(prefix + "banner [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Shows users banner.", false);
        embed.addField(prefix + "chaninfo [<:textchannels:1010953804050616342> or <:idblock:1010954027120480307>]", "Shows channel infomation.", false);
        embed.addField(prefix + "jumbo <emoji>", "Sends emoji image link.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("tools:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void toolsTwo(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 2 of 3");
        embed.addField(prefix + "rules <rule number>", "Lists a rule.", false);
        embed.addField(prefix + "serverinfo", "Lists infomation about the server.", false);
        embed.addField(prefix + "uptime", "Shows bots run time.", false);
        embed.addField(prefix + "userinfo [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Lists infomation about a user.", false);
        embed.addField(prefix + "lyrics <track>", "Sends full lyrics of track.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("tools:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void toolsThree(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 3 of 3");
        embed.addField(prefix + "spotify <track>", "Search Spotify tracks.", false);
        embed.addField(prefix + "youtube <search>", "Search YouTube.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("tools:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void modOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 1 of 4");
        embed.addField(prefix + "ban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Bans a user.", false);
        embed.addField(prefix + "case ", "Command not finished.", false);
        embed.addField(prefix + "caselist", "Command not finished.", false);
        embed.addField(prefix + "kick <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Kicks a user.", false);
        embed.addField(prefix + "lock", "Locks channel.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("mod:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("mod:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("mod:4", Emoji.fromUnicode("4️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void modTwo(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 2 of 4");
        embed.addField(prefix + "mute <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Mutes a user.", false);
        embed.addField(prefix + "nsfw", "Toggles channel age-restriction", false);
        embed.addField(prefix + "purge <amount> [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Deletes given amount of messages with user-specific option.", false);
        embed.addField(prefix + "rules [rule number]", "Lists one rule or all rules.", false);
        embed.addField(prefix + "slowmode <amount+s,m,h>", "Sets channel slowmode.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("mod:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("mod:4", Emoji.fromUnicode("4️⃣")),
            Button.primary("mod:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void modThree(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 3 of 4");
        embed.addField(prefix + "softban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Command not finished.", false);
        embed.addField(prefix + "steal <name> <emoji or image link>", "Adds emoji to server.", false);
        embed.addField(prefix + "tempban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Command not finished.", false);
        embed.addField(prefix + "unban <<:idblock:1010954027120480307>>", "Unbans a user.", false);
        embed.addField(prefix + "unlock", "Unlocks channel.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("mod:4", Emoji.fromUnicode("4️⃣")),
            Button.primary("mod:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("mod:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void modFour(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 4 of 4");
        embed.addField(prefix + "unmute <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Unmutes a user.", false);
        embed.addField(prefix + "warn <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> <reason>", "Command not finished.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("mod:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("mod:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("mod:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    //slash commands
    private static void mainPageSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Command Lists");
        embed.setDescription("Fun - <:fun:1040424423213252698>\nMusic - <:music:1040431422634205264>\nTools - <:settings:1010953881963986944>\nModeration - <:bans:1010954107135217794>" +
        "\n\n**Notes:\n**< > - Required\r\n[ ] - Optional\r\n<:textchannels:1010953804050616342> - Channel\r\n<:mention:1010953969155178509> - User\r\n<:idblock:1010954027120480307> - ID");
        embed.setFooter("Programmed by Voiasis#0363");
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:fun:1", Emoji.fromCustom("fun", Long.parseLong("1040424423213252698"), false)),
            Button.primary("slash:music:1", Emoji.fromCustom("music", Long.parseLong("1040431422634205264"), false)),
            Button.primary("slash:tools:1", Emoji.fromCustom("settings", Long.parseLong("1010953881963986944"), false)),
            Button.danger("slash:mod:1", Emoji.fromCustom("bans", Long.parseLong("1010954107135217794"), false)))
        .queue();
    }
    private static void funOneSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:fun:1040424423213252698> Fun");
        embed.setFooter("Page 1 of 1");
        embed.addField(prefix + "neko", "Shows random neko picture.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void musicOneSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 1 of 3");
        embed.addField(prefix + "add", "Adds music to the playback queue.", false);
        embed.addField(prefix + "jump", "Jump forward in the current track.", false);
        embed.addField(prefix + "queue", "Shows the playback queue.", false);
        embed.addField(prefix + "next", "Skips tracks.", false);
        embed.addField(prefix + "pause", "Pauses playback.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("slash:tools:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void musicTwoSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 2 of 3");
        embed.addField(prefix + "play", "Play given track or playlist now.", false);
        embed.addField(prefix + "playtime", "Time left in currently playing track.", false);
        embed.addField(prefix + "repeat", "Repeat the current playback queue.", false);
        embed.addField(prefix + "search", "Search and play top YouTube result.", false);
        embed.addField(prefix + "seek", "Seek to the specified position.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("slash:tools:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void musicThreeSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:music:1040431422634205264> Music");
        embed.setFooter("Page 3 of 3");
        embed.addField(prefix + "shuffle", "Shuffles the playback queue.", false);
        embed.addField(prefix + "stop", "Stops playback and disconnects.", false);
        embed.addField(prefix + "volume", "Change playback volume.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("slash:tools:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void toolsOneSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 1 of 3");
        embed.addField(prefix + "actinfo [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Lists users current activities.", false);
        embed.addField(prefix + "avatar [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Shows users profile picture.", false);
        embed.addField(prefix + "banner [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Shows users banner.", false);
        embed.addField(prefix + "chaninfo [<:textchannels:1010953804050616342> or <:idblock:1010954027120480307>]", "Shows channel infomation.", false);
        embed.addField(prefix + "jumbo <emoji>", "Sends emoji image link.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("slash:ools:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void toolsTwoSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 2 of 3");
        embed.addField(prefix + "rules <rule number>", "Lists a rule.", false);
        embed.addField(prefix + "serverinfo", "Lists infomation about the server.", false);
        embed.addField(prefix + "uptime", "Shows bots run time.", false);
        embed.addField(prefix + "userinfo [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Lists infomation about a user.", false);
        embed.addField(prefix + "lyrics <track>", "Sends full lyrics of track.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("slash:tools:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void toolsThreeSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:settings:1010953881963986944> Tools");
        embed.setFooter("Page 3 of 3");
        embed.addField(prefix + "spotify <track>", "Search Spotify tracks.", false);
        embed.addField(prefix + "youtube <search>", "Search YouTube.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:tools:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("slash:tools:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void modOneSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 1 of 4");
        embed.addField(prefix + "ban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Bans a user.", false);
        embed.addField(prefix + "case ", "Command not finished.", false);
        embed.addField(prefix + "caselist", "Command not finished.", false);
        embed.addField(prefix + "kick <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Kicks a user.", false);
        embed.addField(prefix + "lock", "Locks channel.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:mod:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("slash:mod:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("slash:mod:4", Emoji.fromUnicode("4️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void modTwoSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 2 of 4");
        embed.addField(prefix + "mute <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Mutes a user.", false);
        embed.addField(prefix + "nsfw", "Toggles channel age-restriction", false);
        embed.addField(prefix + "purge <amount> [<:mention:1010953969155178509> or <:idblock:1010954027120480307>]", "Deletes given amount of messages with user-specific option.", false);
        embed.addField(prefix + "rules [rule number]", "Lists one rule or all rules.", false);
        embed.addField(prefix + "slowmode <amount+s,m,h>", "Sets channel slowmode.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:mod:3", Emoji.fromUnicode("3️⃣")),
            Button.primary("slash:mod:4", Emoji.fromUnicode("4️⃣")),
            Button.primary("slash:mod:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void modThreeSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 3 of 4");
        embed.addField(prefix + "softban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Command not finished.", false);
        embed.addField(prefix + "steal <name> <emoji or image link>", "Adds emoji to server.", false);
        embed.addField(prefix + "tempban <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Command not finished.", false);
        embed.addField(prefix + "unban <<:idblock:1010954027120480307>>", "Unbans a user.", false);
        embed.addField(prefix + "unlock", "Unlocks channel.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:mod:4", Emoji.fromUnicode("4️⃣")),
            Button.primary("slash:mod:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("slash:mod:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
    private static void modFourSlash(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = "/";
        embed.setTitle("<:bans:1010954107135217794> Moderation");
        embed.setFooter("Page 4 of 4");
        embed.addField(prefix + "unmute <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> [reason]", "Unmutes a user.", false);
        embed.addField(prefix + "warn <<:mention:1010953969155178509> or <:idblock:1010954027120480307>> <reason>", "Command not finished.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("slash:mod:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("slash:mod:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("slash:mod:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("slash:main:1", "Back"))
        .queue();
    }
}
