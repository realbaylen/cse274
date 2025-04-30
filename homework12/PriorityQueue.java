import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// DO NOT IMPORT MORE CLASSES

/**
 * A generic PriorityQueue implementation using a min-heap with an ArrayList.
 *
 * <p>1- Since we are using an ArrayList, adding an element may have a linear (O(n)) time complexity
 * when resizing is needed, but the amortized time complexity remains O(log n). 2- The heap should
 * starts from index 1, and index 0 is left null to make parent/child formulas cleaner. 3- We are
 * implementing MinHeap
 *
 * @author ADD YOUR NAME HERE
 */
public class PriorityQueue<T extends Comparable<T>> {
  private List<T> heap;

  /** Default constructor initializes an empty heap with a dummy element at index 0. */
  public PriorityQueue() {
    heap = new ArrayList<>();
    heap.add(null); // Dummy element at index 0
  }

  /**
   * Adds a new element to the priority queue.
   *
   * @param value the element to add
   */
  public void add(T value) {
    if (value == null) {
        throw new IllegalArgumentException("Cannot add null to the priority queue.");
    }
    heap.add(value); // Add the new element to the end of the heap
    heapifyUp(heap.size() - 1); // Restore the heap property
  }

  // Helper method to maintain heap order after adding an element
  private void heapifyUp(int index) {
    while (index > 1) { // While not at the root
        int parentIndex = index / 2;
        if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex); // Swap with the parent if smaller
            index = parentIndex; // Move up to the parent's index
        } else {
            break; // Heap property is satisfied
        }
    }
  }

  // Helper method to swap two elements
  private void swap(int i, int j) {
    T temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /**
   * Removes and returns the smallest element (root) from the priority queue.
   *
   * @return the smallest element
   * @throws IllegalStateException if the heap is empty
   */
  public T remove() {
    if (isEmpty()) {
      throw new IllegalStateException("Priority queue is empty.");
    }
    T root = heap.get(1);
    T last = heap.remove(heap.size() - 1);
    if (!isEmpty()) {
      heap.set(1, last);
      heapifyDown(1);
    }
    return root;
  }

  // Helper method to maintain heap order after removal
  private void heapifyDown(int index) {
    int left, right, smallest;

    while (index < heap.size()) {
      left = 2 * index;
      right = 2 * index + 1;
      smallest = index;

      if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
        smallest = left;
      }

      if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
        smallest = right;
      }

      if (smallest != index) {
        swap(index, smallest);
        index = smallest;
      } else {
        break;
      }
    }
  }

  /**
   * Returns the smallest element without removing it.
   *
   * @return the smallest element
   * @throws IllegalStateException if the heap is empty
   */
  public T peek() {
    if (isEmpty()) {
        throw new IllegalStateException("Priority queue is empty.");
    }
    return heap.get(1); // The smallest element is at the root (index 1)
  }

  /** Clears all elements from the priority queue. */
  public void clear() {
    heap.clear();
    heap.add(null); // Re-add the dummy element at index 0
  }

  /**
   * Returns true if the priority queue is empty.
   *
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return heap.size() == 1; // Only the dummy element exists
  }

  /**
   * Returns the number of elements in the priority queue.
   *
   * @return the size of the queue
   */
  public int size() {
    return heap.size() - 1; // Exclude the dummy element
  }

  /**
   * Returns a sorted array of the elements without modifying the priority queue.
   *
   * @return a sorted array
   */
  public Collection<T> sort() {
    List<T> copy = new ArrayList<>(heap.subList(1, heap.size())); // Exclude dummy element
    copy.sort(Comparable::compareTo); // Sort the copy
    return copy;
  }

  /**
   * Returns a string representation of the priority queue.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return heap.subList(1, heap.size()).toString(); // Exclude dummy element
  }
}
