package org.sithbot.handlers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.json.simple.parser.ParseException;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;
import org.sithbot.config.ConfigManager;
import org.sithbot.utils.EmbedWrapper;

import java.awt.*;
import java.io.IOException;

public class GuildMemberJoinHandler {
    public void welcomeMember(Guild guild, Member member) throws InvalidConfigurationException, IOException, ParseException {
        YamlFile Config = new ConfigManager().accessConfig();
        if (Config.isSet(guild.getId() + "." + "Welcome-Channel")) {
            TextChannel channel = guild.getTextChannelById(Config.getString(guild.getId() + "." + "Welcome-Channel"));
            Color color = Color.BLUE;
            color = new EmbedWrapper().GetGuildEmbedColor(guild);
            Role role = guild.getRoleById(Config.getString(guild.getId() + ".Welcome-Mention-Role"));
            //channel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage("Welcome " + member.getUser().getName(), "", "", color, drink[0] + role.getAsMention(), null, null, null, drink[1])).queue();
        }
    }
}