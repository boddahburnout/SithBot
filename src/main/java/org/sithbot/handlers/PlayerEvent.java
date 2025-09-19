package org.sithbot.handlers;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class PlayerEvent {
    private final PlayerEventType type;
    private final AudioTrack track;
    private final String message;
    private final int playlistSize;

    public PlayerEvent(PlayerEventType type, AudioTrack track, String message) {
        this(type, track, message, 0);
    }

    public PlayerEvent(PlayerEventType type, AudioTrack track, String message, int playlistSize) {
        this.type = type;
        this.track = track;
        this.message = message;
        this.playlistSize = playlistSize;
    }

    public PlayerEventType getType() { return type; }
    public AudioTrack getTrack() { return track; }
    public String getMessage() { return message; }
    public int getPlaylistSize() { return playlistSize; }
}
