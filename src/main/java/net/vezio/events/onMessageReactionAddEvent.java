package net.vezio.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.ReactionRoles;
import net.vezio.auto.StarBoard;
import net.vezio.auto.Suggestions;
import net.vezio.translation.langTranslate;

public class onMessageReactionAddEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        langTranslate.reactTranslate(event);
        StarBoard.system(event);
        Suggestions.reactionAdd(event);
        ReactionRoles.reactionroles(event);
    }
}
