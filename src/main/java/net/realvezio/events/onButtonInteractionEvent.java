package net.realvezio.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.realvezio.auto.CommandList;
import net.realvezio.auto.SupportTickets;

public class onButtonInteractionEvent extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        CommandList.commands(event);
        SupportTickets.tickets(event);
    }
}
