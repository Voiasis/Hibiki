package net.voiasis.events;

import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.NameNormalizer;

public class onGuildMemberUpdateNicknameEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        NameNormalizer.normalizer(event.getMember());
    }
}
