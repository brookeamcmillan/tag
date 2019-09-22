package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class EatCommand extends BaseEmoteCommand{
    public EatCommand(InputOutput io) {
        super("Eat", "You're back to full health.", io);
    }
}
