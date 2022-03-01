package byog.lab6;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMemoryGame {
    @Test
    public void testGenerateRandomString() {
        MemoryGame mg = new MemoryGame(40, 40, 666);
        assertEquals(3, mg.generateRandomString(3).length());
        //System.out.println(mg.generateRandomString(3));
    }
    @Test
    public void testDrawFrame() {
        MemoryGame mg = new MemoryGame(40, 40, 666);
        mg.drawFrame(mg.generateRandomString(3));
    }
    @Test
    public void testStartGame() {
        MemoryGame mg = new MemoryGame(40, 40, 664);
        mg.startGame();
    }

}
