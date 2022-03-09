package hw2;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestUnit {
    @Test
    public void testPercolation() {
        Percolation test = new Percolation(10);
        assertFalse(test.isOpen(0, 0));
        assertFalse(test.isFull(0, 0));
        test.open(0, 5);
        test.open(1, 5);
        assertTrue(test.isFull(1, 5));

    }
}
