package net.vezio.auto;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionRoles {
    public static void reactionroles(MessageReactionAddEvent event) {
        if (event.getMember().getId().equals("835691330725347379")) {
            event.retrieveMessage().complete().addReaction(event.getEmoji()).queue(q -> {});
        }
        if (event.getChannel().getId().equals("1010933102425681922") && !event.getUser().isBot()) {
            if (event.getEmoji().getName().equals("approved")) {
                if (!hasRole(event.getMember(), "1010933101939130468")) { //rule accept for memebr role
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130468")).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130468")).queue();
                }
            }
        }
        if (event.getChannel().getId().equals("1010933102425681923") && !event.getUser().isBot()) {
            if (event.getEmoji().getName().equals("oneping")) {
                if (!hasRole(event.getMember(), "1010933101939130466")) { //ping pong
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130466")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130466")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("youtube")) {
                if (!hasRole(event.getMember(), "1010933101939130464")) { //youtube
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130464")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130464")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("soundcloud")) {
                if (!hasRole(event.getMember(), "1010933101939130463")) { //music
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130463")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130463")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("announcements")) {
                if (!hasRole(event.getMember(), "1010933101939130465")) { //announcements
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130465")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130465")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
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
