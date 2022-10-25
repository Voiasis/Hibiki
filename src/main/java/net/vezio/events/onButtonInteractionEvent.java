package net.vezio.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.CommandList;
import net.vezio.auto.SupportTickets;

public class onButtonInteractionEvent extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        CommandList.commands(event);
        SupportTickets.tickets(event);
    }
}
