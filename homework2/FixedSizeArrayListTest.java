// A tester that tests all methods of FixedSizeArrayList class
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FixedSizeArrayListTest {
    private FixedSizeArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new FixedSizeArrayList<Integer>(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsForNegativeCapacity() {
        new FixedSizeArrayList<Integer>(-1);
    }

    @Test
    public void testDefaultConstructor() {
        list = new FixedSizeArrayList<Integer>();
        assertTrue(list.size() == 0);
    }

    @Test
    public void testAddAndGet() {
        list.add(10);
        assertEquals(Integer.valueOf(10), list.get(0));
        list.add(0, 20);
        assertEquals(Integer.valueOf(20), list.get(0));
        assertFalse(list.add(-1, 40));
        assertFalse(list.add(3, 40));
        list.add(40);
        list.add(40);
        list.add(40);
        assertFalse(list.add(40));
        assertFalse(list.add(0, 40));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsForInvalidIndex1() {
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsForInvalidIndex2() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsForInvalidIndex3() {
        list.get(100);
    }

    @Test
    public void testAddFirstAndLast() {
        list.addFirst(1);
        list.addLast(2);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.remove(0));
        assertEquals(Integer.valueOf(2), list.get(0));
    }

    @Test
    public void testRemoveFirst() {
        list.add(10);
        list.add(2);
        assertEquals(Integer.valueOf(10), list.removeFirst());
        assertEquals(Integer.valueOf(2), list.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        list.add(10);
        list.add(2);
        assertEquals(Integer.valueOf(2), list.removeLast());
        assertEquals(Integer.valueOf(10), list.removeLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveThrowsForInvalidIndex1() {
        list.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveThrowsForInvalidIndex2() {
        list.remove(-10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveThrowsForInvalidIndex3() {
        list.remove(100);
    }

    @Test
    public void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    public void testIndexOf() {
        list.add(5);
        list.add(10);
        assertEquals(1, list.indexOf(10));
        assertEquals(-1, list.indexOf(99));
    }

    @Test
    public void testAddNullThrows() {
        list.add(null);
        assertNull(list.get(0));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        assertTrue(list.size() == 2);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testisEmpty() {
        boolean result = list.isEmpty();
        assertTrue(result);
        list.add(50);
        result = list.isEmpty();
        assertFalse(result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet1() {
        list.set(-10, 80);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet2() {
        list.set(100, 80);
    }

    @Test
    public void testToArray() {
        list.add(50);
        list.add(50);
        list.add(50);
        Object[] arr = list.toArray();
        assertTrue(arr.length == 3);
        for (Object e : arr) {
            assertTrue(e.equals(50));
        }

    }

    @Test
    public void testAddAll1() {
        FixedSizeArrayList<Integer> toAdd = new FixedSizeArrayList<Integer>();
        toAdd.add(1);
        toAdd.add(2);
        toAdd.add(3);

        list.add(333);
        assertTrue(list.addAll(toAdd));
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(1), list.get(1));
        assertEquals(Integer.valueOf(2), list.get(2));
        assertEquals(Integer.valueOf(3), list.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAll2() {
        assertTrue(list.addAll(null));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddAll3() {
        FixedSizeArrayList<Integer> toAdd = new FixedSizeArrayList<Integer>();
        toAdd.add(1);
        toAdd.add(2);
        toAdd.add(3);
        toAdd.add(4);
        toAdd.add(5);

        list.add(333);
        assertTrue(list.addAll(toAdd));
    }

    @Test
    public void testClone() {
        list.add(10);
        FixedSizeArrayList<Integer> cloned = (FixedSizeArrayList<Integer>) list.clone();
        assertEquals(list.get(0), cloned.get(0));
        assertTrue(list.size() == cloned.size());
    }

    @Test
    public void testRemoveElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.remove(Integer.valueOf(20)));
        assertEquals(2, list.size());
        assertFalse(list.contains(20));
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(30), list.get(1));
        assertFalse(list.remove((Integer) 400));
    }

    @Test
    public void testGetFirstAndLast() {
        list.add(500);
        list.add(550);
        list.add(580);
        list.add(600);
        assertTrue(list.getFirst() == 500);
    }

    @Test
    public void testReversed() {
        list.add(1);
        list.add(2);
        list.add(3);

        FixedSizeArrayList<Integer> reversedList = (FixedSizeArrayList<Integer>) list.reversed();

        assertEquals(3, reversedList.size());
        assertEquals(Integer.valueOf(3), reversedList.get(0));
        assertEquals(Integer.valueOf(2), reversedList.get(1));
        assertEquals(Integer.valueOf(1), reversedList.get(2));
    }

    @Test
    public void testSubListFunctionality() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        List<Integer> subList = list.subList(1, 3);

        assertEquals(2, subList.size());
        assertEquals(Integer.valueOf(20), subList.get(0));
        assertEquals(Integer.valueOf(30), subList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListFromIndexNegative() {
        list.subList(-1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListToIndexGreaterThanSize() {
        list.add(10);
        list.add(20);
        list.subList(0, 3); // Size is only 2
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListFromIndexGreaterThanToIndex() {
        list.add(10);
        list.add(20);
        list.subList(2, 1);
    }

    @Test
    public void testLastIndexOf() {
        list.add(10);
        list.add(20);
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(2, list.lastIndexOf(10));
        assertEquals(3, list.lastIndexOf(20));
        assertEquals(4, list.lastIndexOf(30));
        assertEquals(-1, list.lastIndexOf(99)); // Element not in list
    }

    @Test
    public void testAddUntilFull() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertFalse(list.add(6)); // Should not add as capacity is 5
    }
}
