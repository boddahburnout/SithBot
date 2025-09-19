package org.sithbot.toke;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.simpleyaml.configuration.file.YamlFile;
import org.sithbot.config.ConfigManager;
import org.sithbot.handlers.CommandHandler;
import org.sithbot.utils.EmbedWrapper;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

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
    protected void execute(CommandEvent commandEvent) {
        Member member = commandEvent.getMember();
        new TokeCard().tokeSetup(member);
        Guild guild = commandEvent.getGuild();
        Message message = commandEvent.getMessage();
        TextChannel textChannel = commandEvent.getTextChannel();
        YamlFile botConfig = null;
        String configPath = "420.User."+member.getId();
        String msg = "";
        try {
            botConfig = (new ConfigManager()).accessConfig();
            Map<String, String> commandData = (new CommandHandler()).getCommandData(guild, message);
            int count = botConfig.getInt(configPath+".tokes");
            String[] args = commandData.get("args").split(" ");
            Color color = Color.BLUE;
            try {
                color = new EmbedWrapper().GetGuildEmbedColor(guild);
            } catch (IllegalStateException e) {

            }
            if (args.length > 2) {
                MessageEmbed embed = new EmbedWrapper().EmbedMessage("Toke Counter", null, null,  color,"Use add/remove to alter the amounts", null, null,commandEvent.getSelfUser().getAvatarUrl(),null);
                textChannel.sendMessageEmbeds(embed).queue();
                return;
            }
            if (args[0].equals("add")) {
                try {
                    count = count + Integer.parseInt(args[1]);
                } catch (IndexOutOfBoundsException e) {
                    count++;
                }
                botConfig.set(configPath+".tokes", count);
                msg = "Toke added to your counter your now at "+count;
            }
            if (args[0].equals("remove")) {
                try {
                    count = count + Integer.parseInt(args[1]);
                } catch (IndexOutOfBoundsException e) {
                    count--;
                }
                if (count < 0) { count = 0; };
                botConfig.set(configPath + ".tokes", count);
                msg = "Toke removed from your counter your now at " + count;
            }
            botConfig.save();
            MessageEmbed embed =  new EmbedWrapper().EmbedMessage("Toke Counter", null, null, color, msg, null, null, commandEvent.getSelfUser().getAvatarUrl(), null);
            textChannel.sendMessageEmbeds(embed).queue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
