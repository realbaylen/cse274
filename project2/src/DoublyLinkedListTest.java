import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testConstructorCreatesEmptyList() {
        assertTrue("List should be empty initially", list.isEmpty());
        assertEquals("Size should be 0 initially", 0, list.size());
    }

    @Test
    public void testAddSingleElement() {
        assertTrue("Adding element should return true", list.add(10));
        assertTrue("Adding element should return true", list.add(100));
        assertFalse("List should not be empty after adding an element", list.isEmpty());
        assertEquals("Size should be 1 after adding one element", 2, list.size());
        assertEquals("First element should be 10", Integer.valueOf(100), list.get(1));
        assertEquals("First element should be 10", Integer.valueOf(10), list.get(0));
    }

    @Test
    public void testAddMultipleElements() {
        assertTrue(list.add(10));
        assertTrue(list.add(20));
        assertTrue(list.add(30));
        
        assertEquals("Size should be 3 after adding three elements", 3, list.size());
        assertEquals("First element should be 10", Integer.valueOf(10), list.get(0));
        assertEquals("Second element should be 20", Integer.valueOf(20), list.get(1));
        assertEquals("Third element should be 30", Integer.valueOf(30), list.get(2));
    }

    @Test
    public void testAddElementToEmptyList() {
        assertTrue(list.add(5));
        assertEquals("Size should be 1 after adding one element", 1, list.size());
        assertEquals("First element should be 5", Integer.valueOf(5), list.get(0));
    }

    @Test
    public void testAddElementsMaintainsOrder() {
        list.add(1);
        list.add(2);
        list.add(3);
        
        assertEquals("First element should be 1", Integer.valueOf(1), list.get(0));
        assertEquals("Second element should be 2", Integer.valueOf(2), list.get(1));
        assertEquals("Third element should be 3", Integer.valueOf(3), list.get(2));
    }

    @Test
    public void testAddWithIndex() {
        list.add(-34);
        list.add(15);
        list.add(89);
        
        assertEquals("First element should be -34", Integer.valueOf(-34), list.getFirst());
        assertEquals("Second element should be 15", Integer.valueOf(15), list.get(1));
        assertEquals("Third element should be 89", Integer.valueOf(89), list.get(2));

        list.addFirst(333);
        list.addLast(-666);
        list.add(2, 10);
        list.add(2, 12);
        assertEquals("There should be 7 elements in the list now", list.size(), 7);
        assertEquals("First element should be 333", Integer.valueOf(333), list.getFirst());
        assertEquals("The Last element should be -666", Integer.valueOf(-666), list.get(6));
        assertEquals("The third element should be 12", Integer.valueOf(12), list.get(2));
        assertEquals("The fifth element should be 15", Integer.valueOf(15), list.get(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddExceptions1() {
        list.add(-1, 40);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddExceptions2() {
        list.add(1, 40);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetExceptions1() {
        list.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetExceptions2() {
        list.get(-1);
    }

    @Test
    public void testClear() {
        list.add(-34);
        list.add(15);
        list.add(89);
        list.clear();

        assertTrue("List should be empty initially", list.isEmpty());
        assertEquals("Size should be 0 initially", 0, list.size());
    }

    @Test
    public void testIndexOf() {
        list.add(-34);
        list.add(15);
        list.add(89);
        list.addFirst(333);
        list.addLast(-666);
        list.add(2, 10);
        list.add(2, 12);
        list.add(-34);
        assertEquals("The index should be 5", list.indexOf(89), 5);
        assertEquals("The index should be 1", list.indexOf(-34), 1);
        assertEquals("The index should be -1", list.indexOf(-343434), -1);
        assertEquals("The index should be 7", list.lastIndexOf(-34), 7);
        assertEquals("The index should be -1", list.lastIndexOf(-343434), -1);
        
        assertTrue("The result must be true", list.contains(-666));
        assertFalse("The result must be false", list.contains(0));
    }

    @Test
    public void testAddAll() {
        list.add(333);
        list.add(-666);
        
        DoublyLinkedList<Integer> coll = new DoublyLinkedList<Integer>();
        coll.add(10);
        coll.add(12);
        coll.addFirst(-34);
        list.addAll(coll);

        assertEquals("The size should be 5", list.size(), 5);
        assertEquals("The last element is 12", list.get(4), Integer.valueOf(12));
        assertEquals("The last element is 333", list.get(0), Integer.valueOf(333));
    }
    @Test
    public void testRemoveAndSetFunctions() {
        assertFalse(list.remove(Integer.valueOf(444)));
        for (int i = 1; i < 20; i++) {
            list.add(i);
        }
        assertEquals(list.remove(2), Integer.valueOf(3));
        assertEquals(list.set(16, 55555), Integer.valueOf(18));
    }

    @Test
    public void testRemoveFunctions() {
        assertFalse(list.remove(Integer.valueOf(444)));
        list.add(-34);
        list.add(15);
        list.add(89);
        list.add(333);
        list.add(-666);
        list.add(2, 10);
        list.add(2, 12);
        list.add(-34);

        list.remove(Integer.valueOf(-34));
        assertEquals("The first element should be 15", list.get(0), Integer.valueOf(15));

        list.removeFirst();
        assertEquals("The first element should be 12", list.get(0), Integer.valueOf(12));

        list.removeLast();
        assertEquals("The last element should be -666", list.get(list.size() - 1), Integer.valueOf(-666));

        list.remove(3);
        assertEquals("The element at the index of 3 should be -666", list.get(3), Integer.valueOf(-666));

        list.remove(Integer.valueOf(10));
        assertEquals("The element at the index of 1 should be 89", list.get(1), Integer.valueOf(89));

        list.remove(Integer.valueOf(-666));
        assertEquals("The last element of the list should be 89", list.get(list.size() - 1), Integer.valueOf(89));

        assertFalse("The result should be false", list.remove(Integer.valueOf(99999)));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove1() {
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove2() {
        list.remove(1);
    }

    @Test
    public void testRReversed() {
        list.add(-34);
        list.add(15);
        list.add(89);
        list.add(333);
        list.add(-666);
        list.add(2, 10);
        list.add(2, 12);
        list.add(-34);
        DoublyLinkedList<Integer> rev = (DoublyLinkedList<Integer>) list.reversed();

        Object[] l1 = list.toArray();
        Object[] l2 = rev.toArray();
        for (int i = 0; i < l1.length; i++) {
            assertTrue(l1[i].equals(l2[l2.length - 1 - i]));
        }
    }

    @Test
    public void testSet() {
        list.add(-34);
        list.add(15);
        list.add(89);
        list.add(333);
        list.add(-666);
        list.add(2, 10);
        list.add(2, 12);
        list.add(-34);

        list.set(0, 1111);
        list.set(3, 2222);
        list.set(7, 9999);

        assertEquals("The first element should be 1111", list.get(0), Integer.valueOf(1111));
        assertEquals("The forth element should be 2222", list.get(3), Integer.valueOf(2222));
        assertEquals("The last element should be 9999", list.get(list.size() - 1), Integer.valueOf(9999));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet1() {
        list.set(-1, 8888);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet2() {
        list.set(1, 8888);
    }

    @Test
    public void testSubList() {
        list.add(-34);
        list.add(15);
        list.add(89);
        list.add(333);
        list.add(-666);
        list.add(10);
        list.add(12);
        list.add(-37);

        DoublyLinkedList<Integer> sub1 = (DoublyLinkedList<Integer>) list.subList(0, 1);
        assertEquals("The element should be -34", sub1.get(0), Integer.valueOf(-34));
        assertTrue("The size should be 1", sub1.size() == 1);

        DoublyLinkedList<Integer> sub2 = (DoublyLinkedList<Integer>) list.subList(list.size() - 1, list.size());
        assertEquals("The element should be -37", sub2.get(0), Integer.valueOf(-37));
        assertTrue("The size should be 1", sub2.size() == 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList1() {
        list.add(-34);
        list.add(15);
        list.subList(-1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList2() {
        list.add(-34);
        list.add(15);
        list.subList(1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList3() {
        list.add(-34);
        list.add(15);
        list.subList(3, 1);
    }

    @Test
    public void testClone1() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        DoublyLinkedList<Integer> clonedList = (DoublyLinkedList<Integer>) list.clone();
        assertEquals("Cloned list should have the same size as the original", list.size(), clonedList.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals("Elements should match in order", list.get(i), clonedList.get(i));
        }
    }

    @Test
    public void testToString() {
        assertEquals("{}", list.toString());

        list.add(5);
        assertEquals("{5}", list.toString());

        list.add(2);
        list.add(3);
        assertEquals("{5, 2, 3}", list.toString());
    }
}
