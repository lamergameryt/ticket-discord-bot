/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.ChannelManager;
import xyz.lamergameryt.TicketBot.Config;
import xyz.lamergameryt.TicketBot.Utilities;

import static xyz.lamergameryt.TicketBot.Utilities.getDefaultEmbed;

@CommandInfo(name = "new", description =  "Creates a new text channel in the category specified, with an appropriate name.")
public class NewTicketCommand extends Command {
    /**
     * The name of the category in which the tickets will be created.
     *
     * {@link xyz.lamergameryt.TicketBot.Config#TICKET_CATEGORY_NAME}
     */
    private String ticketCategory = Config.TICKET_CATEGORY_NAME;

    /**
     * The length of the random suffix appended to every ticket.
     *
     * {@link xyz.lamergameryt.TicketBot.Config#RANDOM_SUFFIX_LENGTH}
     */
    private int suffixLength = Config.RANDOM_SUFFIX_LENGTH;

    /**
     * The list of roles which have access to view a ticket (other than the user who created it).
     *
     * {@link xyz.lamergameryt.TicketBot.Config#SUPPORT_ROLE_LIST}
     */
    private String[] supportRoles = Config.SUPPORT_ROLE_LIST;

    public NewTicketCommand() {
        super.name = "new";
        super.arguments = "[reason]";
        super.help = "Create a new ticket.";
    }

    @Override
    protected void execute(CommandEvent event) {
        // If the category specified in the config is invalid then an error should be displayed.
        if (ticketCategory.isEmpty()) {
            System.out.println("The ticket category \"" + ticketCategory + "\" is not specified.");
            System.exit(1);
        }

        boolean withReason = !event.getArgs().isEmpty();

        // If the category specified in the config doesn't exist on discord then an error should be displayed.
        if (!(event.getGuild().getCategoriesByName(ticketCategory, true).size() > 0)) {
            System.out.println("The ticket category \"" + ticketCategory + "\" does not exist.");
            System.exit(1);
        }

        // A random suffix which is to be appended to the ticket's name.
        String suffix = Utilities.getSuffix(suffixLength);
        String ticketName = "ticket-" + event.getAuthor().getName();

        if (suffix != null) {
            ticketName += "-" + suffix;
        }

        TextChannel ticket = event.getGuild().createTextChannel(ticketName, event.getGuild().getCategoriesByName(ticketCategory, true).get(0)).complete();

        // Allow only the user to see the ticket and disable viewing the ticket for all other members.
        ChannelManager ticketManager = ticket.getManager().putPermissionOverride(event.getMember(), 3072L, 8192L)
                .putPermissionOverride(event.getGuild().getRolesByName("@everyone", true).get(0), 0L, 1024L);

        // Allow the support roles to view the ticket.
        for (String supportRole : supportRoles) {
            if (!event.getGuild().getRolesByName(supportRole, true).isEmpty()) {
                ticketManager = ticketManager.putPermissionOverride(event.getGuild().getRolesByName(supportRole, true).get(0), 3072L, 8192L);
            }
        }
        ticketManager.queue();

        // Send the appropriate messages to the channel the command was executed and in the created ticket.
        event.reply(getDefaultEmbed("Ticket => Created",
                "The ticket <#" + ticket.getId() + "> was created." + (withReason ? "\n\n**Reason**: " + event.getArgs() : ""))
                        .build());
        ticket.sendMessage(getDefaultEmbed("Ticket => Created",
                "Please wait! Our staff will be there to assist you shortly.\n\n**Created By:** " + event.getAuthor().getAsMention() + (withReason ? "\n**Reason:** " + event.getArgs() : ""))
                .build()).queue();
    }
}
