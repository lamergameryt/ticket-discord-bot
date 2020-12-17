/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import xyz.lamergameryt.TicketBot.Commands.AddTicketCommand;
import xyz.lamergameryt.TicketBot.Commands.CloseTicketCommand;
import xyz.lamergameryt.TicketBot.Commands.NewTicketCommand;
import xyz.lamergameryt.TicketBot.Commands.RemoveTicketCommand;

import javax.security.auth.login.LoginException;

import static xyz.lamergameryt.TicketBot.Utilities.getDefaultEmbed;

public class TicketBotMain {
    public static void main(String[] args) {
        CommandClientBuilder ccb = new CommandClientBuilder();
        ccb.useDefaultGame();
        ccb.setOwnerId(Config.OWNER_ID);
        ccb.setPrefix(Config.BOT_PREFIX);
        ccb.useHelpBuilder(true);
        ccb.addCommands(new NewTicketCommand(),
                new CloseTicketCommand(),
                new AddTicketCommand(),
                new RemoveTicketCommand());

        // Build an embed to send when the help command is called.
        ccb.setHelpConsumer(e -> {
            EmbedBuilder helpMessage = getDefaultEmbed("Ticket => Commands", "");
            for (Command c : ccb.build().getCommands())
                helpMessage.addField("__" + Config.BOT_PREFIX + c.getName() + " " + c.getArguments() + "__", c.getHelp(), false);
            e.reply(helpMessage.build());
        });

        try {
            JDA bot = JDABuilder.createLight(Config.BOT_TOKEN).setActivity(Utilities.getActivity())
                    .addEventListeners(ccb.build()).build();
            bot.awaitReady();
        } catch (LoginException e) {
            System.out.println("The token \"" + Config.BOT_TOKEN + "\" is invalid.");
        } catch (InterruptedException e) {
            System.out.println("The creation of the bot was interrupted. Please re-run the program.");
        }
    }
}
