package org.sithbot.toke;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.simpleyaml.configuration.file.YamlFile;
import org.sithbot.config.ConfigManager;
import org.sithbot.utils.EmbedWrapper;

import java.awt.*;
import java.io.IOException;

public class Tokes extends Command {
    String[] alias = new String[1];
    public Tokes() {
        alias[0] = "toke";
        this.help = "Track your toke count";
        this.category = new Category("User");
        this.name = "toke";
        this.aliases = alias;
        this.guildOnly = false;
    }
    @Override
    protected void execute(CommandEvent e) {
        Member member = e.getMember();
        new TokeCard().tokeSetup(member);
        Guild guild = e.getGuild();
        TextChannel textChannel = e.getTextChannel();
        String configPath = "420.User."+member.getId();
        String msg = "";
        YamlFile botConfig = (new ConfigManager()).accessConfig();
        int count = botConfig.getInt(configPath+".tokes");
        String[] args = e.getArgs().split(" ");
        Color color = Color.BLUE;
        color = new EmbedWrapper().GetGuildEmbedColor(guild);

        if (args.length > 2) {
            MessageEmbed embed = new EmbedWrapper().EmbedMessage("Toke Counter", null, null,  color,"Use add/remove to alter the amounts", null, null,e.getSelfUser().getAvatarUrl(),null);
            textChannel.sendMessageEmbeds(embed).queue();
            return;
        }
        if (args[0].equals("add")) {
            try {
                count = count + Integer.parseInt(args[1]);
            } catch (IndexOutOfBoundsException ex) {
                count++;
            }
            botConfig.set(configPath+".tokes", count);
            msg = "Toke added to your counter your now at "+count;
        }
        if (args[0].equals("remove")) {
            try {
                count = count + Integer.parseInt(args[1]);
            } catch (IndexOutOfBoundsException ex) {
                count--;
            }
            if (count < 0) { count = 0; };
            botConfig.set(configPath + ".tokes", count);
            msg = "Toke removed from your counter your now at " + count;
            }
        try {
            botConfig.save();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        MessageEmbed embed =  new EmbedWrapper().EmbedMessage("Toke Counter", null, null, color, msg, null, null, e.getSelfUser().getAvatarUrl(), null);
            textChannel.sendMessageEmbeds(embed).queue();
    }
}
