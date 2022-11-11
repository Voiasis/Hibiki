package net.voiasis.auto;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonRoles {
    public static void buttonroles(ButtonInteractionEvent event) {
        String cid = event.getComponentId();
        switch (cid) {
            case "roles:pingpong" :
            if (!hasRole(event.getMember(), "1010933101939130466")) { //ping pong
                event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130466")).queue();
                event.reply("Added role <@&1010933101939130466>").setEphemeral(true).queue();
            } else {
                event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130466")).queue();
                event.reply("Removed role <@&1010933101939130466>").setEphemeral(true).queue();
            }
            break;
            case "roles:nsfwchat" :
            if (!hasRole(event.getMember(), "1039982799521857546")) { //announcements
                event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1039982799521857546")).queue();
                event.reply("Added role <@&1039982799521857546>\nCheck <#1039982596475596821> for server link").setEphemeral(true).queue();
            } else {
                event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1039982799521857546")).queue();
                event.reply("Removed role <@&1039982799521857546>").setEphemeral(true).queue();
            }
            break;
            case "roles:announcements" :
            if (!hasRole(event.getMember(), "1010933101939130465")) { //announcements
                event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130465")).queue();
                event.reply("Added role <@&1010933101939130465>").setEphemeral(true).queue();
            } else {
                event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130465")).queue();
                event.reply("Removed role <@&1010933101939130465>").setEphemeral(true).queue();
            }
            break;
            case "roles:youtube" :
            if (!hasRole(event.getMember(), "1010933101939130464")) { //youtube
                event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130464")).queue();
                event.reply("Added role <@&1010933101939130464>").setEphemeral(true).queue();
            } else {
                event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130464")).queue();
                event.reply("Removed role <@&1010933101939130464>").setEphemeral(true).queue();
            }
            break;
            case "roles:music" :
            if (!hasRole(event.getMember(), "1010933101939130463")) { //music
                event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById("1010933101939130463")).queue();
                event.reply("Added role <@&1010933101939130463>").setEphemeral(true).queue();
            } else {
                event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById("1010933101939130463")).queue();
                event.reply("Removed role <@&1010933101939130463>").setEphemeral(true).queue();
            }
            break;

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
