package net.voiasis.commands.prefix.fun;

import java.awt.Color;
import java.io.IOException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class prefixNeko {
    public static void neko(Message message) {
        try {
            String json = ClientBuilder.newClient().target("https://nekos.life/").request().accept(MediaType.APPLICATION_JSON).get(String.class);
            String result = SearchTools.lineSearch3("https://cdn.nekos.life/neko/", json.toString());
            String neko = result.replaceAll(" ", "").replace("<metaproperty=\"og:url\"content=\"", "").replace("\"/>", "");
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setImage(neko);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        } catch (JSONException | IOException e) {
            BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Neko", 4);
        }
    }
}
