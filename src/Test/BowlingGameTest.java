package Test;
import Classes.Game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
    private Game game;

    @BeforeEach
    void setupANewGame(){
        game = new Game();
    }

    private void rollZeros(int numberOfRolls){
        for (int i = 0; i < numberOfRolls; i++){
            game.addRoll(0);
        }
    }

    private void roll(int ... pinsArray){
        for (int pins : pinsArray){
            game.addRoll(pins);
        }
    }

    @Test
    void worstGamePossible(){
        rollZeros(20);
        assertEquals(0, game.totalScore());
    }
    @Test
    void onePinDown(){
        rollZeros(19);
        game.addRoll(1);
        assertEquals(1, game.totalScore());
    }
    @Test
    void gameWithoutStrike(){
        roll(0, 7, 8, 1, 5, 4, 1, 0, 6, 0, 9, 0, 0, 3, 5, 1, 3, 0, 1, 0);
        assertEquals(54, game.totalScore());
    }

    @Test
    void spareAndOnePinDown(){
        roll(5, 5, 1);
        rollZeros(17);
        assertEquals(12, game.totalScore());
    }

    @Test
    void noSpareThrown(){
        roll(0, 5, 5, 1);
        rollZeros(16);
        assertEquals(11, game.totalScore());
    }

    @Test
    void twoSparesAndOnePinsDown(){
        roll(5, 5, 5, 5, 1);
        rollZeros(15);
        assertEquals(15 + 11 + 1, game.totalScore());
    }

    @Test
    void strike(){
        roll(10, 1, 1);
        rollZeros(16);
        assertEquals(12 + 2, game.totalScore());
    }

    @Test
    void twoStrikes(){
        roll(10, 10, 1, 1);
        rollZeros(14);
        assertEquals(35, game.totalScore());
    }

    @Test
    void threeStrikes(){
        roll(10, 10, 10, 1, 1);
        rollZeros(12);
        assertEquals(65, game.totalScore());
    }

    @Test
    void twelveStrike(){
        roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(300, game.totalScore());
    }

    @Test
    void wurf10(){
        rollZeros(18);
        roll(6, 4, 3);
        assertEquals(13, game.totalScore());
    }

    @Test
    void wurf10WithStrike(){
        rollZeros(18);
        roll(10, 3, 2);
        assertEquals(15, game.totalScore());
    }


}
