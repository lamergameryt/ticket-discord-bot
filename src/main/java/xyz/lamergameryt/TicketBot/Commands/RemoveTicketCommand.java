/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.Member;

import static xyz.lamergameryt.TicketBot.Utilities.getDefaultEmbed;

@CommandInfo(name = "remove", description = "Removes the specified user from the text channel in which the command was executed.")
public class RemoveTicketCommand extends Command {
    public RemoveTicketCommand() {
        super.name = "remove";
        super.arguments = "<user-id>";
        super.help = "Remove an user from the ticket.";
    }

    @Override
    protected void execute(CommandEvent event) {
        // Ignore the command if the channel in which this command was execute is not a ticket.
        if (!event.getTextChannel().getName().contains("ticket-"))
            return;

        // If the length of args is more than or less than 1, it means an invalid id was entered.
        if (event.getArgs().trim().split(" ").length != 1) {
            event.reply(getDefaultEmbed("Ticket => Error", "Please make sure you specify the ID of the user you want to remove from this ticket.").build());
            return;
        }

        Member member = event.getGuild().retrieveMemberById(event.getArgs()).complete();
        if (member == null) {
            event.reply(getDefaultEmbed("Ticket => Error", "The user with the ID **" + event.getArgs() + "** doesn't exist.").build());
            return;
        }

        // Remove the user from the ticket.
        event.getTextChannel().getManager().putPermissionOverride(member, 0L, 1024L).queue();
        event.reply(getDefaultEmbed("Ticket => Removed", "The user <@" + member.getId() + "> was removed from the ticket").build());
    }
}
