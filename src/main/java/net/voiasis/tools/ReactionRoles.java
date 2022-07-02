package net.voiasis.tools;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionRoles {
    public static void reactionAdd(MessageReactionAddEvent event) {
        if (!event.getUser().isBot()) {
            if (event.getChannel().equals(event.getGuild().getTextChannelById("773089307148812288"))) {
                if (event.getEmoji().getName().equals("android")) { //works
                    if (!hasRole(event.getMember(), "888652946617815111")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("888652946617815111")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("888652946617815111")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                } else 
                if (event.getEmoji().getName().equals("apple")) { //doesnt work
                    if (!hasRole(event.getMember(), "888652792502313011")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("888652792502313011")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("888652792502313011")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                } else 
                if (event.getEmoji().getName().equals("frping")) { //doesnt work TODO
                    if (!hasRole(event.getMember(), "835808706042134608")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("835808706042134608")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("835808706042134608")).queue();
                       event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                } else 
                if (event.getEmoji().getName().equals("pojav")) { //doesnt work
                    if (!hasRole(event.getMember(), "773104647434010654")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("773104647434010654")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("773104647434010654")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                } else 
                if (event.getEmoji().getName().equals("frcoalmine")) { //doesnt work
                    if (!hasRole(event.getMember(), "872116029919219773")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("872116029919219773")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("872116029919219773")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                } else 
                if (event.getEmoji().getName().equals("franime")) { //doesnt work
                    if (!hasRole(event.getMember(), "936886489466413066")) {
                        event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("936886489466413066")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("936886489466413066")).queue();
                        event.retrieveMessage().complete().removeReaction(event.getEmoji(), event.getUser()).queue();
                    }
                }
            }
        }
    }
    private static boolean hasRole(Member member, String roleId) {
        if (findRole(member, roleId) != null) {
            //BotLog.log("true", "hasRole", 3);
            return true;
        } else {
            //BotLog.log("false", "hasRole", 3);
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
