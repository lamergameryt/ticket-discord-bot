/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.Member;

import static xyz.lamergameryt.TicketBot.Utilities.getDefaultEmbed;

@CommandInfo(name = "add", description = "Adds an user to the text channel in which the command was executed.")
public class AddTicketCommand extends Command {
    public AddTicketCommand() {
        super.name = "add";
        super.arguments = "<user-id>";
        super.help = "Add an user to the ticket.";
    }

    @Override
    protected void execute(CommandEvent event) {
        // Ignore the command if the channel in which this command was execute is not a ticket.
        if (!event.getTextChannel().getName().contains("ticket-"))
            return;

        // If the length of args is more than or less than 1, it means an invalid id was entered.
        if (event.getArgs().trim().split(" ").length != 1) {
            event.reply(getDefaultEmbed("Ticket => Error", "Please make sure you specify the ID of the user you want to add to this ticket.").build());
            return;
        }

        Member member = event.getGuild().retrieveMemberById(event.getArgs()).complete();
        if (member == null) {
            event.reply(getDefaultEmbed("Ticket => Error", "The user with the ID **" + event.getArgs() + "** doesn't exist.").build());
            return;
        }

        // Add the user to the ticket.
        event.getTextChannel().getManager().putPermissionOverride(member, 3072L, 8192L).queue();
        event.reply(getDefaultEmbed("Ticket => Added", "The user " + member.getAsMention() + " was added to the ticket").build());
    }
}
