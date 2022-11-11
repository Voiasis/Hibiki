package net.voiasis.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.ButtonRoles;
import net.voiasis.auto.CommandList;
import net.voiasis.auto.SupportTickets;

public class onButtonInteractionEvent extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        CommandList.commands(event);
        SupportTickets.tickets(event);
        ButtonRoles.buttonroles(event);
    }
}
