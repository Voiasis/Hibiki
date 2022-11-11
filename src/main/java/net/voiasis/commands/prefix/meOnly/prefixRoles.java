package net.voiasis.commands.prefix.meOnly;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class prefixRoles {
    public static void roles(Message message) {
        EmbedBuilder embed1 = new EmbedBuilder();
        embed1.setColor(Color.RED);
        EmbedBuilder embed2 = new EmbedBuilder();
        embed2.setColor(Color.RED);

        embed1.addField("Fun Roles",
        "Select <:oneping:1010953940386463907> to gain the <@&1010933101939130466> role. Gives access to <#1010933102681542765>.\nThe role is pingable. Let the ping chaos commence." + 
        "\n\nSelect <:mariearaara:1011377361297608775> to get a link to my \"secret NSFW dungeon\". (Must be 18 or above)", false);

        embed2.addField("Update Roles", "Select <:announcements:1010954120242397254> to gain the <@&1010933101939130465> role.\nThe role will be pinged whenever theres an announcement about the server." +
        "\n\nSelect <:youtube:1010953608256294993> to gain the <@&1010933101939130464> role.\nThe role will be pinged whenever theres an update relating to my YouTube channel." +
        "\n\nSelect <:soundcloud:1010953846492758176> to gain the <@&1010933101939130463> role.\nThe role will be pinged whenever theres an update relating to my music production.", false);

        message.getChannel().sendMessageEmbeds(embed1.build()).setActionRow(
            Button.primary("roles:pingpong", Emoji.fromCustom("oneping", Long.parseLong("1010953940386463907"), false)),
            Button.danger("roles:nsfwchat", Emoji.fromCustom("mariearaara", Long.parseLong("1011377361297608775"), false))
        ).queue();
        message.getChannel().sendMessageEmbeds(embed2.build()).setActionRow(
            Button.primary("roles:announcements", Emoji.fromCustom("announcements", Long.parseLong("1010954120242397254"), false)),
            Button.primary("roles:youtube", Emoji.fromCustom("youtube", Long.parseLong("1010953608256294993"), false)),
            Button.primary("roles:music", Emoji.fromCustom("soundcloud", Long.parseLong("1010953846492758176"), false))
        ).queue();
    }
}