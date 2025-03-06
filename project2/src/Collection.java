/**
 * The root interface in the collection hierarchy.
 * 
 * A collection represents a group of objects, known as its elements.
 * Some collections allow duplicate elements and others do not.
 * Some are ordered, and others are unordered.
 * 
 * Generic Collection Interface defining standard operations for a
 * collection. The elements in the collection must implement
 * Comparable<T> to allow comparisons.
 */
public interface Collection<T extends Comparable<T>> {

    /**
     * Returns true if this collection changed as a result of the call.
     * (Returns false if this collection does not permit duplicates
     * and already contains the specified element.)
     * 
     * @param elem the element to add
     * 
     * @return true if the element was added, false if unsuccessfull
     */
    boolean add(T elem);

    /**
     * Adds all elements from another collection to this collection.
     * Returns true if this collection changed as a result of the call.
     * (This could replace the old collection or adds elements to
     * specific parts of the old collection)
     * 
     * @param c the collection containing elements to be added
     * 
     * @return true if all elements were added, false if unsuccessfull
     */
    boolean addAll(Collection<? extends T> coll);

    /**
     * Removes all elements from the collection.
     */
    void clear();

    /**
     * Checks if the collection contains a specific element.
     * 
     * @param elem the element to check for
     * 
     * @return true if the element is found, false otherwise
     */
    boolean contains(T elem);

    /**
     * Checks if the collection is empty.
     * 
     * @return true if there are no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Removes a single instance of the specified element from this
     * collection.
     * Returns True if this collection changed as a result of the call.
     * 
     * @param elem the element to remove
     * 
     * @return the element that was removed
     */
    boolean remove(T elem);

    /**
     * Returns the number of elements in the collection.
     * 
     * @return the size of the collection
     */
    int size();

    /**
     * Converts the collection into an array.
     * 
     * @return an array containing all elements in the collection
     */
    T[] toArray();
}
