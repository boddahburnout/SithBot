package org.sithbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.beryx.awt.color.ColorFactory;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;
import org.sithbot.category.BotCategories;
import org.sithbot.config.ConfigManager;
import org.sithbot.handlers.CommandHandler;
import org.sithbot.utils.permission.PermCheck;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SetRGB extends Command {

    public SetRGB() {
        this.name = "setrgb";
        this.help = "Set the embedded color for your guild";
        this.arguments = "<r> <g> <b>";
        this.category = new BotCategories().AdminCat();
    }

    @Override
    protected void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        Message message = event.getMessage();
        YamlFile botConfig = null;
        Member member = event.getMember();
        try {
            if (!new PermCheck().CheckGuildRole(guild, member)) {
                message.getChannel().sendMessage("That command is staff only!").queue();
                return;
            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //                botConfig.set("Perms.Guilds.212738721596833794", "");
        try {
            botConfig = (new ConfigManager()).accessConfig();
        Map<String, String> commandData = (new CommandHandler()).getCommandData(guild, message);
        botConfig.set("Settings.Guilds."+guild.getId() + ".Color", commandData.get("args"));
        botConfig.save();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.getChannel().sendMessage("Embed message color set!").queue();
    }
}
