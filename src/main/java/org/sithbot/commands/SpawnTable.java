package org.sithbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import org.sithbot.games.BlackJack;

@CommandInfo(
        name = {"SpawnTable"},
        description = "Spawns Black Jack Table"
)
@Author("Darthkota98")
public class SpawnTable extends Command {

    public SpawnTable()
    {
        this.name = "SpawnTable";
        this.help = "Spawns Black Jack Table";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        new BlackJack().createTable(event.getTextChannel());
    }
}