/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import xyz.lamergameryt.TicketBot.Config;

import java.util.concurrent.TimeUnit;

import static xyz.lamergameryt.TicketBot.Utilities.getDefaultEmbed;

@CommandInfo(name = "close", description = "Deletes the ticket channel which the command was executed in.")
public class CloseTicketCommand extends Command {
    /**
     * The time to wait before closing a ticket.
     *
     * {@link xyz.lamergameryt.TicketBot.Config#TICKET_CLOSE_COOLDOWN}
     */
    private int closeCooldown = Config.TICKET_CLOSE_COOLDOWN;

    public CloseTicketCommand() {
        super.name = "close";
        super.help = "Close an open ticket.";
    }

    @Override
    protected void execute(CommandEvent event) {
        // Ignore the command if the channel in which this command was execute is not a ticket.
        if (!event.getTextChannel().getName().contains("ticket-"))
            return;

        event.reply(getDefaultEmbed("Ticket => Close", "Closing this ticket in " + closeCooldown + " second(s).").build());

        // Close the ticket after the specified seconds.
        event.getTextChannel().delete().queueAfter(closeCooldown, TimeUnit.SECONDS);
    }
}
