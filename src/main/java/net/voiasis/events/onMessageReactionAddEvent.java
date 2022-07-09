package net.voiasis.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.StarBoard;
import net.voiasis.auto.Suggestions;
import net.voiasis.translation.langTranslate;

public class onMessageReactionAddEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        langTranslate.reactTranslate(event);
        StarBoard.system(event);
        Suggestions.reactionAdd(event);
    }
}
