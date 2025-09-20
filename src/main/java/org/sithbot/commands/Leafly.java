package org.sithbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import darth.leaflyscrape.DataFetch;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.json.simple.JSONObject;
import org.sithbot.utils.EmbedWrapper;
import org.sithbot.utils.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class Leafly extends Command {

    public Leafly() {
        this.name = "strain";
        this.category = null;
        this.help = "Look up an overview on a strains data";
        this.arguments = "<Strain>";
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
        MessageChannel channel = event.getEvent().getGuildChannel();
        String message = event.getArgs();
        DataFetch dataFetch = new DataFetch();
        if (message.length() == 0) {
            channel.sendMessage("Please provide a strain").queue();
            return;
        }
        Color color = Color.BLUE;
        color = new EmbedWrapper().GetGuildEmbedColor(event.getGuild());
        try {
            JSONObject strain = dataFetch.fetchData(message);
            //JSONObject strain = leaflyApi.fechLeafly(message);
            String thumb;
            if (dataFetch.getNugImage(strain) == null) {
                if (dataFetch.getSymbol(strain) != null) {
                    thumb = strain.get("flowerImagePng").toString();
                } else {
                    thumb = null;
                }
            } else {
                thumb = dataFetch.getNugImage(strain).toString();
            }
            channel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage(dataFetch.getStrainName(strain).toString(), null, "", color, new StringUtils().FormatWeedData(strain), null, null, thumb, null)).queue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            channel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage("Strain not found", null,null, color,"The strain you requested was not found make sure it's spelled exactly how it should be. For example 'Death Star' not 'Deathstar'",null, null, event.getSelfUser().getAvatarUrl(), null)).queue();
        } catch (RuntimeException e) {
            channel.sendMessageEmbeds(new EmbedWrapper().EmbedMessage("Strain not found", null,null, color,"The strain you requested was not found make sure it's spelled exactly how it should be. For example 'Death Star' not 'Deathstar'",null, null, event.getSelfUser().getAvatarUrl(), null)).queue();
        }
    }
}