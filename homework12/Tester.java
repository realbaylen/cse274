import org.junit.Test;
import static org.junit.Assert.*;

public class Tester {

    @Test
    public void testMyStack() {
        Stack<Integer> stack = new Stack<>();

        assertTrue(stack.empty());

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(Integer.valueOf(30), stack.peek());
        assertFalse(stack.empty());

        assertEquals(1, stack.search(30));
        assertEquals(2, stack.search(20));
        assertEquals(3, stack.search(10));
        assertEquals(-1, stack.search(40));

        assertEquals(Integer.valueOf(30), stack.pop());
        assertEquals(Integer.valueOf(20), stack.pop());
        assertEquals(Integer.valueOf(10), stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void testMyQueue() {
        Queue<String> queue = new Queue<>();

        assertNull(queue.peek());
        assertNull(queue.poll());

        queue.add("A");
        queue.add("B");
        queue.add("C");

        assertEquals("A", queue.peek());
        assertEquals("A", queue.poll());
        assertEquals("B", queue.remove());
        assertEquals("C", queue.poll());

        assertNull(queue.poll());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testMyQueueRemoveThrows() {
        Queue<String> queue = new Queue<>();
        queue.remove();
    }

    @Test
    public void testMyDeque() {
        Deque<String> deque = new Deque<>();

        assertTrue(deque.add("X"));
        deque.addFirst("Y");
        deque.addLast("Z");

        assertTrue(deque.contains("X"));
        assertEquals("Y", deque.getFirst());
        assertEquals("Z", deque.getLast());

        assertEquals("Y", deque.remove());

        deque.addFirst("M");
        deque.addLast("N");

        assertEquals("M", deque.removeFirst());
        assertEquals("N", deque.removeLast());
        // X, Z
        assertEquals("X", deque.poll());
        assertEquals("Z", deque.poll());
        assertNull(deque.poll());

        deque.add("A");
        deque.add("B");

        assertEquals("A", deque.peek());
        assertEquals("A", deque.peekFirst());
        assertEquals("B", deque.peekLast());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeRemoveThrows() {
        Deque<Integer> deque = new Deque<>();
        deque.remove();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeRemoveFirstThrows() {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeRemoveLastThrows() {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeGetFirstThrows() {
        Deque<Integer> deque = new Deque<>();
        deque.getFirst();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeGetLastThrows() {
        Deque<Integer> deque = new Deque<>();
        deque.getLast();
    }

    @Test
    public void testMyStackToString() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("[1, 2, 3]", stack.toString());
    }

    @Test
    public void testMyQueueToString() {
        Queue<String> queue = new Queue<>();
        queue.add("apple");
        queue.add("banana");
        assertEquals("[apple, banana]", queue.toString());
    }

    @Test
    public void testMyDequeToString() {
        Deque<Character> deque = new Deque<>();
        deque.addFirst('A');
        deque.addLast('B');
        deque.addFirst('Z');
        assertEquals("[Z, A, B]", deque.toString());
    }

    @Test
    public void testPollFirstOnDeque() {
        Deque<String> deque = new Deque<>();
        deque.add("X");
        deque.add("Y");
        assertEquals("X", deque.pollFirst());
        assertEquals("[Y]", deque.toString());
    }

    @Test
    public void testPollLastOnDeque() {
        Deque<String> deque = new Deque<>();
        deque.add("X");
        deque.add("Y");
        assertEquals("Y", deque.pollLast());
        assertEquals("[X]", deque.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size(); i++) {
            sb.append(this.get(i));
            if (i < this.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
