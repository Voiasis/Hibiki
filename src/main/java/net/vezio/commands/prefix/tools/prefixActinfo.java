package net.vezio.commands.prefix.tools;

import java.awt.Color;
import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.RichPresence;
import net.vezio.auto.Grammar;
import net.vezio.tools.BotLog;
import net.vezio.tools.SpotifyController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class prefixActinfo {
    public static void actinfo(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMentions().getUsers().toArray().length == 1) {
                send(message, message.getMentions().getMembers().get(0));
            } else if (message.getContentRaw().length() > args[0].length()) {
                try {
                    send(message, message.getGuild().getMemberById(args[1]));
                } catch (Exception e) {
                    message.reply("Invaild user ID provided or can't find that user.").mentionRepliedUser(false).queue();
                }
            } else {
                send(message, message.getMember());
            }
        }
    }
    private static void send(Message message, Member member) {
        EmbedBuilder embed1 = new EmbedBuilder();
        EmbedBuilder embed2 = new EmbedBuilder();
        EmbedBuilder embed3 = new EmbedBuilder();
        EmbedBuilder embed4 = new EmbedBuilder();
        EmbedBuilder embed5 = new EmbedBuilder();
        EmbedBuilder embed6 = new EmbedBuilder();
        embed1.setColor(Color.RED);
        embed2.setColor(Color.RED);
        embed3.setColor(Color.RED);
        embed4.setColor(Color.RED);
        embed5.setColor(Color.RED);
        embed6.setColor(Color.RED);
        if (!member.getActivities().isEmpty()) {
            int actCount = member.getActivities().size();
            switch (actCount) {
                case 1 :
                RichPresence presence11 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence11, member, actCount, 1);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build()).mentionRepliedUser(false).queue();
                break;
                case 2 :
                RichPresence presence21 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence21, member, actCount, 1);
                RichPresence presence22 = member.getActivities().get(actCount - 2).asRichPresence();
                field(embed2, presence22, member, actCount, 2);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build(), embed2.build()).mentionRepliedUser(false).queue();
                break;
                case 3 :
                RichPresence presence31 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence31, member, actCount, 1);
                RichPresence presence32 = member.getActivities().get(actCount - 2).asRichPresence();
                field(embed2, presence32, member, actCount, 2);
                RichPresence presence33 = member.getActivities().get(actCount - 3).asRichPresence();
                field(embed3, presence33, member, actCount, 3);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build(), embed2.build(), embed3.build()).mentionRepliedUser(false).queue();
                break;
                case 4 :
                RichPresence presence41 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence41, member, actCount, 1);
                RichPresence presence42 = member.getActivities().get(actCount - 2).asRichPresence();
                field(embed2, presence42, member, actCount, 2);
                RichPresence presence43 = member.getActivities().get(actCount - 3).asRichPresence();
                field(embed3, presence43, member, actCount, 3);
                RichPresence presence44 = member.getActivities().get(actCount - 4).asRichPresence();
                field(embed4, presence44, member, actCount, 4);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build(), embed2.build(), embed3.build(), embed4.build()).mentionRepliedUser(false).queue();
                break;
                case 5 :
                RichPresence presence51 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence51, member, actCount, 1);
                RichPresence presence52 = member.getActivities().get(actCount - 2).asRichPresence();
                field(embed2, presence52, member, actCount, 2);
                RichPresence presence53 = member.getActivities().get(actCount - 3).asRichPresence();
                field(embed3, presence53, member, actCount, 3);
                RichPresence presence54 = member.getActivities().get(actCount - 4).asRichPresence();
                field(embed4, presence54, member, actCount, 4);
                RichPresence presence55 = member.getActivities().get(actCount - 5).asRichPresence();
                field(embed5, presence55, member, actCount, 5);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build(), embed2.build(), embed3.build(), embed4.build(), embed5.build()).mentionRepliedUser(false).queue();
                break;
                case 6 :
                RichPresence presence61 = member.getActivities().get(actCount - 1).asRichPresence();
                field(embed1, presence61, member, actCount, 1);
                RichPresence presence62 = member.getActivities().get(actCount - 2).asRichPresence();
                field(embed2, presence62, member, actCount, 2);
                RichPresence presence63 = member.getActivities().get(actCount - 3).asRichPresence();
                field(embed3, presence63, member, actCount, 3);
                RichPresence presence64 = member.getActivities().get(actCount - 4).asRichPresence();
                field(embed4, presence64, member, actCount, 4);
                RichPresence presence65 = member.getActivities().get(actCount - 5).asRichPresence();
                field(embed5, presence65, member, actCount, 5);
                RichPresence presence66 = member.getActivities().get(actCount - 6).asRichPresence();
                field(embed6, presence66, member, actCount, 6);
                message.reply(Grammar.grammarY("Activity", actCount) +" for " + member.getEffectiveName()).setEmbeds(embed1.build(), embed2.build(), embed3.build(), embed4.build(), embed5.build(), embed6.build()).mentionRepliedUser(false).queue();
                break;
            }
            if (actCount >= 7) {
                if (message.getMember() == member) {
                    message.reply("Woah woah woah.. You need to slow down. You are multitasking WAYYY too hard.").mentionRepliedUser(false).queue();
                } else {
                    message.reply("Woah woah woah.. " + member.getEffectiveName() + " needs to slow down. They are multitasking WAYYY too hard.").mentionRepliedUser(false).queue();
                }
            }
        } else {
            if (message.getMember() == member) {
                message.reply("You do not currently have any activities.").mentionRepliedUser(false).queue();
            } else {
                message.reply(member.getEffectiveName() + " does not currently have any activities.").mentionRepliedUser(false).queue();
            }
        }
    }
    private static EmbedBuilder field(EmbedBuilder embed, RichPresence presence, Member member, int actCount, int subtract) {
        if (presence != null) {
            embed.addField(
                action(presence.getType().name()) + presence.getName(),
                detail(presence) + "\r\n" +
                label(presence.getType().name()) + state(presence.getState()) +
                spotifyLink(presence.getType().name(), presence.getName(), member, detail(presence), state(presence.getState())),
            false);
            image(embed, presence);
            return embed;
        } else {
            Activity activity = member.getActivities().get(actCount - subtract);
            if (activity.getEmoji() != null) { 
                embed.addField(
                    "Custom Status",
                    activity.getEmoji().getFormatted() + " " + status(activity.getName()),
                    false);
                return embed;
            } else {
                embed.addField(
                    "Custom Status",
                    action(activity.getType().name()) + status(activity.getName()),
                    false);
                return embed;
            }
        }
    }
    private static String spotifyLink(String action, String name, Member member, String track, String artist) {
        String link;
        if (action.equals("LISTENING") && name.equals("Spotify")) {
            try {
                Track[] search = SpotifyController.searchTrack(track + " " + artist);
                link = "\r\non " + search[0].getAlbum().getName();// + "\r\n[Track link](" + search[0].getExternalUrls().toString().replace("ExternalUrl(externalUrls={spotify=", "").replace("}", "");
                return link;
            } catch (IOException | SpotifyWebApiException | ParseException e) {
                BotLog.log(BotLog.getStackTraceString(e, member.getJDA()), "test", 4);
                link = "";
                return link;
            }
        } else {
            link = "";
            return link;
        }
    }
    private static EmbedBuilder image(EmbedBuilder embed, RichPresence presence) {
        if (presence.getLargeImage() != null) {
            embed.setThumbnail(presence.getLargeImage().getUrl());
            return embed;
        } else if (presence.getSmallImage() != null) {
            embed.setThumbnail(presence.getSmallImage().getUrl());
            return embed;
        } else {
            return embed;
        }
    }
    private static String detail(RichPresence presence) {
        if (presence.getDetails() != null) {
            return presence.getDetails();
        } else {
            return "";
        }
    }
    private static String state(String state) {
        String newState;
        if (state == null) {
            newState = " ";
            return newState;
        } else {
            return state.replace(";", ",");
        }
    }
    private static String action(String caps) {
        String action;
        switch (caps) {
            case "PLAYING" :
            action = "Playing ";
            return action;
            case "WATCHING" :
            action = "Watching ";
            return action;
            case "LISTENING" :
            action = "Listening to ";
            return action;
            case "COMPETING" :
            action = "Competing in ";
            return action;
            case "STREAMING" :
            action = "Streaming ";
            return action;
            case "CUSTOM_STATUS" :
            action = "";
            return action;
        }
        return caps;
    }
    private static String label(String caps) {
        String label;
        switch (caps) {
            case "PLAYING" :
            label = "";
            return label;
            case "WATCHING" :
            label = "";
            return label;
            case "LISTENING" :
            label = "by ";
            return label;
            case "COMPETING" :
            label = "";
            return label;
            case "STREAMING" :
            label = "";
            return label;
        }
        return caps;
    }
    private static String status(String name) {
        String status = "";
        if (name.equals("Custom Status")) {
            return status;
        } else {
            return name;
        }
    }
}
