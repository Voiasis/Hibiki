package net.vezio.auto;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public class SpamFilter {
    public static void filterSpam(MessageReceivedEvent event) { 
        //spam filter
        /*try {
            if (!event.getChannel().getId().equals("996261782693875835")) {
                List<Message> history = event.getChannel().asTextChannel().getIterableHistory().complete().stream().limit(10).filter(msg -> !msg.equals(event.getMessage())).collect(Collectors.toList());
                int spam = history.stream().filter(message -> message.getAuthor().equals(event.getAuthor()) && !message.getAuthor().isBot()).filter(msg -> (
                event.getMessage().getTimeCreated().toEpochSecond() - msg.getTimeCreated().toEpochSecond()) < 10).collect(Collectors.toList()).size();
                if (spam > 2 && !event.getGuild().getOwner().equals(event.getMember()) && !PermissionUtil.checkPermission(event.getMember(), Permission.ADMINISTRATOR)) {
                    event.getChannel().asTextChannel().deleteMessages(history.stream().filter(message -> message.getAuthor().equals(event.getAuthor()) && !message.getAuthor().isBot()).filter(msg ->(
                    event.getMessage().getTimeCreated().toEpochSecond() - msg.getTimeCreated().toEpochSecond()) < 10).collect(Collectors.toList())).queue();
                    event.getMember().timeoutFor(10, TimeUnit.SECONDS).queue();
                    event.getChannel().sendMessage("Please slow down, " + event.getAuthor().getAsMention()).queue(message -> {
                        message.delete().queueAfter(10, TimeUnit.SECONDS);
                        event.getMessage().delete().queueAfter(10, TimeUnit.SECONDS);
                    });
                }
            }   
        } catch (IllegalStateException e) {
        }*/
    }
}
