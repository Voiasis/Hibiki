package net.voiasis.auto;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class SupportTickets {
    public static void tickets(ButtonInteractionEvent event) {
        String buttonID = event.getComponentId();
        switch (buttonID) {
            case "ticket:new" :
                try {
                    newTicket(event);
                } catch (IOException e) {
                    BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "SupportTickets", 4);
                }
            break;
            case "ticket:close" :
            try {
                closeTicket(event);
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "SupportTickets", 4);
            }
            break;
            case "ticket:open" :
            //mods only TODO
            break;
        }
    }
    private static void newTicket(ButtonInteractionEvent event) throws IOException {
        InteractionHook botReply = event.reply("Opening a request <a:typing:995510544813543545>").setEphemeral(true).complete();
        Member member = event.getMember();
        Category category = event.getGuild().getCategoryById("927687629200297984");
        int openTicketAmount = category.getTextChannels().size();
        boolean memberHasOpenTicket = false; //change to use config file using userid and true false with linesearch TODO
        TextChannel channel = event.getGuild().getTextChannelById("927678238560489532");
        while (openTicketAmount > 0) {
            channel = category.getTextChannels().get(openTicketAmount - 1);
            if (member.hasAccess(channel)) {
                memberHasOpenTicket = true;
                break;
            } else {
                openTicketAmount--;
            }
        }
        if (memberHasOpenTicket) {
            botReply.editOriginal("You already have an open ticket. (" + channel.getAsMention() + ") Please close it before opening a new one.").queue();
        } else {
            String ticketNumber = SearchTools.lineSearch("request", "Requests.txt").replace("request", "");
            BufferedReader file = new BufferedReader(new FileReader("Requests.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            int newNumber = Integer.parseInt(ticketNumber) + 1;
            inputStr = inputStr.replace("request" + ticketNumber, "request" + newNumber);
            FileOutputStream fileOut = new FileOutputStream("Requests.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            EnumSet<Permission> allow = EnumSet.of(Permission.VIEW_CHANNEL);
            EnumSet<Permission> deny = EnumSet.of(Permission.VIEW_CHANNEL);
            TextChannel newTicket = category.createTextChannel("open-request-" + ticketNumber)
                .addRolePermissionOverride(event.getGuild().getPublicRole().getIdLong(), null, deny)
                .addMemberPermissionOverride(member.getIdLong(), allow, null)
                .addRolePermissionOverride(Long.parseLong("927668878279061615"), allow, null)
                .addRolePermissionOverride(Long.parseLong("927671268910727328"), allow, null).complete();
            String fileTxt = event.getMember().getId() + "=" + newTicket.getId() + "\r\n";
            Files.write(Paths.get("Requests.txt"), fileTxt.getBytes(), StandardOpenOption.APPEND);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.addField("Moderation Request", "Moderation has been notified and will get to you as soon as they can.\r\n\r\nIn the meantime, please tell us in great detail who, what, when, and where.\r\nWith screenshots/message link(s) if possible. If not, then please provide the time this event occurred.", false);
            newTicket.sendMessage("<@&927691942614142976>, " + member.getAsMention() + " has requested moderation!").setEmbeds(embed.build()).setActionRow(Button.danger("ticket:close", "🔒 Close")).queue();
            botReply.editOriginal("New request has been made. (" + newTicket.getAsMention() + ")").queue();
            embed.clear();
            embed.setColor(Color.RED);
            embed.setAuthor(event.getUser().getAsTag(), event.getUser().getAvatarUrl(), event.getUser().getAvatarUrl());
            embed.setDescription("**New request created.\r\nUser: " + event.getUser().getAsMention() + "\r\nRequest: " + newTicket.getAsMention() + "**");
            embed.setFooter("User ID: " + event.getUser().getId() + " | Request ID: " + newTicket.getId());
            TextChannel logChannel = event.getJDA().getGuildById("902397621015040020").getTextChannelById("927690797812449330");
            logChannel.sendMessageEmbeds(embed.build()).queue();
        }
    }
    private static void closeTicket(ButtonInteractionEvent event) throws IOException {
        Member member = event.getMember();
        if (member.hasAccess(event.getGuild().getTextChannelById("903473047221202984"))) {
            InteractionHook botReply = event.reply("Closing the request <a:typing:995510544813543545>").setEphemeral(true).complete();
            Category category = event.getGuild().getCategoryById("927687713853943839");
            TextChannel channel = event.getTextChannel();
            String newName = channel.getName().replace("open", "closed");
            String memberID = SearchTools.lineSearch(channel.getId(), "Requests.txt").replace(channel.getId(), "").replace("=", "");
            Member requestMember = event.getGuild().getMemberById(memberID);
            EnumSet<Permission> deny = EnumSet.of(Permission.VIEW_CHANNEL);
            channel.getManager().putPermissionOverride(requestMember, null, deny).queue();
            channel.getManager().setName(newName).queue();
            channel.getManager().setParent(category).queue();
            botReply.editOriginal("The request has been closed.").queue();
        } else {
            event.reply("Only Moderators can use that.").setEphemeral(true).queue();
        }
    }
}
