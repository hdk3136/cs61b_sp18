import org.junit.Test;
import static org.junit.Assert.*;

public class testWordToDeque {
    @Test
    public void testWordToDeque() {
        String input = "justONEtest";
        Deque<Character> actual = Palindrome.wordToDeque(input);
        for (int i = 0; i < actual.size(); i++) {
            if (!(actual.get(i) == input.charAt(i))) {
                System.out.println("error! expected " + input.charAt(i)
                                   + " but get " + actual.get(i));
            }
        }
    }
}
