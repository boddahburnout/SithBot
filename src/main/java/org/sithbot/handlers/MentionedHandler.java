package org.sithbot.handlers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import org.simpleyaml.configuration.file.YamlFile;
import org.sithbot.config.ConfigManager;

import java.io.IOException;

public class MentionedHandler {
    public void sendprefix(Guild guild, Message message) {
        TextChannel channel = message.getChannel().asTextChannel();
        channel.sendMessage("My prefix is ~").queue();
    }
}
        //try {
            //new CleverbotHandler().sendRequest(guild, "697355371056201858", channel, message, member, jda.getRoles());
        //} catch (IOException e) {

        //
