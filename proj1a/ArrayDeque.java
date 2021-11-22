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

    private void updateUsageFactor() {
        this.usageFactor = (double) size / (double) items.length;
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
            updateUsageFactor();
            if (items.length >= 16 && usageFactor < 0.25) {
                resizeSmaller();
            }
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
            updateUsageFactor();
            if (items.length >= 16 && usageFactor < 0.25) {
                resizeSmaller();
            }
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
        return items[(updateAdd(nextFirst) + index) % items.length];
    }







}
