import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Collection;
import java.util.Set;

public class HashMapTest {
    @Test
    public void testConstructorSizeisEmptyClear() {
        HashMap<String, Integer> map = new HashMap<>();
        assertEquals(0, map.size());

        // Optional: check that putting a value works after default construction
        map.put("a", 1);
        assertEquals(1, map.size());

        map = new HashMap<>(10);
        assertEquals(0, map.size());

        // Optional: check that putting a value works after custom capacity construction
        map.put("x", 100);
        assertEquals(1, map.size());

        // Create a new, empty custom HashMap.
        map = new HashMap<>();

        // Verify that the map is empty initially.
        assertTrue("Map should be empty initially", map.isEmpty());
        assertEquals("Initial map size should be 0", 0, map.size());

        // Insert several key-value pairs, including null key and null value.
        map.put("one", 1);
        map.put("two", 2);
        map.put(null, 3);
        map.put("four", null);

        // After insertion, the map should not be empty.
        assertFalse("Map should not be empty after adding elements", map.isEmpty());
        assertEquals("Map size should reflect the number of elements added", 4, map.size());

        // Now, clear the map.
        map.clear();

        // After clear, the map should be empty, size should be 0,
        // and the values collection should be empty.
        assertTrue("Map should be empty after clear()", map.isEmpty());
        assertEquals("Map size should be 0 after clear()", 0, map.size());
        assertTrue("Values collection should be empty after clear()", map.values().isEmpty());
    }

    @Test
    public void testGet() {
        // Use a small initial capacity to force rehashing if needed.
        HashMap<String, Integer> map = new HashMap<>(4);

        // Initially, non-existent keys (including null) should return null.
        assertNull("Non-existent key should return null", map.get("nonexistent"));
        assertNull("Null key should return null when not set", map.get(null));

        // Insert some values including one with a null key.
        map.put("one", 1);
        map.put("two", 2);
        map.put(null, 0); // This indirectly tests hash() for a null key.

        // Verify retrieval.
        assertEquals("Value for key 'one' should be 1", Integer.valueOf(1), map.get("one"));
        assertEquals("Value for key 'two' should be 2", Integer.valueOf(2), map.get("two"));
        assertEquals("Value for null key should be 0", Integer.valueOf(0), map.get(null));

        // Update null key to test that updates work.
        map.put(null, 10);
        assertEquals("After update, null key should return 10", Integer.valueOf(10), map.get(null));

        // Update an existing key.
        map.put("one", 100);
        assertEquals("After update, key 'one' should return 100", Integer.valueOf(100), map.get("one"));
    }

    @Test
    public void testPut() {
        // Start with a small capacity to ensure the grow method is triggered.
        HashMap<String, String> map = new HashMap<>(4);

        // Inserting a new key should return null.
        assertNull("Putting new key should return null", map.put("key1", "value1"));
        // Inserting the same key should return the previous value.
        assertEquals("Putting duplicate key should return previous value", "value1", map.put("key1", "value2"));
        // Check that get returns the updated value.
        assertEquals("Updated key 'key1' should return value2", "value2", map.get("key1"));

        // Test putting a null key.
        assertNull("Putting null key should return null", map.put(null, "nullValue"));
        assertEquals("Getting null key should return 'nullValue'", "nullValue", map.get(null));
        // Update the null key.
        assertEquals("Putting duplicate null key should return previous value", "nullValue",
                map.put(null, "newNullValue"));
        assertEquals("After update, null key should return 'newNullValue'", "newNullValue", map.get(null));

        // Insert additional elements to force the grow method (rehashing) to be
        // triggered.
        // With an initial capacity of 4 and a 0.75 threshold, inserting more than 3
        // elements will trigger growth.
        for (int i = 2; i <= 100; i++) {
            map.put("key" + i, "value" + i);
        }

        // There should be 11 keys now: "key1" through "key10" plus the null key.
        assertEquals("Map should contain 11 keys", 101, map.size());

        // Verify that all keys return the correct values after growth.
        assertEquals("Key 'key1' should still return 'value2'", "value2", map.get("key1"));
        for (int i = 2; i <= 100; i++) {
            assertEquals("Key 'key" + i + "' should return correct value",
                    "value" + i, map.get("key" + i));
        }
    }

    @Test
    public void testContainsKeyWithNull() {
        // Use a small capacity to force growth if needed.
        HashMap<String, Integer> map = new HashMap<>(4);

        // Initially, the map should not contain a null key.
        assertFalse("Map should not contain a null key initially", map.containsKey(null));

        // Insert a few keys including a null key.
        map.put("key1", 100);
        map.put("key2", 200);
        map.put(null, 50);

        // Verify that the map reports that it contains the keys, including the null
        // key.
        assertTrue("Map should contain key 'key1'", map.containsKey("key1"));
        assertTrue("Map should contain key 'key2'", map.containsKey("key2"));
        assertTrue("Map should contain the null key after insertion", map.containsKey(null));

        // Insert additional keys to force the grow() method to trigger.
        for (int i = 3; i <= 1000; i++) {
            map.put("key" + i, i * 1000);
        }

        // Check that all keys, including the null key, are still present.
        for (int i = 1; i <= 1000; i++) {
            assertTrue("Map should contain key 'key" + i + "'", map.containsKey("key" + i));
        }
        assertTrue("Map should still contain the null key after growth", map.containsKey(null));
    }

    @Test
    public void testContainsValueWithNull() {
        // Use a small capacity to force growth if needed.
        HashMap<String, String> map = new HashMap<>(4);

        // Initially, the map should not contain a null value.
        assertFalse("Map should not contain a null value initially", map.containsValue(null));

        // Insert key-value pairs, including one with a null value.
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("keyNull", null);

        // Verify that the map contains the values, including the null value.
        assertTrue("Map should contain value 'value1'", map.containsValue("value1"));
        assertTrue("Map should contain value 'value2'", map.containsValue("value2"));
        assertTrue("Map should contain a null value after insertion", map.containsValue(null));

        // Insert additional keys to force the grow() method to trigger.
        for (int i = 3; i <= 1000; i++) {
            map.put("key" + i, "value" + i);
        }

        // Verify that all expected values are still in the map after growth.
        assertTrue("Map should still contain value 'value1'", map.containsValue("value1"));
        assertTrue("Map should still contain value 'value2'", map.containsValue("value2"));
        for (int i = 3; i <= 1000; i++) {
            assertTrue("Map should contain value 'value" + i + "'", map.containsValue("value" + i));
        }
        // Check that the null value remains.
        assertTrue("Map should still contain a null value after growth", map.containsValue(null));
    }

    @Test
    public void testPutAll() {
        // Create an instance of the custom HashMap with a small capacity.
        HashMap<String, Integer> customMap = new HashMap<>(4);

        // Insert initial mappings.
        customMap.put("A", 1);
        customMap.put("B", 2);
        customMap.put("C", 3);

        // Create a standard Java Map to use with putAll.
        // Note: In a Map, duplicate keys are not allowed;
        // adding the same key twice (as below) results in the last value prevailing.
        Map<String, Integer> updateMap = new HashMap<>();
        updateMap.put("A", 10);
        updateMap.put("A", 20); // This effectively makes the value for "A" equal to 20.
        updateMap.put("B", 22);
        updateMap.put("D", 4);

        // Calling putAll should update existing keys ("A", "B") and add new ones ("D").
        customMap.putAll(updateMap);

        // Verify that the values were updated correctly.
        assertEquals("Value for key 'A' should be updated to 20", Integer.valueOf(20), customMap.get("A"));
        assertEquals("Value for key 'B' should be updated to 22", Integer.valueOf(22), customMap.get("B"));
        assertEquals("Value for key 'C' should remain 3", Integer.valueOf(3), customMap.get("C"));
        assertEquals("Value for key 'D' should be 4", Integer.valueOf(4), customMap.get("D"));

        // Verify overall size.
        // Initial map had 3 keys, and updateMap provides mappings for A, B, and D (with
        // A and B updated).
        // So the total number of keys should be 4.
        assertEquals("Map size should be 4", 4, customMap.size());
    }

    @Test
    public void testReplace() {
        // Create an instance of the custom HashMap.
        HashMap<String, String> customMap = new HashMap<>(4);

        // Insert initial mappings including null key and a key with a null value.
        customMap.put("key1", "value1");
        customMap.put("key2", "value2");
        customMap.put(null, "nullValue");
        customMap.put("keyNull", null);

        // Replace the value for an existing key.
        String oldValue = customMap.replace("key1", "newValue1");
        assertEquals("Replace should return the old value for key 'key1'", "value1", oldValue);
        assertEquals("After replace, key 'key1' should have new value", "newValue1", customMap.get("key1"));

        // Replace the value for a null key.
        oldValue = customMap.replace(null, "newNullValue");
        assertEquals("Replace should return the old value for null key", "nullValue", oldValue);
        assertEquals("After replace, null key should have new value", "newNullValue", customMap.get(null));

        // Replace the value for a key that previously mapped to null.
        oldValue = customMap.replace("keyNull", "nonNullValue");
        // Since the old value was null, replace should return null.
        assertNull("Replace should return null when previous value was null", oldValue);
        assertEquals("After replace, key 'keyNull' should have non-null value", "nonNullValue",
                customMap.get("keyNull"));

        // Attempt to replace a key that does not exist.
        oldValue = customMap.replace("nonexistent", "someValue");
        assertNull("Replace should return null for a key that doesn't exist", oldValue);
        assertNull("Map should still not contain key 'nonexistent'", customMap.get("nonexistent"));

        // Verify that the size of the map remains unchanged after replacements.
        // We initially added 4 keys and no replacement operation should add new keys.
        assertEquals("Map size should remain unchanged after replace operations", 4, customMap.size());
    }

    @Test
    public void testRemoveByKey() {
        // Create a new HashMap instance.
        HashMap<String, Integer> map = new HashMap<>(4);

        // Insert several key/value pairs:
        // - A non-null key ("A")
        // - A null key
        // - Another non-null key ("B")
        map.put("A", 1);
        map.put(null, 2);
        map.put("B", 3);

        // --- Testing the non-null branch ---
        Integer removed = map.remove("A");
        assertEquals("Removing key 'A' should return 1", Integer.valueOf(1), removed);
        // Confirm that other keys remain.
        assertNotNull("Key 'B' should still be present", map.get("B"));
        assertNotNull("Null key should still be present", map.get(null));

        // --- Testing the null key branch ---
        removed = map.remove(null);
        assertEquals("Removing null key should return 2", Integer.valueOf(2), removed);
        assertNull("After removal, null key should not be found", map.get(null));

        // --- Testing non-existent key ---
        removed = map.remove("NonExistent");
        assertNull("Removing a non-existent key should return null", removed);

        // Add another null key entry and test removal again.
        map.put(null, 100);
        removed = map.remove(null);
        assertEquals("Removing null key after re-adding should return 100", Integer.valueOf(100), removed);
    }

    @Test
    public void testRemoveByKeyValue() {
        // Create a HashMap with a small capacity.
        HashMap<String, String> map = new HashMap<>(4);

        // Try to remove a non-existent key-value pair.
        assertFalse("Removing non-existent key-value pair should return false",
                    map.remove("nonexistent", "value"));

        // Insert key-value pairs, including null keys and values.
        map.put("A", "apple");
        map.put("B", "banana");
        map.put(null, "nullKey");
        map.put("C", null);
        map.put("D", "durian");

        // Remove an entry with matching key and value.
        assertTrue("Should remove key 'A' with matching value", map.remove("A", "apple"));
        assertNull("After removal, get('A') should return null", map.get("A"));

        // Attempt to remove an entry with a correct key but non-matching value.
        assertFalse("Should not remove key 'B' when value does not match", map.remove("B", "wrongValue"));
        assertEquals("Value for 'B' should remain unchanged", "banana", map.get("B"));

        // Remove the null key with the matching value.
        assertTrue("Should remove null key with matching value", map.remove(null, "nullKey"));
        assertNull("After removal, get(null) should return null", map.get(null));

        // Remove a key that maps to a null value.
        assertTrue("Should remove key 'C' with matching null value", map.remove("C", null));
        assertNull("After removal, get('C') should return null", map.get("C"));

        // Remove another valid entry.
        assertTrue("Should remove key 'D' with matching value", map.remove("D", "durian"));
        assertNull("After removal, get('D') should return null", map.get("D"));

        // Verify that only key "B" remains.
        assertEquals("Map should contain only one key after removals", 1, map.size());
        assertTrue("Map should still contain key 'B'", map.containsKey("B"));
        assertEquals("Key 'B' should have value 'banana'", "banana", map.get("B"));
    }

    @Test
    public void testKeySet() {
        // Create an instance of your custom HashMap.
        HashMap<String, Integer> map = new HashMap<>();

        // Initially, the key set should be empty.
        Set<String> keys = map.keySet();
        assertTrue("Key set of an empty map should be empty", keys.isEmpty());

        // Insert key-value pairs, including a null key and duplicate key.
        map.put("A", 1);
        map.put("B", 2);
        map.put(null, 3); // include a null key
        map.put("C", 4);
        // Inserting "A" again should update the value, not add a duplicate key.
        map.put("A", 10);

        // Retrieve the key set.
        keys = map.keySet();

        // There should be exactly 4 unique keys: "A", "B", "C", and null.
        assertEquals("Key set should contain 4 keys", 4, keys.size());
        assertTrue("Key set should contain 'A'", keys.contains("A"));
        assertTrue("Key set should contain 'B'", keys.contains("B"));
        assertTrue("Key set should contain 'C'", keys.contains("C"));
        assertTrue("Key set should contain null", keys.contains(null));

        // Add another key to the map and verify the key set updates.
        map.put("D", 5);
        keys = map.keySet();
        assertEquals("Key set should contain 5 keys after adding 'D'", 5, keys.size());
        assertTrue("Key set should contain 'D'", keys.contains("D"));
    }

    @Test
    public void testValues() {
        // Create a custom HashMap with an initial capacity.
        HashMap<String, Integer> map = new HashMap<>(8);

        // Insert key-value pairs, including:
        // - a key with a null value,
        // - a null key, and
        // - duplicate values (from different keys).
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", null);
        map.put(null, 4);
        map.put("e", 2); // duplicate value for "b" and "e"

        // Get the collection of values.
        Collection<Integer> values = map.values();

        // The size of the values collection should equal the map's size.
        assertEquals("The size of the values collection should match the map size",
                map.size(), values.size());

        // Verify that the collection contains all inserted values.
        // Note: duplicate values can appear as separate entries since they are mapped
        // by different keys.
        assertTrue("Values should contain 1", values.contains(1));
        assertTrue("Values should contain 2", values.contains(2));
        assertTrue("Values should contain 3", values.contains(3));
        assertTrue("Values should contain null", values.contains(null));
        assertTrue("Values should contain 4", values.contains(4));
    }

    @Test
    public void testEquals() {
        // Create two empty maps.
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();

        // Branch 1: this == obj
        assertTrue("A map should equal itself", map1.equals(map1));

        // Branch 2: obj not an instance of Map
        assertFalse("A map should not equal an object of a different type", map1.equals("Not a Map"));

        // Populate both maps with identical entries (including null key and null
        // value).
        map1.put("A", "alpha");
        map1.put("B", "bravo");
        map1.put(null, "nullKey");
        map1.put("C", null);

        map2.put("B", "bravo");
        map2.put("A", "alpha");
        map2.put("C", null);
        map2.put(null, "nullKey");

        // Branch 3: same size, same entries => should be equal.
        assertTrue("Maps with identical entries should be equal", map1.equals(map2));
        assertTrue("Equality should be symmetric", map2.equals(map1));

        // Branch 4: size mismatch
        map2.put("D", "delta"); // now map2 has one extra entry
        assertFalse("Maps with different sizes should not be equal", map1.equals(map2));
        map2.remove("D"); // restore equality

        // Branch 5: For a given key, other.get(key) returns null while this map's value
        // is non-null.
        // Change map2's value for key "A" to null (while map1 has "alpha").
        map2.put("A", null);
        assertFalse("Maps should not be equal when one map has a null value for a key and the other a non-null value",
                map1.equals(map2));
        // Restore the original value.
        map2.put("A", "alpha");

        // Branch 6: For a given key, both maps return non-null values, but they are not
        // equal.
        // Change map2's value for key "B" to a different string.
        map2.put("B", "different");
        assertFalse("Maps should not be equal when values for a key differ", map1.equals(map2));
        // Restore equality.
        map2.put("B", "bravo");

        // Final check: maps should be equal again.
        assertTrue("Maps should be equal after restoring all entries", map1.equals(map2));
    }

    @Test
    public void testCloneMethod() {
        // Create and populate the original map.
        HashMap<String, Integer> original = new HashMap<>(16);
        original.put("A", 1);
        original.put("B", 2);
        original.put(null, 3);
        original.put("C", null);
        
        // Clone the map.
        HashMap<String, Integer> cloned = original.clone();
        
        // The cloned map should be a different instance.
        assertNotSame("Cloned map must be a different instance than the original", original, cloned);
        
        // Both maps should be equal initially.
        assertTrue("Cloned map should be equal to the original", original.equals(cloned));
        assertTrue("Original map should be equal to the cloned", cloned.equals(original));
        
        // Modify the original map.
        original.put("D", 4);
        
        // The modification should not affect the cloned map.
        assertFalse("After modifying the original, the maps should not be equal", original.equals(cloned));
        assertNull("Cloned map should not contain key 'D'", cloned.get("D"));
        
        // Modify the cloned map.
        cloned.put("E", 5);
        
        // The modification to the clone should not affect the original.
        assertFalse("After modifying the clone, the maps should not be equal", original.equals(cloned));
        assertNull("Original map should not contain key 'E'", original.get("E"));
        
        // Optionally, remove an entry from the clone and ensure the original is unaffected.
        cloned.remove("B");
        assertNotNull("Original map should still have key 'B'", original.get("B"));

        HashMap<String, Integer> empty = new HashMap<>();
        HashMap<String, Integer> emptyClone = empty.clone();
        
        // The empty clone must be a different instance.
        assertNotSame("Cloned empty map must be a different instance than the original", empty, emptyClone);
        
        // Both empty maps should be equal.
        assertTrue("Cloned empty map should be equal to the original empty map", empty.equals(emptyClone));
        assertEquals("Cloned empty map should have size 0", 0, emptyClone.size());
    
    }

    @Test
    public void testToString() {
        HashMap<String, Integer> map = new HashMap<>();

        // Test the empty map representation.
        String emptyStr = map.toString();
        assertNotNull("toString() should not return null", emptyStr);
        assertTrue("Empty map's toString should start with '{'", emptyStr.startsWith("{"));
        assertTrue("Empty map's toString should end with '}'", emptyStr.endsWith("}"));
        // Optionally, if your implementation returns "{}" for an empty map:
        // assertEquals("{}", emptyStr);

        // Add some entries.
        map.put("A", 1);
        map.put("B", 2);
        map.put(null, 3);
        map.put("C", null);

        String str = map.toString();
        // Since the order of entries may vary, verify that the representation includes
        // each pair.
        assertTrue("toString should contain 'A ,1'", str.contains("A, 1"));
        assertTrue("toString should contain 'B, 2'", str.contains("B, 2"));
        // For the null key, the output may print "null" as the key.
        assertTrue("toString should contain 'null, 3'", str.contains("null, 3"));
        assertTrue("toString should contain 'C, null'", str.contains("C, null"));
        // Also, verify that the representation is enclosed in braces.
        assertTrue("toString should start with '{'", str.startsWith("{"));
        assertTrue("toString should end with '}'", str.endsWith("}"));
    }
}
