public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int off = x - y;
        return off == 1 || off == -1;
    }

}
