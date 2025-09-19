package org.sithbot.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.sithbot.utils.EmbedWrapper;
import org.sithbot.utils.MusicPlayer.PlayerManager;
import org.sithbot.utils.MusicPlayer.Thumbnail;

public class Playing extends Command {
    public Playing() {
        this.name = "playing";
        this.help = "See what's playing";
    }

    @Override
    protected void execute(CommandEvent e) {
        Guild guild = e.getGuild();
        MessageChannel messageChannel = e.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();

        AudioTrack audioTrack = playerManager.getGuildMusicManager(guild.getIdLong()).player.getPlayingTrack();
        try {
            messageChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "The song playing is " + playerManager.getGuildMusicManager(guild.getIdLong()).player.getPlayingTrack().getInfo().title, null, null, new Thumbnail().Thumbnail(audioTrack), null)).queue();
        } catch (NullPointerException ex) {
            messageChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "Nothing is playing!", null, null, guild.getJDA().getSelfUser().getEffectiveAvatarUrl(), null)).queue();
        }
    }
}