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
    private final SaveGameFactory saveFactory;

    public Game(Command[] commands, InputOutput io, SaveGameFactory saveFactory) {
        startingLocation = buildWorld();
        this.commands = commands;
        this.io = io;
        this.p = new Player(startingLocation);
        this.saveFactory = saveFactory;
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
                saveFactory.save(this);
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

        var tict = new Location();
        tict.setName("The Ice Cream Truck");

        var md = new Location();
        md.setName("Mount Doom");

        var ta = new Location();
        ta.setName("The Amazon");

        var tmcs = new Location();
        tmcs.setName("The Mac & Cheese Shop");

        var tr = new Location();
        tr.setName("The Reef");

        var tvm = new Location();
        tvm.setName("The Velvet Moose");

        var a = new Location();
        a.setName("Airport");

        var tms = new Location();
        tms.setName("The Mountains");

        var tm = new Location();
        tm.setName("The Mall");

        var tvod = new Location();
        tvod.setName("The Volcano of Death");


        tdh.getExits().add(new Exit("Heaven Ave", tmcs, "h", "heaven", "ave"));
        tdh.getExits().add(new Exit("The Deathly Brownie", td, "tdb", "brownie", "the"));
        td.getExits().add(new Exit("Camel Path", ta, "cp", "camel", "path"));
        tmcs.getExits().add(new Exit("Highway 121", ta, "hwy", "121", "h121", "hwy121"));
        td.getExits().add(new Exit("Rocky Road", tict, "rr", "rocky", "road"));
        td.getExits().add(new Exit("The Dock", a, "the dock", "dock"));
        tvm.getExits().add(new Exit("The Pudding Slide", a, "the pudding", "slide"));
        tvm.getExits().add(new Exit("The Front Door", ta, "front door", "front", "door"));
        a.getExits().add(new Exit("flight 121", tms, "f 121"));
        tict.getExits().add(new Exit("Magic Portal", md, "magic", "portal"));
        tms.getExits().add(new Exit("The Lava Flow", tvod, "lava flow", "lava", "flow"));
        tms.getExits().add(new Exit("The narrow trail", md, "narrow trail", "narrow", "trail"));
        tms.getExits().add(new Exit("the plane", ta, "plane"));
        tms.getExits().add(new Exit("bike trail", tr, "bike", "b trail", "bike t"));
        a.getExits().add(new Exit("Flight to the Mall", tm, "flight", "mall", "to the mall"));
        tmcs.getExits().add(new Exit("Paradise Rd", tr, "paradise", "rd"));
        tmcs.getExits().add(new Exit("Highway 21", tvod, "h 21", "21"));
        tr.getExits().add(new Exit("the city walk", tm, "city walk", "city", "walk"));
        tm.getExits().add(new Exit("Path to Doom", md, "path doom", "doom"));
        md.getExits().add(new Exit("The Cab", tm, "cab"));
        ta.getExits().add(new Exit("Amaz-ing Moose", tvm, "amazing moose", "amazing", "moose"));
        tr.getExits().add(new Exit("The Scenic Route", tvm, "scenic", "route", "scenic route"));
        md.getExits().add(new Exit("Jump into Lava", tvod, "jump lava", "jump", "into lava"));
        tm.getExits().add(new Exit("An escalator of doom", tvod, "escalator", "escalator of doom"));
        return tdh;
    }

}


