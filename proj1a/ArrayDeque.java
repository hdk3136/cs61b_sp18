public class ArrayDeque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size() * 2];
        nextFirst = other.size() - other.size() / 2;
        nextLast = nextFirst + other.size() + 1;
        if (other.nextFirst < other.nextLast) {
            System.arraycopy(other, other.nextFirst + 1, items, this.nextFirst + 1, other.size());
        } else {
            System.arraycopy(other, other.nextFirst + 1, items,
                     nextFirst + 1, other.items.length - 1 - other.nextFirst);
            System.arraycopy(other, 0, items,
                     nextFirst + other.items.length - other.nextFirst, other.nextLast);
        }
        this.size = other.size();
    }

    private int updateAdd(int n) {
        if (n == this.items.length - 1) {
            n = 0;
        } else {
            n += 1;
        }
        return n;
    }

    private int updateMinus(int n) {
        if (n == 0) {
            n = this.items.length - 1;
        } else {
            n -= 1;
        }
        return n;
    }

    private void resize() {
        if (nextFirst == nextLast + 1 || nextLast - nextFirst == this.size() - 1) {
            T[] newItems = (T[]) new Object[this.items.length * 2];
            int newNextFirst = this.items.length - this.items.length / 2;
            int newNextLast = newNextFirst + this.size() + 1;
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, newItems, newNextFirst + 1, this.size());
            } else {
                System.arraycopy(items, nextFirst + 1, newItems,
                         newNextFirst + 1, items.length - 1 - nextFirst);
                System.arraycopy(items, 0, newItems,
                         newNextFirst + items.length - nextFirst, nextLast);
            }
            items = newItems;
            nextFirst = newNextFirst;
            nextLast = newNextLast;
        }
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = updateMinus(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = updateAdd(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T first = items[updateAdd(nextFirst)];
            nextFirst = updateAdd(nextFirst);
            size -= 1;
            if (size == 0) {
                nextFirst = 4;
                nextLast = 5;
            }
            return first;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T last = items[updateMinus(nextLast)];
            nextLast = updateMinus(nextLast);
            size -= 1;
            if (size == 0) {
                nextFirst = 4;
                nextLast = 5;
            }
            return last;
        }
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }







}
