package net.voiasis.commands.slash.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.voiasis.commands.slash.handler.CommandBuilder;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class slashSay extends CommandBuilder {
    public slashSay() {
        setCommandData(Commands.slash("say", "Sends text.")
        .addOption(OptionType.STRING, "message", "Type out what you want the bot to say.", true)
        .addOptions(new OptionData(OptionType.CHANNEL, "channel", "Choose the channel you'd like to send the message in.")
        .setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_NEWS_THREAD, ChannelType.GUILD_PRIVATE_THREAD, ChannelType.GUILD_PUBLIC_THREAD))
        .addOption(OptionType.STRING, "reply", "Enter the ID you want to reply to.", false)
        .addOption(OptionType.BOOLEAN, "mention", "Enable reply ping? Default: false", false)
        );
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        Member member = event.getMember();
        if (allowedUsers(member)) {
            OptionMapping messageOpt = event.getOption("message");
            OptionMapping channelOpt = event.getOption("channel");
            OptionMapping replyOpt = event.getOption("reply");
            OptionMapping mentionOpt = event.getOption("mention");
            String message = messageOpt == null ? null : messageOpt.getAsString();
            String channel = channelOpt == null ? null : channelOpt.getAsString();
            String replyId = replyOpt == null ? null : replyOpt.getAsString();
            Boolean mention = mentionOpt == null ? null : mentionOpt.getAsBoolean();
            if (channel == null) {
                if (replyId == null) {
                    event.getChannel().sendMessage(message).queue(m -> {
                        embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                        event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                    });
                } else {
                    TextChannel channel2 = event.getTextChannel();
                    Message message2 = channel2.retrieveMessageById(replyId).complete();
                    if (mention != null) {
                        if (mention) {
                            message2.reply(message).mentionRepliedUser(true).queue(m -> {
                                embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                                event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                            });
                        } else {
                            message2.reply(message).mentionRepliedUser(false).queue(m -> {
                                embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                                event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                            });
                        }
                    } else {
                        message2.reply(message).mentionRepliedUser(false).queue(m -> {
                            embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                            event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                        });
                    }
                }
            } else {
                TextChannel channel2 = event.getGuild().getTextChannelById(channel);
                if (replyId == null) {
                    channel2.sendMessage(message).queue(m -> {
                        embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                        event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                    });
                } else {
                    Message message2 = channel2.retrieveMessageById(replyId).complete();
                    if (mention != null) {
                        if (mention) {
                            message2.reply(message).mentionRepliedUser(true).queue(m -> {
                                embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                                event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                            });
                        } else {
                            message2.reply(message).mentionRepliedUser(false).queue(m -> {
                                embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                                event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                            });
                        }
                    } else {
                        message2.reply(message).mentionRepliedUser(false).queue(m -> {
                            embed.addField("Say", "Sent \"" + m.getContentRaw() + "\" inside " + m.getChannel().getAsMention(), false);
                            event.replyEmbeds(embed.build()).mentionRepliedUser(false).setEphemeral(true).queue();
                        });
                    }
                }
            }
        } else {
            embed.addField("No", "Fuck off", false);
            event.replyEmbeds(embed.build()).setEphemeral(true).queue(m -> {
                event.getHook().deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
            });
        }
    }
    public static boolean allowedUsers(Member member) {
        String userId = member.getId();
        if (userId.equals("472899069136601099")) { //me
            return true;
        } else if (userId.equals("603166759515848705")) { //legacy
            return true;
        } else if (userId.equals("886444946285011025")) { //elaine
            return true;
        } else {
            return false;
        }
    }
}
