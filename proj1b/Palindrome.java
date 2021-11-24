public class Palindrome {
    /**Returns a Deque that conclude the input String in the
     * same order
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        if (d.size() == 1 || d.size() == 0) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return isPalindromeHelper(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
