import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author amjadm
 */
public class HashSetTest {

    @Test
    public void testConstructorWithCapacity() {
        HashSet<Integer> set = new HashSet<>(10);
        assertNotNull(set); // Ensure the object is created
        assertEquals(0, set.size()); // Initial size should be 0
    }

    @Test
    public void testDefaultConstructor() {
        HashSet<Integer> set = new HashSet<>();
        assertNotNull(set); // Ensure the object is created
        assertEquals(0, set.size()); // Default size should start at 0
    }

    @Test
    public void testAdd() {
        HashSet<Integer> set = new HashSet<>();

        // Test adding a new element
        assertTrue(set.add(5)); // Should return true since it's a new element

        // Test adding a duplicate element
        assertFalse(set.add(5)); // Should return false since 5 is already in the set

        // Test adding multiple elements
        assertTrue(set.add(10));
        assertTrue(set.add(15));

        // Verify the size after insertions
        assertEquals(3, set.size());
    }

    @Test
    public void testAdd2() {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            // Test adding a new element
            assertTrue(set.add(i)); // Should return true since it's a new element
        }

        // Verify the size after insertions
        assertEquals(100, set.size());
    }

    @Test
    public void testContains() {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            // Test adding a new element
            assertTrue(set.add(i)); // Should return true since it's a new element
        }

        for (int i = 0; i < 100; i++) {
            // Test adding a new element
            assertTrue(set.contains(i)); // Should return true since it's a new element
        }

        // Verify the size after insertions
        assertFalse(set.contains(100));
    }

    @Test
    public void testAddAll() {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> otherSet = new HashSet<>();

        // Populate the second set
        otherSet.add(1);
        otherSet.add(2);
        otherSet.add(3);
        otherSet.add(4);
        otherSet.add(5);

        // Test adding a collection of new elements
        assertTrue(set.addAll(otherSet)); // Should return true since elements are new

        // Verify the size after insertion
        assertEquals(5, set.size());

        // Test adding duplicate elements
        assertFalse(set.addAll(otherSet)); // Should return false since they are already in the set

        // Test adding a mix of new and existing elements
        HashSet<Integer> mixedSet = new HashSet<>();
        mixedSet.add(3);
        mixedSet.add(4);
        mixedSet.add(5);
        mixedSet.add(6);
        mixedSet.add(7);

        assertTrue(set.addAll(mixedSet)); // Should return true since new elements (6,7) are added

        // Verify the final size
        assertEquals(7, set.size());
    }

    @Test
    public void testClear() {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        // Ensure set is not empty before clearing
        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        // Clear the set
        set.clear();

        // Verify the set is empty
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testIsEmpty() {
        HashSet<Integer> set = new HashSet<>();

        // Initially, the set should be empty
        assertTrue(set.isEmpty());

        // Add an element and check again
        set.add(5);
        assertFalse(set.isEmpty());

        // Remove the element and check again
        set.remove(5);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testRemove() {
        HashSet<Integer> set = new HashSet<>();

        // Add elements to the set
        set.add(10);
        set.add(20);
        set.add(30);

        // Ensure the elements exist before removal
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertTrue(set.contains(30));
        assertEquals(3, set.size());

        // Remove an existing element
        assertTrue(set.remove(20)); // Should return true
        assertFalse(set.contains(20)); // 20 should no longer be in the set
        assertEquals(2, set.size());

        // Attempt to remove an element that is not in the set
        assertFalse(set.remove(40)); // Should return false, as 40 was never added

        // Remove remaining elements
        assertTrue(set.remove(10));
        assertTrue(set.remove(30));

        // Ensure set is empty after all removals
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testContainsAll() {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> otherSet = new HashSet<>();

        // Add elements to both sets
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        otherSet.add(1);
        otherSet.add(2);
        otherSet.add(3);

        // Test if set contains all elements of otherSet
        assertTrue(set.containsAll(otherSet)); // Should return true

        // Add an element to otherSet that is not in set
        otherSet.add(10);

        // Now set should not contain all elements of otherSet
        assertFalse(set.containsAll(otherSet)); // Should return false

        // Edge case: Checking if a set contains all elements of an empty set
        HashSet<Integer> emptySet = new HashSet<>();
        assertTrue(set.containsAll(emptySet)); // Should return true (empty set is subset of any set)

        // Edge case: Checking if an empty set contains all elements of a non-empty set
        assertFalse(emptySet.containsAll(set)); // Should return false
    }

    @Test
    public void testToString() {
        HashSet<Integer> set = new HashSet<>();

        // Test empty set
        assertEquals("{}", set.toString());

        // Add elements and check format
        set.add(1);
        set.add(2);
        set.add(3);
        String result = set.toString();

        // Since HashSet doesn't guarantee order, we check all possible outputs
        assertTrue(result.equals("{1, 2, 3}") || result.equals("{1, 3, 2}") ||
                result.equals("{2, 1, 3}") || result.equals("{2, 3, 1}") ||
                result.equals("{3, 1, 2}") || result.equals("{3, 2, 1}"));

        // Test set with a single element
        HashSet<Integer> singleElementSet = new HashSet<>();
        singleElementSet.add(10);
        assertEquals("{10}", singleElementSet.toString());
    }

    @Test
    public void testEquals() {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        HashSet<Integer> differentSet = new HashSet<>();
        HashSet<Integer> emptySet = new HashSet<>();

        // Test equality on two empty sets
        assertTrue(set1.equals(set2));

        // Test inequality with an empty set
        set1.add(1);
        assertFalse(set1.equals(emptySet));

        // Add the same elements to both sets (order shouldn't matter)
        set2.add(1);
        set1.add(2);
        set2.add(2);
        set1.add(3);
        set2.add(3);

        assertTrue(set1.equals(set2)); // Sets have same elements, should be equal

        // Test with a different set
        differentSet.add(1);
        differentSet.add(2);
        assertFalse(set1.equals(differentSet)); // differentSet is missing 3, should be false

        // Test against a completely different type
        assertFalse(set1.equals("Not a Set")); // Should return false

        // Test sets with same elements but different internal structure (e.g., after
        // removals & re-adding)
        HashSet<Integer> restructuredSet = new HashSet<>();
        restructuredSet.add(3);
        restructuredSet.add(2);
        restructuredSet.add(1);
        assertTrue(set1.equals(restructuredSet)); // Order should not affect equality
    }
}