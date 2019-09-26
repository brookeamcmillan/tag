import org.improving.tag.Game;
import org.improving.tag.Player;
import org.improving.tag.SaveGameFactory;
import org.improving.tag.commands.LoadCommand;
import org.improving.tag.commands.SetNameCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoadCommandTests {

    private LoadCommand target;
    private TestInputOutput io;
    private Game game;
    private SaveGameFactory factory;

    @BeforeEach
    public void Arrange() {
        // Arrange
        io = new TestInputOutput();
        target = new LoadCommand(io, factory);
        game = mock(Game.class);
        factory = SaveGameFactory;

        Player player = new Player(null);
        player.setName("brooke");
        player.setHitPoints(50);
    }

    @Test
    public void execute_should_display_all_words_but_Brooke_McMillan() {
        //Act
        target.execute("@set name=brooke", game);

        //Assert
        assertEquals("Your name is now brooke.", io.lastText);
    }

    @Test
    public void execute_should_set_name() {
        Player player = new Player(null);
        player.setName("brooke");
        player.setHitPoints(50);
        player = spy(player);

        when(game.getPlayer()).thenReturn(player);

        //Act
        target.execute("@Set Name=brooke", game);

        //Assert
        verify(player).setName("brooke");
    }

    @Test
    public void execute_should_display_all_words_but_SetName_with_spaces() {
        Player player = new Player(null);
        player.setName("brooke");
        player.setHitPoints(50);

        when(game.getPlayer()).thenReturn(player);

        //Act
        target.execute("  @Set Name=brooke  ", game);

        //Assert
        verify(player).setName("brooke");
        verify(game, times(2)).getPlayer();
    }

    @Test
    public void isValid_should_be_true_when_input_is_SetName() {
        // Act
        var result = target.isValid("@set name=brooke", game);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_SetName_with_spaces() {
        // Act
        var result = target.isValid("  @Set Name= brooke  ", game);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_SetName_with_caps() {
        // Act
        var result = target.isValid("@SeT nAme=hello", game);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_foobar() {

        // Act
        var result = target.isValid("foobar", game);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_null() {

        // Act
        var result = target.isValid(null, game);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_only_one_word() {

        // Act
        var result = target.isValid("SetName", game);

        // Assert
        assertFalse(result);
    }
}