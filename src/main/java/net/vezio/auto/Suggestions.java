package net.vezio.auto;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class Suggestions {
    public static void suggestions(Message message) {
        if (message.getChannel().getId().equals("1010933102807363706")) {
            message.createThreadChannel("[" + message.getAuthor().getName() + "] Suggestion Discussion").queue();
            long approvedId = Long.parseLong("1010938746348261397");
            message.addReaction(Emoji.fromCustom("approved", approvedId, false)).queue();
            long rejectedId = Long.parseLong("1010938961369247874");
            message.addReaction(Emoji.fromCustom("rejected", rejectedId, false)).queue();
        }
    }
    public static void reactionAdd(MessageReactionAddEvent event) {
        if (event.getChannel().getId().equals("1010933102807363706") && event.retrieveMessage().complete().getAuthor() == event.getUser()) { //prevent self vote
            event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
        } else {
            //make sure people only vote once and when they get a lot of approved votes, it gets sent to best-suggestions like starboard
        }
    }
}
