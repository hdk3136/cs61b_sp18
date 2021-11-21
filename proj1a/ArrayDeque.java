public class ArrayDeque<T> {
    private static class TArray{

    }
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
            System.arraycopy(other, other.nextFirst + 1, items, this.nextFirst + 1, other.items.length - 1 - other.nextFirst);
            System.arraycopy(other, 0, items, this.nextFirst + other.items.length - other.nextFirst, other.nextLast);
        }
        this.size = other.size();
    }

    public int UpdateAdd(int n) {
        if (n == this.items.length - 1) {
            n = 0;
        } else {
            n += 1;
        }
        return n;
    }

    public int UpdateMinus(int n) {
        if (n == 0) {
            n = this.items.length - 1;
        } else {
            n -= 1;
        }
        return n;
    }

    public void Resize() {
        if (nextFirst == nextLast + 1 || nextLast - nextFirst == this.size() - 1) {
            T[] new_items = (T[]) new Object[this.items.length * 2];
            int new_nextFirst = this.items.length- this.items.length / 2;
            int new_nextLast = new_nextFirst + this.size() + 1;
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, new_items, new_nextFirst + 1, this.size());
            } else {
                System.arraycopy(items, nextFirst + 1, new_items, new_nextFirst + 1, items.length - 1 - nextFirst);
                System.arraycopy(items, 0, new_items, new_nextFirst + items.length - nextFirst, nextLast);
            }
            items = new_items;
            nextFirst =new_nextFirst;
            nextLast = new_nextLast;
        }
    }

    public void addFirst(T item) {
        Resize();
        items[nextFirst] = item;
        nextFirst = UpdateMinus(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        Resize();
        items[nextLast] = item;
        nextLast = UpdateAdd(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < nextLast; j++) {
                System.out.print(items[j] + " ");
            }
        }
        System.out.println();

    }

    public T removeFirst() {
        nextFirst = UpdateAdd(nextFirst);
        size -= 1;
        return items[UpdateAdd(nextFirst)];
    }

    public T removeLast() {
        nextLast = UpdateMinus(nextLast);
        size -= 1;
        return items[UpdateMinus(nextLast)];
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }







}
