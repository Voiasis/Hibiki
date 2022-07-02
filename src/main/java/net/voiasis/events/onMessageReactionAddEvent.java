package net.voiasis.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.tools.NameNormalizer;
import net.voiasis.tools.ReactionRoles;
import net.voiasis.tools.StarBoard;
import net.voiasis.translation.langTranslate;

public class onMessageReactionAddEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        langTranslate.reactTranslate(event);
        StarBoard.system(event);
        ReactionRoles.reactionAdd(event);
        NameNormalizer.normalizer(event.getMember(), event.getGuild());
    }
}
