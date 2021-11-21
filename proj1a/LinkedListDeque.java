public class LinkedListDeque<T> {
    private class TNode {
        T item;
        TNode previous;
        TNode next;

        TNode() {

        }

        TNode(T it, TNode pre, TNode nex) {
            item = it;
            previous = pre;
            next = nex;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode();
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode();
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size; i++) {
            this.addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        size += 1;
        TNode newNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.previous = newNode;
        sentinel.next = newNode;
    }

    public void addLast(T item) {
        size += 1;
        TNode newNode = new TNode(item, sentinel.previous, sentinel);
        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T first = sentinel.next.item;
            size -= 1;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            return first;
        }
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T last = sentinel.previous.item;
            size -= 1;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            return last;
        }
    }

    public T get(int index) {
        TNode p = sentinel;
        int n = 0;
        while (p.next != sentinel) {
            p = p.next;
            if (index == n) {
                return p.item;
            }
            n++;
        }
        return null;
    }

    public T getRecursiveHelper(TNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }
}
