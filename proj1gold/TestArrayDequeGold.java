import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {

        StudentArrayDeque<Integer> act = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> exp = new ArrayDequeSolution<>();
        String message = "\n";

        while (true) {
            double p = StdRandom.uniform();
            Integer i = StdRandom.uniform(10);
            if (p < 0.25) {
                act.addFirst(i);
                exp.addFirst(i);
                message += "addFirst(" + i + ")\n";
                assertEquals(message, exp.get(0), act.get(0));
            } else if (p < 0.5) {
                act.addLast(i);
                exp.addLast(i);
                message += "addLast(" + i + ")\n";
                assertEquals(message, exp.get(act.size() - 1), act.get(act.size() - 1));
            } else if (p < 0.75 && exp.size() != 0) {
                Integer actF = act.removeFirst();
                Integer expF = exp.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, expF, actF);
            } else if (exp.size() != 0) {
                Integer actL = act.removeLast();
                Integer expL = exp.removeLast();
                message += "removeLast()\n";
                assertEquals(message, expL, actL);
            }

        }






    }
}
