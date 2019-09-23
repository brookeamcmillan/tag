import org.improving.tag.InputOutput;
import org.improving.tag.commands.EatCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EatCommandTests {

    EatCommand target;
    TestInputOutput io;

    @BeforeEach
    public void Arrange() {
        // Arrange
        io = new TestInputOutput();
        target = new EatCommand(io);

    }
    @Test
    public  void execute_should_return__phrase() {
        //Act
        target.execute(null, null);

        //Assert
        assertEquals("You're back to full health.", io.lastText);
    }

    @Test
    public void isValid_should_be_true_when_input_is_eat() {
        // Act
        var result = target.isValid( "eat", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_eat_with_spaces() {
        // Act
        var result = target.isValid( "eat", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_eat_with_caps() {
        // Act
        var result = target.isValid( "eAt", null);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_foobar() {

        // Act
        var result = target.isValid( "foobar", null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_null() {

        // Act
        var result = target.isValid( null, null);

        // Assert
        assertFalse(result);
    }

}

