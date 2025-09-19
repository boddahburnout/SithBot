package org.sithbot.event;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.sithbot.games.BlackJack;
import org.sithbot.handlers.GuildMemberJoinHandler;
import org.sithbot.handlers.MentionedHandler;
import org.sithbot.utils.MusicPlayer.PlayerManager;

import java.io.IOException;
import java.util.Objects;

public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //Handle messages here
        Guild guild = event.getGuild();
        Message message = event.getMessage();

        //System.out.println(new RandomPhrase().getRandomJoinPhrase(event.getAuthor()));
//        new GuildJoinHandler().appendGuild(guild);
            if (message.getMentions().isMentioned(event.getJDA().getSelfUser())) {
                new MentionedHandler().sendprefix(guild, message);
            }

//            try {
//                List<String> emotes = new FileUtils().FileScan(new File("emotes/").listFiles());
//
//                if (new CommandHandler().iscommand(guild, message)) {
//                    Map<String, String> commandData = new CommandHandler().getCommandData(guild, message);
//                    String cmd = commandData.get("name");
//
//                    if (emotes.contains(commandData.get("name"))) {
//                        File image = new FileUtils().GetRandomFile(new File("emotes/" + commandData.get("name")));
//                        List<User> user = event.getMessage().getMentionedUsers();
//                        try {
//                            channel.sendMessage(event.getAuthor().getAsMention() + " gave a " + commandData.get("name") + " to " + user.get(0).getAsMention()).addFile(image).queue();
//                        } catch (IndexOutOfBoundsException e) {
//                            channel.sendMessage("Mention a friend to use emotes!").queue();
//                            return;
//                        }
//                        return;
//                    }
//
    }
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();
        try {
            new GuildMemberJoinHandler().welcomeMember(guild, member);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onGuildJoin(GuildJoinEvent event) {
//        new GuildJoinHandler().appendGuild(event.getGuild());
//    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        new BlackJack().reactHandler(event.getGuild(), event);
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).inAudioChannel()) {
            VoiceChannel vc = event.getGuild().getSelfMember().getVoiceState().getChannel().asVoiceChannel();
            if (vc.getMembers().size() == 1) {
                event.getGuild().getAudioManager().closeAudioConnection();
                PlayerManager manager = PlayerManager.getInstance();
                //manager.getGuildMusicManager(event.getGuild()).player.destroy();
            }
        }
    }
}