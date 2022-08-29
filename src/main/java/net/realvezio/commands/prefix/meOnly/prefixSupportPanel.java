package net.realvezio.commands.prefix.meOnly;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class prefixSupportPanel {
    public static void panel(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Request Moderation", "Has someone broken a rule? Open a ticket with the button below and let us know!", false);
        message.getChannel().sendMessageEmbeds(embed.build()).setActionRow(Button.danger("ticket:new", "⚠️ Request")).queue();
    }
}
