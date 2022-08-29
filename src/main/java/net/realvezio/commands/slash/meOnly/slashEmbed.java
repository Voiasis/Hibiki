package net.realvezio.commands.slash.meOnly;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.realvezio.commands.slash.handler.CommandBuilder;

public class slashEmbed extends CommandBuilder {
    public slashEmbed() {
        setCommandData(Commands.slash("embed", "Creates Embeds.")
        .addOption(OptionType.STRING, "authorname", "test", false) //1
        .addOption(OptionType.STRING, "authorurl", "test", false) //2
        .addOption(OptionType.STRING, "authoriconurl", "test", false) //3
        .addOption(OptionType.STRING, "title", "test", false) //4
        .addOption(OptionType.STRING, "titleurl", "test", false) //5
        .addOption(OptionType.STRING, "description", "test", false) //6
        .addOption(OptionType.STRING, "thumnailurl", "test", false) //7
        .addOption(OptionType.STRING, "fieldname1", "test", false) //11
        .addOption(OptionType.STRING, "fieldvalue1", "test", false) //12
        .addOption(OptionType.BOOLEAN, "fieldinline1", "test", false) //13
        .addOption(OptionType.STRING, "fieldname2", "test", false) //14
        .addOption(OptionType.STRING, "fieldvalue2", "test", false) //15
        .addOption(OptionType.BOOLEAN, "fieldinline2", "test", false) //16
        .addOption(OptionType.STRING, "fieldname3", "test", false) //17
        .addOption(OptionType.STRING, "fieldvalue3", "test", false) //18
        .addOption(OptionType.BOOLEAN, "fieldinline3", "test", false) //19
        .addOption(OptionType.STRING, "fieldname4", "test", false) //20
        .addOption(OptionType.STRING, "fieldvalue4", "test", false) //21
        .addOption(OptionType.BOOLEAN, "fieldinline4", "test", false) //22
        .addOption(OptionType.STRING, "fieldname5", "test", false) //23
        .addOption(OptionType.STRING, "fieldvalue5", "test", false) //24
        .addOption(OptionType.BOOLEAN, "fieldinline5", "test", false) //25
        .addOption(OptionType.STRING, "imageurl", "test", false) //8
        .addOption(OptionType.STRING, "footertext", "test", false) //9
        .addOption(OptionType.STRING, "footericonurl", "text", false) //10
        );
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        Member member = event.getMember();
        if (member.getId().equals("472899069136601099")) {
            OptionMapping authornameOpt = event.getOption("authorname");
            OptionMapping authorurlOpt = event.getOption("authorurl");
            OptionMapping authoriconurlOpt = event.getOption("authoriconurl");
            OptionMapping titleOpt = event.getOption("title");
            OptionMapping titleurlOpt = event.getOption("titleurl");
            OptionMapping descriptionOpt = event.getOption("description");
            OptionMapping thumnailurlOpt = event.getOption("thumnailurl");
            OptionMapping fieldname1Opt = event.getOption("fieldname1");
            OptionMapping fieldvalue1Opt = event.getOption("fieldvalue1");
            OptionMapping fieldinline1Opt = event.getOption("fieldinline1");
            OptionMapping fieldname2Opt = event.getOption("fieldname2");
            OptionMapping fieldvalue2Opt = event.getOption("fieldvalue2");
            OptionMapping fieldinline2Opt = event.getOption("fieldinline2");
            OptionMapping fieldname3Opt = event.getOption("fieldname3");
            OptionMapping fieldvalue3Opt = event.getOption("fieldvalue3");
            OptionMapping fieldinline3Opt = event.getOption("fieldinline3");
            OptionMapping fieldname4Opt = event.getOption("fieldname4");
            OptionMapping fieldvalue4Opt = event.getOption("fieldvalue4");
            OptionMapping fieldinline4Opt = event.getOption("fieldinline4");
            OptionMapping fieldname5Opt = event.getOption("fieldname5");
            OptionMapping fieldvalue5Opt = event.getOption("fieldvalue5");
            OptionMapping fieldinline5Opt = event.getOption("fieldinline5");
            OptionMapping imageurlOpt = event.getOption("imageurl");
            OptionMapping footertextOpt = event.getOption("footertext");
            OptionMapping footericonurlOpt = event.getOption("footericonurl");
            String authorname = authornameOpt == null ? null : authornameOpt.getAsString();
            String authorurl = authorurlOpt == null ? null : authorurlOpt.getAsString();
            String authoriconurl = authoriconurlOpt == null ? null : authoriconurlOpt.getAsString();
            String title = titleOpt == null ? null : titleOpt.getAsString();
            String titleurl = titleurlOpt == null ? null : titleurlOpt.getAsString();
            String description = descriptionOpt == null ? null : descriptionOpt.getAsString();
            String thumnailurl = thumnailurlOpt == null ? null : thumnailurlOpt.getAsString();
            String fieldname1 = fieldname1Opt == null ? null : fieldname1Opt.getAsString();
            String fieldvalue1 = fieldvalue1Opt == null ? null : fieldvalue1Opt.getAsString();
            Boolean fieldinline1 = fieldinline1Opt == null ? null : fieldinline1Opt.getAsBoolean();
            String fieldname2 = fieldname2Opt == null ? null : fieldname2Opt.getAsString();
            String fieldvalue2 = fieldvalue2Opt == null ? null : fieldvalue2Opt.getAsString();
            Boolean fieldinline2 = fieldinline2Opt == null ? null : fieldinline2Opt.getAsBoolean();
            String fieldname3 = fieldname3Opt == null ? null : fieldname3Opt.getAsString();
            String fieldvalue3 = fieldvalue3Opt == null ? null : fieldvalue3Opt.getAsString();
            Boolean fieldinline3 = fieldinline3Opt == null ? null : fieldinline3Opt.getAsBoolean();
            String fieldname4 = fieldname4Opt == null ? null : fieldname4Opt.getAsString();
            String fieldvalue4 = fieldvalue4Opt == null ? null : fieldvalue4Opt.getAsString();
            Boolean fieldinline4 = fieldinline4Opt == null ? null : fieldinline4Opt.getAsBoolean();
            String fieldname5 = fieldname5Opt == null ? null : fieldname5Opt.getAsString();
            String fieldvalue5 = fieldvalue5Opt == null ? null : fieldvalue5Opt.getAsString();
            Boolean fieldinline5 = fieldinline5Opt == null ? null : fieldinline5Opt.getAsBoolean();
            String imageurl = imageurlOpt == null ? null : imageurlOpt.getAsString();
            String footertext = footertextOpt == null ? null : footertextOpt.getAsString();
            String footericonurl = footericonurlOpt == null ? null : footericonurlOpt.getAsString();
            if (authorname != null) {
                if (authorurl != null) {
                    if (authoriconurl != null) {
                        embed.setAuthor(authorname, authorurl, authoriconurl);
                    } else {
                        embed.setAuthor(authorname, authorurl, null);
                    }
                } else {
                    if (authoriconurl != null) {
                        embed.setAuthor(authorname, null, authoriconurl);
                    } else {
                        embed.setAuthor(authorname, null, null);
                    }
                }
            }
            if (title != null) {
                if (titleurl != null) {
                    embed.setTitle(title, titleurl);
                } else {
                    embed.setTitle(title, null);
                }
            }
            if (description != null) {
                embed.setDescription(description);
            }
            if (thumnailurl != null) {
                embed.setThumbnail(thumnailurl);
            }
            if (fieldname1 != null && fieldvalue1 != null) {
                if (fieldinline1 != null) {
                    embed.addField(fieldname1, fieldvalue1, fieldinline1); 
                } else {
                    embed.addField(fieldname1, fieldvalue1, false);
                }
            }
            if (fieldname2 != null && fieldvalue2 != null) {
                if (fieldinline2 != null) {
                    embed.addField(fieldname2, fieldvalue2, fieldinline2);
                } else {
                    embed.addField(fieldname2, fieldvalue2, false);
                }
            }
            if (fieldname3 != null && fieldvalue3 != null) {
                if (fieldinline3 != null) {
                    embed.addField(fieldname3, fieldvalue3, fieldinline3);
                } else {
                    embed.addField(fieldname3, fieldvalue3, false);
                }
            }
            if (fieldname4 != null && fieldvalue4 != null) {
                if (fieldinline4 != null) {
                    embed.addField(fieldname4, fieldvalue4, fieldinline4); 
                } else {
                    embed.addField(fieldname4, fieldvalue4, false);
                }
            }
            if (fieldname5 != null && fieldvalue5 != null) {
                if (fieldinline5 != null) {
                    embed.addField(fieldname5, fieldvalue5, fieldinline5);
                } else {
                    embed.addField(fieldname5, fieldvalue5, false);
                }
            }
            if (imageurl != null) {
                embed.setImage(imageurl);
            }
            if (footertext != null) {
                if (footericonurl != null) {
                    embed.setFooter(footertext, footericonurl);
                } else {
                    embed.setFooter(footertext, null);
                }
            }
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            event.reply("Completed.").mentionRepliedUser(false).setEphemeral(true).queue();
        }
    }
}
