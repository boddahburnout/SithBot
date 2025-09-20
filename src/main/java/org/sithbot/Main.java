package org.sithbot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.simpleyaml.configuration.file.YamlFile;
import org.sithbot.commands.*;
import org.sithbot.config.ConfigManager;
import org.sithbot.event.Events;
import org.sithbot.music.*;
import org.sithbot.toke.Joints;
import org.sithbot.toke.Rip;
import org.sithbot.toke.TokeCard;
import org.sithbot.toke.Tokes;

public class Main extends ListenerAdapter {
    private static JDA jda;
    public static void main(String[] args) throws Exception {

        CommandClientBuilder clientBuilder = new CommandClientBuilder();

        clientBuilder.setPrefix("~");

        clientBuilder.setOwnerId("292484423658766346");

        clientBuilder.setActivity(Activity.playing("Smoking hella dank at the Deathstar"));

        clientBuilder.addCommands(
                new Leafly(),
                new Ping(),
                new Guilds(),
                new Status(),
                new Join(),
                new Leave(),
                new Pause(),
                new Play(),
                new Playing(),
                new Skip(),
                new Queue(),
                new SpawnTable(),
                new Remove(),
                new Shuffle(),
                new SetRGB(),
                new Invite(),
                new Joints(),
                new Rip(),
                new Tokes(),
                new TokeCard()
        );

        new ConfigManager().setConfig();
        //Access the config
        YamlFile Config = new ConfigManager().accessConfig();
        //Call fs setup
        //new FileUtils().fsCheck();
        //Sets token to a string
        String token = Config.getString("Global.Bot-Token");

        jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(
                        clientBuilder.build(),
                        new Events()
                )

                .build();
    }
    public static JDA getJdaInstance() {
        return jda;
    }
}