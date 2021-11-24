public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int i) {
        N = i;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int off = x - y;
        return off == N || off == -N;
    }
}
