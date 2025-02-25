/**
 * Implementation of Singly Linked List
 *
 * @author Baylen Romanello
 */
public class SinglyLinkedList<T extends Comparable<T>> implements List<T> {
  private Node<T> head;
  private int size;

  public SinglyLinkedList() {
    head = null;
    size = 0;
  }

  @Override
  public boolean add(int index, T elem) { // Ω(1), O(n)
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    Node<T> newNode = new Node<T>(elem);
    if (index == 0) {
      newNode.next = head;
      head = newNode;
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      newNode.next = current.next;
      current.next = newNode;
    }

    size++;
    return true;
  }

  @Override
  public boolean add(T elem) { // O(n), Ω(1)
    /*
    Node<T> newNode = new Node<T>(elem);
    if (head == null) {

        head = newNode;
    } else {
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    size++;
    return true;
    */
    // OR
    return add(size, elem); // O(n), Ω(1)
  }

  @Override
  public void addFirst(T elem) { // O(1)
    add(0, elem);
  }

  @Override
  public void addLast(T elem) { // O(n)
    add(size, elem);
  }

  @Override
  public T get(int index) { // O(n)
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    return current.data;
  }

  @Override
  public int size() { // O(1)
    return size;
  }

  @Override
  public void clear() { // O(1)
    head = null;
    size = 0;
  }

  @Override
  public boolean isEmpty() { // O(1)
    return size == 0;
  }

  @Override
  public T getFirst() { // O(1)
    return get(0);
  }

  // Returns the index of the first occurrence of the specified element in the list
  // If the element is found, its index is returned; otherwise, returns -1
  // Traverses the list from the head, checking each node’s data
  // Time complexity: O(n) since it may need to scan the entire list in the worst case
  @Override
  public int indexOf(T elem) { // O(n)
    // TODO Auto-generated method stub
    Node<T> current = head;
    int i = 0;
    while (current != null) {
      if (current.data.equals(elem)) {
        return index;
      }
      current = current.next;
      i++;
    }
    return -1;
  }

  // Returns the index of the last occurrence of the specified element in the list
  // If the element is found multiple times, the index of its last occurrence is returned
  // If the element is not found, returns -1
  // Traverses the list from the head, updating the index each time a match is found
  // Time complexity: O(n) since it may need to scan the entire list in the worst case
  @Override
  public int lastIndexOf(T elem) { // O(n)
    // TODO Auto-generated method stub
    Node<T> current = head;
    int i = 0;
    int lastIndex = -1;
    while (current != null) {
      if (current.data.equals(elem)) {
        lastIndex = i;
      }
      current = current.next;
      i++;
    }
    return lastIndex;
  }

  // Checks if the specified element exists in the list
  // Calls indexOf(elem) to determine if the element is present
  // Returns true if the element is found, otherwise returns false
  // Time complexity: O(n) since indexOf(elem) performs a linear search
  @Override
  public boolean contains(T elem) { // O(n)
    // TODO Auto-generated method stub
    return indexOf(elem) != -1;
  }

  // Converts the linked list into an array and returns it
  // Creates a new array of the same size as the list
  // Uses unchecked casting since Java does not support generic array creation
  // Iterates through the list, copying each element into the array
  // Time complexity: O(n) since each element must be copied into the array
  @Override
  public T[] toArray() { // O(n)
    // TODO Auto-generated method stub
    T[] array = (T[]) new Comparable[size];
    Node<T> current = head;
    int i = 0;
    while (current != null) {
      array[i++] = current.data;
      current = current.next;
    }
    return array;
  }

  // Adds all elements from the given collection to the list
  // Converts the collection to an array and inserts each element using add(elem)
  // Since add(elem) may run in O(n) time, the overall complexity becomes O(n²)
  // Returns true after all elements have been added successfully
  @Override
  public boolean addAll(Collection<? extends T> coll) { // O(n^2)
    // TODO Auto-generated method stub
    for (T elem : coll) {
      add(elem);
    }
    return true;
  }

  // Removes the first occurrence of the specified element from the list
  // Returns false if the list is empty or if the element is not found
  // If the element is at the head, updates head to the next node
  // Otherwise, traverses the list to find the element and updates pointers to unlink it
  // Decreases the list size after successful removal
  // Best-case time complexity: Ω(1) when removing the head
  // Worst-case time complexity: O(n) when searching through the entire list
  @Override
  public boolean remove(T elem) { // Ω(1), O(n)
    // TODO Auto-generated method stub
    if (head == null) return false;
    if (head.data.equals(elem)) {
      head = head.next;
      size--;
      return true;
    }
    Node<T> current = head;
    while (current.next != null) {
      if (current.next.data.equals(elem)) {
        current.next = current.next.next;
        size--;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  // Removes the element at the specified index and returns its value
  // Throws IndexOutOfBoundsException if the index is out of range
  // If removing the first element, updates head to the next node
  // Otherwise, traverses to the node before the target and updates its next pointer
  // Decreases the list size after successful removal
  // Best-case time complexity: Ω(1) when removing the first element
  // Worst-case time complexity: O(n) when removing from the middle or end
  @Override
  public T remove(int index) { // Ω(1), O(n)
    // TODO Auto-generated method stub
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    if (index == 0) {
      T data = head.data;
      head = head.next;
      size--;
      return data;
    }
    Node<T> current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    T data = current.next.data;
    current.next = current.next.next;
    size--;
    return data;
  }

  // call the remove(int index) method with the proper argument
  @Override
  public T removeFirst() { // O(1)
    // TODO Auto-generated method stub
    return remove(0);
  }

  // call the remove(int index) method with the proper argument
  @Override
  public T removeLast() { // O(n)
    // TODO Auto-generated method stub
    return remove(size - 1);
  }

  // Returns a new list with the elements in reverse order
  // Creates an empty SinglyLinkedList to store reversed elements
  // Iterates through the original list and inserts each element at the beginning of the new list
  // Since addFirst() runs in O(1), the overall operation runs in O(n)
  // Time complexity: O(n) since each element is processed once
  @Override
  public List<T> reversed() { // O(n)
    // TODO Auto-generated method stub
    SinglyLinkedList<T> reversedList = new SinglyLinkedList<>();
    Node<T> current = head;
    while (current != null) {
      reversedList.addFirst(current.data);
      current = current.next;
    }
    return reversedList;
  }

  // Replaces the element at the specified index with a new value and returns the old value
  // Throws IndexOutOfBoundsException if the index is out of range
  // Traverses the list to reach the target index and updates the node’s data
  // The structure of the list remains unchanged
  // Time complexity: O(n) since traversal is required to find the node
  @Override
  public T set(int index, T elem) { // O(n)
    // TODO Auto-generated method stub
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    T oldData = current.data;
    current.data = elem;
    return oldData;
  }

  // Returns a new list containing elements from the specified range [fromIndex, toIndex)
  // Throws IndexOutOfBoundsException if indices are out of range or invalid
  // Traverses the list to reach fromIndex and iterates to toIndex, copying elements into a new list
  // Since addLast() runs in O(n) for a singly linked list, inserting each element takes additional
  // time
  // Time complexity: O(n²) due to repeated traversal during insertion
  @Override
  public List<T> subList(int fromIndex, int toIndex) { // O(n^2)
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addAll'");
  }

  // Creates and returns a new list that is a copy of the current list
  // Initializes an empty list and copies all elements using addAll()
  // Since addAll() runs in O(n²) for a singly linked list, the overall time complexity is O(n²)
  // Time complexity: O(n²) due to inefficient insertion operations in addAll()
  @Override
  public Object clone() { // O(n^2)
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addAll'");
  }

  // Returns a string representation of the list in the format {element1, element2, ...}
  // Iterates through the list, appending each element to a StringBuilder
  // Since string builder's append method inside a loop is O(n), and the loop
  // runs O(n) times, the overall time complexity is O(n²)
  // Example: For a list containing [1, 2, 3], this method returns "{1, 2, 3}"
  @Override
  public String toString() { // O(n^2)
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addAll'");
  }
}
