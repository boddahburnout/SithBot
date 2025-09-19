package org.sithbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import org.sithbot.utils.EmbedWrapper;
import org.sithbot.utils.StringUtils;

import java.awt.*;
import java.util.ArrayList;


@CommandInfo(
        name = {"guilds", "servers"},
        description = "See a list of guilds actively using the bot"
)

public class Guilds extends Command {

    public Guilds() {
        this.name = "guilds";
        this.aliases = new String[]{"servers"};
        this.category = new Category("User");
        this.guildOnly = false;
    }

    /**
     * The main body method of a {@link Command Command}.
     * <br>This is the "response" for a successful
     * {@link Command#run(CommandEvent) #run(CommandEvent)}.
     *
     * @param event The {@link CommandEvent CommandEvent} that
     *              triggered this Command
     */

    @Override
    protected void execute(CommandEvent event) {
        Message message = event.getMessage();
        JDA jda = event.getJDA();
        ArrayList<String> id = new ArrayList<>();
        int servers = 0;
        for (Guild guilds : jda.getGuilds()) {
            id.add(guilds.getName());
            if (guilds.getName().equals("OmaHoes")) {
                System.out.println(guilds.getOwner());
                for (Member m : guilds.getMembers()) {
                    System.out.println(m.getEffectiveName());
                }
                //TextChannel channel = guilds.getTextChannels().get(1);
                //InviteAction inviteAction = channel.createInvite();
                //Invite invite = inviteAction.complete();
                //System.out.println(invite.getUrl());
                for (Role role : guilds.getRoles()) {
                    //guilds.addRoleToMember(UserSnowflake.fromId("292484423658766346"), guilds.getRoles().get(3));
                }
            }
            servers = servers + 1;
        }
        String msg = new StringUtils().ListToString(id);
        Color color = Color.BLUE;
        try {
            color = new EmbedWrapper().GetGuildEmbedColor(event.getGuild());
        } catch (IllegalStateException e) {

        }
        MessageEmbed embed = new EmbedWrapper().EmbedMessage("I'm serving users in " + servers + " guilds", "Bartender Bot", null, color, msg, null, null, null, null);
        message.getChannel().sendMessageEmbeds(embed).queue();
    }

}
