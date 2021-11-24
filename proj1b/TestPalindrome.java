import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        String input1 = "racecar";
        String input2 = "rererdssr";
        assertTrue(palindrome.isPalindrome(input1));
        assertFalse(palindrome.isPalindrome(input2));
    }

    @Test
    public void testSpecialPalindrome() {
        assertFalse(palindrome.isPalindrome("Aa"));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("A"));
    }

    @Test
    public void testOffByOnePalindrome() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("&%", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("aka", offByOne));
    }
}
