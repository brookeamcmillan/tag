import org.improving.tag.commands.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveCommandTests {

    MoveCommand target;
    TestInputOutput io;

    @BeforeEach
    public void Arrange() {
        // Arrange
        io = new TestInputOutput();
        target = new MoveCommand(io);
    }

    @Test
    public void execute_should_display_all_words_but_move() {
        //Act
        target.execute("move to the moon", null);

        //Assert
        assertEquals("You proceed to the moon.", io.lastText);
    }

    @Test
    public void execute_should_display_all_words_but_move_with_spaces() {
        //Act
        target.execute("  move to the moon  ", null);

        //Assert
        assertEquals("You proceed to the moon.", io.lastText);
    }


    @Test
    public void isValid_should_be_true_when_input_is_move() {
        // Act
        var result = target.isValid("move to the moon", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_move_with_spaces() {
        // Act
        var result = target.isValid("    move to the moon   ", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_move_with_caps() {
        // Act
        var result = target.isValid("MoVe to the MOON", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_foobar() {

        // Act
        var result = target.isValid("foobar", null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_null() {

        // Act
        var result = target.isValid(null, null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_only_one_word() {

        // Act
        var result = target.isValid("move", null);

        // Assert
        assertFalse(result);
    }
}