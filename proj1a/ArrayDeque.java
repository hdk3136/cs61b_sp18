public class ArrayDeque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private double usageFactor;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
        usageFactor = 0.0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size() * 2];
        nextFirst = other.size() - other.size() / 2;
        nextLast = nextFirst + other.size() + 1;
        if (other.nextFirst < other.nextLast) {
            System.arraycopy(other.items, other.nextFirst + 1, items, this.nextFirst + 1, other.size());
        } else {
            System.arraycopy(other.items, other.nextFirst + 1, items,
                     nextFirst + 1, other.items.length - 1 - other.nextFirst);
            System.arraycopy(other.items, 0, items,
                     nextFirst + other.items.length - other.nextFirst, other.nextLast);
        }
        this.size = other.size();
        updateUsageFactor();
    }

    private void updateUsageFactor() {
        this.usageFactor = (double) (this.size / this.items.length);
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

    private void resizeBigger() {
        if (size + 2 >= items.length) {
            T[] bItems = (T[]) new Object[items.length * 2];
            int bNextFirst = items.length - items.length / 2;
            int bNextLast = bNextFirst + size + 1;
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, bItems, bNextFirst + 1, size);
            } else {
                System.arraycopy(items, nextFirst + 1, bItems,
                         bNextFirst + 1, items.length - 1 - nextFirst);
                System.arraycopy(items, 0, bItems,
                         bNextFirst + items.length - nextFirst, nextLast);
            }
            items = bItems;
            nextFirst = bNextFirst;
            nextLast = bNextLast;
        }
        updateUsageFactor();
    }

    private void resizeSmaller() {
        T[] sItems = (T[]) new Object[items.length / 2];
        int sNextFirst = items.length / 8;
        int sNextLast = sNextFirst + size + 1;
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, sItems, sNextFirst + 1, size);
        } else {
            System.arraycopy(items, nextFirst + 1, sItems,
                     sNextFirst + 1, items.length - 1 - nextFirst);
            System.arraycopy(items, 0, sItems,
                     sNextFirst + items.length - nextFirst, nextLast);
        }
        items = sItems;
        nextFirst = sNextFirst;
        nextLast = sNextLast;
    }

    public void addFirst(T item) {
        resizeBigger();
        items[nextFirst] = item;
        nextFirst = updateMinus(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resizeBigger();
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
            if (size >= 16 && usageFactor < 0.25) {
                resizeSmaller();
            }
            updateUsageFactor();
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
            if (size >= 16 && usageFactor < 0.25) {
                resizeSmaller();
            }
            updateUsageFactor();
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
