package org.sithbot.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.sithbot.utils.EmbedWrapper;
import org.sithbot.utils.MusicPlayer.GuildMusicManager;
import org.sithbot.utils.MusicPlayer.PlayerManager;

public class Volume extends Command {
    public Volume() {
        this.name = "volume";
        this.help = "Set the global volume";
    }
    @Override
    protected void execute(CommandEvent e) {
        PlayerManager playerManager = PlayerManager.getInstance();
        Guild guild = e.getGuild();
        TextChannel textChannel = e.getTextChannel();
        GuildMusicManager guildMusicManager = playerManager.getGuildMusicManager(guild.getIdLong());
        String[] args = e.getArgs().split(" ");
        if (e.getArgs().isEmpty()) {
            textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage("Volume", null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "Volume is set to "+ guildMusicManager.player.getVolume(), null,null,e.getSelfUser().getAvatarUrl(),null)).queue();
            return;
        }
        guildMusicManager.player.setVolume(Integer.parseInt(args[0]));
        textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage("Volume set", null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "Volume has been set to "+args[0], null,null,e.getSelfUser().getAvatarUrl(),null)).queue();
    }
}
