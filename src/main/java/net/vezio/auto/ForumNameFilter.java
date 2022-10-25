package net.vezio.auto;

import java.io.IOException;

import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.vezio.tools.BadWordChecker;
import net.vezio.tools.BotLog;

public class ForumNameFilter {
    public static void ForumNameFilter(ChannelCreateEvent event) {
        if (event.getChannelType().name().equals("GUILD_PUBLIC_THREAD")) {
            String threadName = event.getChannel().getName();
            try {
                if (BadWordChecker.badWordCheck(threadName)) {
                    event.getChannel().delete().queue();
                }
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "ForumNameFilter", 4);
            }
        }
    }
}
