/*
 * Copyright (c) 2020 Harsh Patil
 */

package xyz.lamergameryt.TicketBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.awt.*;

public class Utilities {
    /**
     * The characters which are to be used to append as suffix.
     *
     * {@link Utilities#getSuffix(int)}
     */
    private static final String TICKET_SUFFIX_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Returns the template of the embed to be used in the messages sent by the bot.
     *
     * @param title The title of the embed sent in discord.
     * @param description The description of the embed sent in discord.
     * @return EmbedBuilder containing the title and description, with the colour.
     */
    public static EmbedBuilder getDefaultEmbed(String title, String description) {
        return new EmbedBuilder().setTitle(title).setDescription(description).setColor(Color.decode(Config.HEX_COLOR));
    }

    /**
     * Read the activity in the config file and return it as an JDA Activity.
     *
     * @return The "status" of the discord bot as an activity.
     */
    static Activity getActivity() {
        String activityRaw = Config.BOT_ACTIVITY;
        if (activityRaw.trim().isEmpty()) {
            System.out.println("Please make sure that you enter an activity.");
            System.exit(1);
        }

        String activityType = activityRaw.trim().split(" ")[0];
        String activity = activityRaw.substring(activityType.length());

        switch (activityType.toLowerCase()) {
            case "watching":
                return Activity.watching(activity);
            case "listening":
                return Activity.listening(activity);
            case "playing":
                return Activity.playing(activity);
            default:
                System.out.println("The activity you've entered is invalid.");
                System.exit(1);
                return null;
        }
    }

    /**
     * Generate a random suffix which is to be appended to the ticket name.
     *
     * @param length The length of the suffix which is to be generated. {@link Config#RANDOM_SUFFIX_LENGTH}
     * @return The suffix generated.
     */
    public static String getSuffix(int length) {
        StringBuilder builder = new StringBuilder();
        while (length > 0) {
            builder.append(TICKET_SUFFIX_CHARS.charAt((int)(Math.random() * TICKET_SUFFIX_CHARS.length())));
            length--;
        }

        return builder.toString().trim().isEmpty() ? null : builder.toString();
    }
}
