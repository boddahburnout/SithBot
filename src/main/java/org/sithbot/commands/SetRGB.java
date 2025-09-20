package org.sithbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.simpleyaml.configuration.file.YamlFile;
import org.sithbot.category.BotCategories;
import org.sithbot.config.ConfigManager;
import org.sithbot.utils.permission.PermCheck;

import java.awt.*;
import java.io.IOException;

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
        Member member = event.getMember();
        if (!new PermCheck().CheckGuildRole(guild, member)) {
            message.getChannel().sendMessage("That command is staff only!").queue();
        }
        YamlFile botConfig = (new ConfigManager()).accessConfig();
        botConfig.set("Settings.Guilds."+guild.getId() + ".Color", event.getArgs());
        try {
            botConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        message.getChannel().sendMessage("Embed message color set!").queue();
    }
}
