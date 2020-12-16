# Ticket Discord Bot
#### Create discord tickets with ease
![License](https://img.shields.io/github/license/lamergameryt/ticket-discord-bot) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/lamergameryt/ticket-discord-bot) [![JDA Version](https://img.shields.io/badge/Discord%20JDA-4.2.0__222+-blue.svg)](https://spigot.org/) ![GitHub followers](https://img.shields.io/github/followers/lamergameryt?style=social)
[![Made With Java](https://img.shields.io/badge/Made%20With-Java-blueviolet)](https://java.com)

**Ticket Discord Bot** is a discord bot which has one purpose: creating tickets.

This is a very simple discord bot which can be integrated in your code **without credits & asking for permission**.

## How to use this?
This bot was intended to be integrated into your own discord bot. You can still use it as an independent bot though.

To compile the bot into a jar file you will require the following dependencies:
* **JDA (By dv8tion)** - Recommended version is 4.2.0+. Anything below that version is unsupported and the bot may not function as expected.
* **JDA-Utilities (By Jagrosh)** - Recommended version is 3.0.5+.
* **Maven** - Can be downloaded from https://maven.apache.org/download.cgi

To compile the bot, clone this repository and head to the directory which contains the repository. After navigating to that directory, execute the following command:

`mvn clean install`

After its execution, a new directory should be formed with the name `target`. There will be two files in that folder named `TicketBot-<version>.jar` and `TicketBot-<version>-jar-with-dependencies.jar`.

To execute the bot, open a terminal in the `target` folder and execute `java -jar TicketBot-<version>-jar-with-dependencies.jar`.

**Note:** The bot will not work if you compile it without editing the configuration file. To learn how to edit the config file, read the `Configuration` section.
## Configuration
The bot can be configured with the help of a configuration file. This is located in the package `xyz.lamergameryt.TicketBot` and is called `Config.java`.
The configuration file is documented properly and it contains the usage of the variables in it.

The bot **will not work** if the `BOT_TOKEN` and `OWNER_ID` values are not set properly. It is highly recommended that you spend some time configuring the bot before using it.

## License
This plugin is licensed under the MIT License. See the LICENSE file in the top distribution directory for the full license text.

## Liked it?
Thank you for reading through this!

If you liked it, consider giving a star to this project and following me on GitHub as I have put my ❤ and a lot of effort into this.
