package net.voiasis.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.ReactionRoles;

public class onMessageReactionRemoveEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if (event.getChannel().getId().equals("1010933102425681922") && !event.getUser().isBot()) {
            if (event.getEmoji().getName().equals("approved")) {
                if (ReactionRoles.hasRole(event.getMember(), "1010933101939130468")) { //rule accept for memebr role
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130468")).queue();
                }
            }
        }
    }
}
