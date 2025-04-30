import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @Before
    public void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    public void testIsEmptyInitially() {
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }

    @Test
    public void testAddAndPeek() {
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(50);
        pq.add(20);
        pq.add(60);
        pq.add(70);
        pq.add(5); // very small value

        assertFalse(pq.isEmpty());
        assertEquals(8, pq.size());
        assertEquals(Integer.valueOf(5), pq.peek());
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekEmptyThrows() {
        pq.peek();
    }

    @Test
    public void testRemoveOrder() {
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(50);
        pq.add(20);
        pq.add(60);
        pq.add(70);
        pq.add(5);

        assertEquals(Integer.valueOf(5), pq.remove());
        assertEquals(Integer.valueOf(10), pq.remove());
        assertEquals(Integer.valueOf(20), pq.remove());
        assertEquals(Integer.valueOf(30), pq.remove());
        assertEquals(Integer.valueOf(40), pq.remove());
        assertEquals(Integer.valueOf(50), pq.remove());
        assertEquals(Integer.valueOf(60), pq.remove());
        assertEquals(Integer.valueOf(70), pq.remove());

        assertTrue(pq.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveEmptyThrows() {
        pq.remove();
    }

    @Test
    public void testClear() {
        pq.add(15);
        pq.add(25);
        pq.add(5);
        pq.clear();

        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());

        // Add again after clear
        pq.add(7);
        assertEquals(1, pq.size());
        assertEquals(Integer.valueOf(7), pq.peek());
    }

    @Test
    public void testToStringContainsAllElements() {
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(50);
        pq.add(20);
        pq.add(60);
        pq.add(70);
        pq.add(5);

        String str = pq.toString();
        assertTrue(str.contains("5"));
        assertTrue(str.contains("10"));
        assertTrue(str.contains("20"));
        assertTrue(str.contains("30"));
        assertTrue(str.contains("40"));
        assertTrue(str.contains("50"));
        assertTrue(str.contains("60"));
        assertTrue(str.contains("70"));
    }

    @Test
    public void testSortReturnsSortedOrder() {
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(50);
        pq.add(20);
        pq.add(60);
        pq.add(70);
        pq.add(5);

        List<Integer> sorted = (List<Integer>) pq.sort();
        assertEquals(Arrays.asList(5, 10, 20, 30, 40, 50, 60, 70), sorted);

        // Original queue should remain unchanged
        assertEquals(8, pq.size());
        assertEquals(Integer.valueOf(5), pq.peek());
    }

    @Test
    public void testHeapifyUpEdgeCase() {
        pq.add(100);
        pq.add(90);
        pq.add(80);
        pq.add(70);
        pq.add(60);
        pq.add(50);
        pq.add(40);
        pq.add(30);
        pq.add(20);
        pq.add(10);

        assertEquals(Integer.valueOf(10), pq.peek());
    }

    @Test
    public void testHeapifyDownEdgeCase() {
        pq.add(5);
        pq.add(15);
        pq.add(25);
        pq.add(35);
        pq.add(45);
        pq.add(55);
        pq.add(65);
        pq.remove(); // Remove 5

        assertEquals(Integer.valueOf(15), pq.peek());
    }
}