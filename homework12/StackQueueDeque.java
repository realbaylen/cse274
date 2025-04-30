import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Stack implementation using LinkedList.
 * @param <T> the type of elements in the stack
 */
class Stack<T extends Comparable<T>> {
    private LinkedList<T> list = new LinkedList<>();

    public T push(T item) {
        list.addFirst(item);
        return item;
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return list.removeFirst();
    }

    public T peek() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return list.getFirst();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public int search(Object o) {
        int index = list.indexOf(o);
        return index == -1 ? -1 : index + 1; // Convert to 1-based index
    }

    @Override
    public String toString() {
        LinkedList<T> reversedList = new LinkedList<>(list);
        java.util.Collections.reverse(reversedList); // Reverse the list
        return reversedList.toString();
    }
}

/**
 * Queue implementation using LinkedList.
 * @param <T> the type of elements in the queue
 */
class Queue<T extends Comparable<T>> {
    private LinkedList<T> list = new LinkedList<>();

    public boolean add(T item) {
        return list.add(item);
    }

    public T poll() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public T peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    public T remove() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return list.removeFirst();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

/**
 * Deque (double-ended queue) implementation using LinkedList.
 * @param <T> the type of elements in the deque
 */
class Deque<T extends Comparable<T>> {
    private LinkedList<T> list = new LinkedList<>();

    public boolean add(T item) {
        return list.add(item);
    }

    public void addFirst(T item) {
        list.addFirst(item);
    }

    public void addLast(T item) {
        list.addLast(item);
    }

    public boolean contains(T item) {
        return list.contains(item);
    }

    public T getFirst() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return list.getFirst();
    }

    public T getLast() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return list.getLast();
    }

    public T remove() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return list.removeFirst();
    }

    public T removeFirst() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return list.removeFirst();
    }

    public T removeLast() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return list.removeLast();
    }

    public T poll() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public T pollFirst() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public T pollLast() {
        return list.isEmpty() ? null : list.removeLast();
    }

    public T peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    public T peekFirst() {
        return list.isEmpty() ? null : list.getFirst();
    }

    public T peekLast() {
        return list.isEmpty() ? null : list.getLast();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

