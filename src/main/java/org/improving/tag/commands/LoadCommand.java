package org.improving.tag.commands;
import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.improving.tag.SaveGameFactory;
import org.springframework.stereotype.Component;


@Component
public class LoadCommand implements Command {

    private InputOutput io;
    private String path = " ";
    private SaveGameFactory factory;


    public LoadCommand(InputOutput io, SaveGameFactory factory) {
        this.io = io;
        this.factory = factory;
    }

    @Override
    public boolean isValid(String input, Game game) {
        if (input == null) return false;
        input = input.trim();
        if(input.contains("=") == false) return false;
        var parts = input.split(" ");
        if (parts.length == 1) return false;
        return parts[0].equalsIgnoreCase("load");
    }

    @Override
    public void execute(String input, Game game) {
        input = input.trim();
        var fileLocation = input.split(" ");
        this.path = fileLocation[1];

        factory.load(path, game);

        io.displayText("Continuing Previous Game");
    }
}
