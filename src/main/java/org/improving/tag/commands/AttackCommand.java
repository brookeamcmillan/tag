package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AttackCommand implements Command {

    private InputOutput io;

    public AttackCommand(InputOutput io) {
        this.io = io;
    }

    @Override
    public boolean isValid(String input, Game game) {
        return input.trim().equalsIgnoreCase("attack");
    }

    @Override
    public void execute(String input, Game game) {
        var adv = game.getPlayer().getLocation().getAdversary();
        if (adv == null) {
            io.displayText("attack what?");
        } else {
            var r = new Random();
            var roll = r.nextInt(100) + 1;
            if (roll <= 20) {
                adv.setDamageTaken(adv.getDamageTaken() + 10);
                io.displayText("" + (adv.getHitPoints() - adv.getDamageTaken()));
            } else {
                io.displayText("You missed");
            }
        }
    }
}
