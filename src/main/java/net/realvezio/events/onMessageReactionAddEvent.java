package net.realvezio.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.realvezio.auto.ReactionRoles;
import net.realvezio.auto.StarBoard;
import net.realvezio.auto.Suggestions;
import net.realvezio.translation.langTranslate;

public class onMessageReactionAddEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        langTranslate.reactTranslate(event);
        StarBoard.system(event);
        Suggestions.reactionAdd(event);
        ReactionRoles.reactionroles(event);
    }
}
