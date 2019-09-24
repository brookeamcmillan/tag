package org.improving.tag;

import org.improving.tag.commands.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Game {
    private Date startTime;
    private Date endTime;
    private Command[] commands;
    private InputOutput io;
    private Player p;
    private Location startingLocation;

    public Game(Command[] commands, InputOutput io) {
        startingLocation = buildWorld();
        this.commands = commands;
        this.io = io;
        this.p = new Player(startingLocation);
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public Player getPlayer() {
        return p;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    public Date getStartTime() {
        return startTime;
    }

    private void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    private void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void run() {
        this.setStartTime(new Date());

        boolean loop = true;
        while (loop) {
           io.displayPrompt("> ");
            String input = io.recieveInput();

            Command validCommand = getValidCommand(input);
            if (null != validCommand) {
                validCommand.execute(input, this);
            } else if (input.equalsIgnoreCase("exit")) {
                io.displayText("Goodbye.");
                loop = false;
            } else {
                io.displayText("Huh, I don't understand?");
            }
            this.setEndTime(new Date());

        }
    }
    private Command getValidCommand(String input) {
        for ( Command command : commands) {
            if (command.isValid(input, this)) {
                return command;
            }
        }
        return null;
    }

    private Location buildWorld() {
        var tdh = new Location();
        tdh.setName("The Deathly Hallows");

        var td = new Location();
        td.setName("The Dessert");

        var ta = new Location();
        ta.setName("The Amazon");

        var tmcs = new Location();
        tmcs.setName("The Mac & Cheese Shop");

        tdh.getExits().add(new Exit("Heaven Ave", tmcs, "h", "heaven", "ave"));
        tdh.getExits().add(new Exit("The Deathly Brownie", td, "tdb", "brownie", "the"));
        td.getExits().add(new Exit("Camel Path", ta, "cp", "camel", "path"));
        tmcs.getExits().add(new Exit("Highway 121", ta, "hwy", "121", "h121", "hwy121"));

        return tdh;
    }

}


