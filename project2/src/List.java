/**
 * An ordered collection, where the user has precise control 
 * over where in the list each element is inserted. 
 * 
 * Ordered does not mean sorted. It means the elements sits inside
 * the List based on the order in which elements are added 
 * and this order is preserved unless the List get sorted.
 * 
 * The user can access elements by their integer index 
 * (position in the list), and search for elements in the list.
 */
public interface List<T extends Comparable<T>> extends Collection<T> {
    
    /**
     * Adds an element at a specific index in the list.
     * 
     * @param index the position to insert the element
     * @param elem the element to be added
     * 
     * @return true if the element was successfully added
     */
    boolean add(int index, T elem);
    
    /**
     * Adds an element at the beginning of the list.
     * 
     * @param elem the element to be added
     */
    void addFirst(T elem);
    
    /**
     * Adds an element at the end of the list.
     * 
     * @param elem the element to be added
     */
    void addLast(T elem);
    
    /**
     * Retrieves the element at a specific index in the list.
     * 
     * @param index the position of the element to retrieve
     * 
     * @return the element at the specified index
     */
    T get(int index);
    
    /**
     * Retrieves the first element in the list.
     * 
     * @return the first element in the list
     */
    T getFirst();

    /**
     * Removes an element at a specific index in the list.
     * 
     * @param index the position of the element to remove
     * 
     * @return The element that was removed
     */
    T remove(int index);
    
    /**
     * Removes and returns the first element in the list.
     * 
     * @return the first element that was removed
     */
    T removeFirst();
    
    /**
     * Removes and returns the last element in the list.
     * 
     * @return the last element that was removed
     */
    T removeLast();
    
    /**
     * Returns a reversed version of the list.
     * 
     * @return a new list containing elements in reversed order
     */
    List<T> reversed();
    
    /**
     * Replaces the element at the specified position with the given element.
     * 
     * @param index the position to update
     * @param elem the new element
     * 
     * @return the element that was replaced
     */
    T set(int index, T elem);
    
    /**
     * Returns a view of the portion of this list between the 
     * specified indices.
     * 
     * @param fromIndex the starting index (inclusive)
     * @param toIndex the ending index (exclusive)
     * 
     * @return a sublist of this list
     */
    List<T> subList(int fromIndex, int toIndex);
    
    /**
     * Returns the index of the first occurrence of the specified element
     * in the list.
     * 
     * @param elem the element to search for
     * 
     * @return the index of the first occurrence, or -1 if not found
     */
    int indexOf(T elem);
    
    /**
     * Returns the index of the last occurrence of the specified 
     * element in the list.
     * 
     * @param elem the element to search for
     * @return the index of the last occurrence, or -1 if not found
     */
    int lastIndexOf(T elem);
}
