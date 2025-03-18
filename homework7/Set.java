/**
 * A generic Set interface that represents a collection of unique elements.
 * This interface extends Comparable to ensure elements can be compared.
 *
 * @param <T> The type of elements in the set, which must be Comparable.
 */
public interface Set<T extends Comparable<T>> extends Collection<T> {

    /**
     * Checks if all elements in the specified collection are present in this set.
     * This method should efficiently determine whether the current set contains
     * every element from the given collection.
     *
     * @param coll A collection of elements to check.
     * 
     * @return true if all elements in the collection exist in the set, false
     *         otherwise.
     */
    boolean containsAll(Collection<? extends T> coll);

    /**
     * Compares this set to another object for equality.
     * Two sets are considered equal if they contain the same elements.
     *
     * @param obj The object to compare with this set.
     * 
     * @return true if the given object is a Set with the same elements, false
     *         otherwise.
     */
    @Override
    boolean equals(Object obj);
}