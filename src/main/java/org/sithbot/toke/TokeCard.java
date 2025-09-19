package org.sithbot.toke;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;
import org.sithbot.config.ConfigManager;
import org.sithbot.handlers.CommandHandler;
import org.sithbot.utils.EmbedWrapper;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class TokeCard extends Command {
    String[] alias = new String[1];

    public TokeCard() {
        alias[0] = "tc";
        this.help = "Check your toke numbers";
        this.category = new Category("User");
        this.name = "tokecard";
        this.aliases = alias;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        Member member = commandEvent.getMember();
        Guild guild = commandEvent.getGuild();
        TextChannel textChannel = commandEvent.getTextChannel();
        tokeSetup(member);
        YamlFile botConfig = null;
        String configPath = "420.User." + member.getId();
        String msg = "";
        try {
            botConfig = (new ConfigManager()).accessConfig();
            int ripCount = botConfig.getInt(configPath + ".rips");
            int tokeCount = botConfig.getInt(configPath + ".tokes");
            int jointCount = botConfig.getInt(configPath+".joints");
            java.awt.Color color = new EmbedWrapper().GetGuildEmbedColor(guild);
            MessageEmbed embed = new EmbedWrapper().EmbedMessage("Toke record", null,null,color,"Rips: "+ripCount+"\n"+"Tokes: "+tokeCount+"\n"+"Joints: "+jointCount,null,null, member.getAvatarUrl(), null);
            textChannel.sendMessageEmbeds(embed).queue();
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void tokeSetup(Member member) {
        YamlFile botConfig = null;
        String configPath = "420.User." + member.getId();
        String msg = "";
        try {
            botConfig = (new ConfigManager()).accessConfig();
            Object path = botConfig.get(configPath);
            if (path == null) {
                botConfig.set(configPath + ".rips", 0);
                botConfig.set(configPath + ".tokes", 0);
                botConfig.set(configPath+".joints", 0);
                botConfig.save();
            }
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
