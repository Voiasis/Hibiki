package net.vezio.commands.slash.tools;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.slash.handler.CommandBuilder;

public class slashBanner extends CommandBuilder {
    public slashBanner() {
        setCommandData(Commands.slash("banner", "Lists users current activities.").addOption(OptionType.USER, "user", "Who to check activities from.", false));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping userOpt = event.getOption("user");
        User user = userOpt == null ? null : userOpt.getAsUser();
        if (user == null) {
            User user2 = event.getUser();
            send(event, user2);
        } else {
            send(event, user);
        }
    }
    private static void send(SlashCommandInteractionEvent event, User user) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Banner of " + user.getAsTag());
        embed.setDescription("[Image Link]("+ user.retrieveProfile().complete().getBannerUrl() + "?size=1024)");
        embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=1024");
        event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
    }
}
