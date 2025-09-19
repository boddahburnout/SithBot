package org.sithbot.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.sithbot.utils.EmbedWrapper;
import org.sithbot.utils.MusicPlayer.GuildMusicManager;
import org.sithbot.utils.MusicPlayer.PlayerManager;
import org.sithbot.utils.MusicPlayer.Thumbnail;

import java.util.Objects;

public class Skip extends Command {
    public Skip() {
        this.name = "skip";
        this.help = "Skip the playing song";
    }

    @Override
    protected void execute(CommandEvent e) {
        Guild guild = e.getGuild();
        TextChannel textChannel = e.getTextChannel();
        Member selfMember = e.getSelfMember();
        Member member = e.getMember();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager guildMusicManager = playerManager.getGuildMusicManager(guild.getIdLong());
        AudioTrack audioTrack = playerManager.getGuildMusicManager(guild.getIdLong()).player.getPlayingTrack();
        boolean state = Objects.requireNonNull(member.getVoiceState()).inAudioChannel();
        boolean isDeaf = member.getVoiceState().isDeafened();
        boolean selfState = Objects.requireNonNull(selfMember.getVoiceState()).inAudioChannel();
        if (!state) { textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "Join the voice channel first!", null, null, selfMember.getEffectiveAvatarUrl(), null)).queue(); }
        if (isDeaf) { textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "Your not even listening your opinion does not matter", null, null, guild.getJDA().getSelfUser().getEffectiveAvatarUrl(), null)).queue(); }
        if (guildMusicManager.player.getPlayingTrack() == null) { textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), "We are cutting you off for tonight, nothing is even playing.", null, null, guild.getJDA().getSelfUser().getEffectiveAvatarUrl(), null)).queue(); }
        textChannel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(guild.getJDA().getSelfUser().getName(), null, null, new EmbedWrapper().GetGuildEmbedColor(guild), guildMusicManager.player.getPlayingTrack().getInfo().title + " has been skipped!", null, null, new Thumbnail().Thumbnail(audioTrack), null)).queue();
        guildMusicManager.scheduler.nextTrack();
    }
}
