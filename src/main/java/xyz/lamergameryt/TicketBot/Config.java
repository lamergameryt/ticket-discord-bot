/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot;

/**
 * The configuration file for the discord bot. The documentation above each variable describes its usage.
 * Fill out all the values of the variables according to their documentation.
 *
 * The bot can be used with the default values present in the configuration, after adding the token and the owner id.
 */
public class Config {
    /**
     * The token of the discord bot. To get the token of the bot, follow the instructions below —
     * Visit the link https://discord.com/developers/applications and select your bot by clicking its icon.
     * In the Settings panel on the left select Bot.
     * Copy the token which is present below the Username of the bot and paste it here.
     *
     * {@link net.dv8tion.jda.api.JDABuilder#createLight(String)}
     */
    static final String BOT_TOKEN = "";

    /**
     * The owner of this discord bot. This variable will most likely be your own discord id.
     * 
     * {@link com.jagrosh.jdautilities.command.CommandClientBuilder#setOwnerId(String)}
     */
    static final String OWNER_ID = "";

    /**
     * The prefix of the discord bot. This will be the string which is used before every command.
     *
     * {@link com.jagrosh.jdautilities.command.CommandClientBuilder#setPrefix(String)}
     */
    static final String BOT_PREFIX = "+";

    /**
     * The hex code of the color which is to be used in the messages sent by the bot.
     *
     * {@link Utilities#getDefaultEmbed(String, String)}
     */
    static final String HEX_COLOR = "#de3e33";

    /**
     * The string which is shown as the status of the discord bot.
     * This should start with watching, listening or playing.
     *
     * {@link Utilities#getActivity()}
     */
    static final String BOT_ACTIVITY = "Watching over Tickets.";

    /**
     * The list of roles which have access to view a ticket other than the user who created it.
     */
    public static final String[] SUPPORT_ROLE_LIST = {"Support Team", "Support"};

    /**
     * The name of the category in which the tickets are created.
     */
    public static final String TICKET_CATEGORY_NAME = "Tickets";

    /**
     * The time (in seconds) to wait before a ticket is closed.
     */
    public static final int TICKET_CLOSE_COOLDOWN = 5;

    /**
     * The length of the suffix which is appended to each ticket name.
     * Make sure this value is at least 4 to avoid duplicates ticket names.
     */
    public static final int RANDOM_SUFFIX_LENGTH = 5;
}
