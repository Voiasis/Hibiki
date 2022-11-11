package net.voiasis.auto;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionRoles {
    public static void reactionroles(MessageReactionAddEvent event) {
        /*if (event.getMember().getId().equals("835691330725347379")) {
            event.retrieveMessage().complete().addReaction(event.getEmoji()).queue();
        }*/
        if (event.getChannel().getId().equals("1010933102425681922") && !event.getUser().isBot()) {
            if (event.getEmoji().getName().equals("approved")) {
                if (!hasRole(event.getMember(), "1010933101939130468")) { //rule accept for memebr role
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130468")).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130468")).queue();
                }
            }
        }
    }
    public static boolean hasRole(Member member, String roleId) {
        if (findRole(member, roleId) != null) {
            return true;
        } else {
            return false;
        }
    }
    private static Role findRole(Member member, String roleId) {
        List<Role> roles = member.getRoles();
    return roles.stream()
                .filter(role -> role.getId().equals(roleId)) // filter by role name
                .findFirst() // take first result
                .orElse(null); // else return null
    }
}
