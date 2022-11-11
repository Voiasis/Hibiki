package net.voiasis.commands.slash.tools;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.voiasis.commands.slash.handler.CommandBuilder;

public class slashHelp extends CommandBuilder {
    public slashHelp() {
        setCommandData(Commands.slash("help", "Shows bot command lists."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Command Lists");
        embed.setDescription("Fun - <:fun:1040424423213252698>\nMusic - <:music:1040431422634205264>\nTools - <:settings:1010953881963986944>\nModeration - <:bans:1010954107135217794>" +
        "\n\n**Notes:\n**< > - Required\r\n[ ] - Optional\r\n<:textchannels:1010953804050616342> - Channel\r\n<:mention:1010953969155178509> - User\r\n<:idblock:1010954027120480307> - ID");
        embed.setFooter("Programmed by Voiasis#0363");
        event.replyEmbeds(embed.build()).setActionRow(
            Button.primary("slash:fun:1", Emoji.fromCustom("fun", Long.parseLong("1040424423213252698"), false)),
            Button.primary("slash:music:1", Emoji.fromCustom("music", Long.parseLong("1040431422634205264"), false)),
            Button.primary("slash:tools:1", Emoji.fromCustom("settings", Long.parseLong("1010953881963986944"), false)),
            Button.danger("slash:mod:1", Emoji.fromCustom("bans", Long.parseLong("1010954107135217794"), false)))
        .mentionRepliedUser(false).queue();
    }
}
