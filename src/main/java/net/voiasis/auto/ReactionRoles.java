package net.voiasis.auto;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionRoles {
    public static void reactionroles(MessageReactionAddEvent event) {
        if (event.getChannel().getId().equals("927707232093761616") && !event.getUser().isBot()) {
            event.retrieveMessage().complete().addReaction(Emoji.fromFormatted(event.getReaction().getEmoji().getFormatted())).queue();
            if (event.getEmoji().getName().equals("oneping")) {
                if (!hasRole(event.getMember(), "996258355242872842")) {
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("996258355242872842")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("996258355242872842")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("youtube")) {
                if (!hasRole(event.getMember(), "996263081149726750")) {
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("996263081149726750")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("996263081149726750")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("soundcloud")) {
                if (!hasRole(event.getMember(), "996263318157279282")) {
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("996263318157279282")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("996263318157279282")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
            if (event.getEmoji().getName().equals("announcements")) {
                if (!hasRole(event.getMember(), "996266715342184509")) {
                    event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("996266715342184509")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("996266715342184509")).queue();
                    event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                }
            }
        }
    }
    private static boolean hasRole(Member member, String roleId) {
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
