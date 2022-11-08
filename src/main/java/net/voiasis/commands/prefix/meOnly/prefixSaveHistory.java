package net.voiasis.commands.prefix.meOnly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.voiasis.tools.BotLog;

public class prefixSaveHistory {
    public static void save(Message message) {
        if (message.getAuthor().getId().equals("472899069136601099")) {
            message.delete().queue();
            MessageHistory history = message.getChannel().getHistoryFromBeginning(100).complete();
            int amount = history.size();
            while (amount > 0) {
                Message historyMessage = history.getRetrievedHistory().get(amount - 1);
                if (!historyMessage.getAuthor().isBot()) {
                    String messageID = historyMessage.getId();
                    String authorID = historyMessage.getAuthor().getId();
                    String contents = historyMessage.getContentRaw().replace("\n", "[LINEBREAK-N]").replace("\r", "[LINEBREAK-R]");
                    String fileText = messageID + "=" + authorID + "=" + contents + "\r\n";
                    try {
                        Files.write(Paths.get("ServerMessages.txt"), fileText.getBytes(), StandardOpenOption.APPEND);
                        amount--;
                    } catch (IOException e) {
                        BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "ServerLogs", 4);
                        break;
                    }
                } else {
                    amount--;
                }
            }
        }
    }
}
