package org.sithbot.handlers;

import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.sithbot.handlers.PlayerEvent;


public interface PlayerEventHandler {
    void onTrackLoaded(AudioTrack track);
    void onPlaylistLoaded(AudioPlaylist playlist);
    void onSearchResult(AudioTrack firstTrack);
    void onNoMatches(String query);
    void onLoadFailed(Exception e);
}