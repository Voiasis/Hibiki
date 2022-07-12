package net.voiasis.auto;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildChannel;
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
            case "tools:1" :
            toolsOne(event);
            break;
            case "tools:2" :
            toolsTwo(event);
            break;
            case "mod:1" :
            if (!event.isFromGuild()) {
                event.reply("Only moderators can access this page within the server.").setEphemeral(true).queue();
            } else {
                GuildChannel channel = event.getGuild().getGuildChannelById("903473047221202984");
                if (!event.getMember().hasAccess(channel)) {
                    event.reply("Only moderators can access this page.").setEphemeral(true).queue();
                } else {
                    if (!event.getChannel().getId().equals("903473272983814235")) {
                        event.reply("Please move to <#903473272983814235> before accessing this page.").setEphemeral(true).queue();
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
        }
    }
    private static void mainPage(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Main page");
        embed.setDescription("Tools - <:settings:994843742567673896>\r\nModeration - <:bans:994817734959431851>");
        embed.setFooter("Programmed by Voiasis#0001");
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromCustom("settings", Long.parseLong("994843742567673896"), false)),
            Button.primary("mod:1", Emoji.fromCustom("bans", Long.parseLong("994817734959431851"), false)))
        .queue();
    }
    private static void toolsOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:settings:994843742567673896> Tools");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 1 of 2");
        embed.addField(prefix + "actinfo [<:mention:995754056519778477> or <:idblock:994818038983561236>]", "Lists users current activities.", false);
        embed.addField(prefix + "avatar [<:mention:995754056519778477> or <:idblock:994818038983561236>]", "Shows users profile picture.", false);
        embed.addField(prefix + "banner [<:mention:995754056519778477> or <:idblock:994818038983561236>]", "Shows users banner.", false);
        embed.addField(prefix + "chaninfo [<:textchannels:994817869445603418> or <:idblock:994818038983561236>]", "Command not finished.", false);
        embed.addField(prefix + "jumbo <emoji>", "Sends emoji image link.", false);
        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:2", Emoji.fromUnicode("2️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void toolsTwo(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:settings:994843742567673896> Tools");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 2 of 2");
        embed.addField(prefix + "ping", "Shows bots responsiveness.", false);
        embed.addField(prefix + "rules [rule number]", "Command not finished.", false);
        embed.addField(prefix + "serverinfo", "Lists infomation about the server.", false);
        embed.addField(prefix + "uptime", "Shows bots run time.", false);
        embed.addField(prefix + "userinfo [<:mention:995754056519778477> or <:idblock:994818038983561236>]", "Lists infomation about a user.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromUnicode("1️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
    private static void modOne(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        String prefix = BotConfig.get("PREFIX");
        embed.setTitle("<:bans:994817734959431851> Moderation");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 1 of 4");
        embed.addField(prefix + "ban <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Bans a user.", false);
        embed.addField(prefix + "case ", "Command not finished.", false);
        embed.addField(prefix + "caselist", "Command not finished.", false);
        embed.addField(prefix + "kick <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Kicks a user.", false);
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
        embed.setTitle("<:bans:994817734959431851> Moderation");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 2 of 4");
        embed.addField(prefix + "mute <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Mutes a user.", false);
        embed.addField(prefix + "nsfw", "Toggles channel age-restriction", false);
        embed.addField(prefix + "purge <amount>", "Deletes messages.", false);
        embed.addField(prefix + "slowmode <amount+s,m,h>", "Sets channel slowmode.", false);
        embed.addField(prefix + "softban <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Command not finished.", false);
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
        embed.setTitle("<:bans:994817734959431851> Moderation");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 3 of 4");
        embed.addField(prefix + "steal <name> <emoji or image link>", "Adds emoji to server.", false);
        embed.addField(prefix + "tempban <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Command not finished.", false);
        embed.addField(prefix + "unban <<:idblock:994818038983561236>>", "Unbans a user.", false);
        embed.addField(prefix + "unlock", "Unlocks channel.", false);
        embed.addField(prefix + "unmute <<:mention:995754056519778477> or <:idblock:994818038983561236>> [reason]", "Unmutes a user.", false);
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
        embed.setTitle("<:bans:994817734959431851> Moderation");
        embed.setDescription("< > - Required\r\n[ ] - Optional\r\n<:textchannels:994817869445603418> - Channel\r\n<:mention:995754056519778477> - User\r\n<:idblock:994818038983561236> - ID");
        embed.setFooter("Page 4 of 4");
        embed.addField(prefix + "warn <<:mention:995754056519778477> or <:idblock:994818038983561236>> <reason>", "Command not finished.", false);

        event.editMessageEmbeds(embed.build()).setActionRow(
            Button.primary("mod:1", Emoji.fromUnicode("1️⃣")),
            Button.primary("mod:2", Emoji.fromUnicode("2️⃣")),
            Button.primary("mod:3", Emoji.fromUnicode("3️⃣")),
            Button.danger("main:1", "Back"))
        .queue();
    }
}
