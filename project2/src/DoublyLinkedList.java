/**
 * The implementation of DoublyLinkedList class
 *
 * @author Baylen Romanello <====
 */
public class DoublyLinkedList<T extends Comparable<T>> implements List<T> {
  private Node2<T> head, tail;
  private int size;

  public DoublyLinkedList() {
    this.head = this.tail = null;
    this.size = 0;
  }

  @Override
  public boolean add(int index, T elem) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    Node2<T> newNode = new Node2<>(elem);

    if (head == null) { // list if empty
      head = tail = newNode;
    } else if (index == 0) { // newNode becomes new head
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    } else if (index == size) { // newNode becomes new tail
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    } else { // newNode inserted at index
      Node2<T> current;

      if (index < size / 2) { // start from head
        current = head;
        for (int i = 0; i < index; i++) {
          current = current.next;
        }
      } else { // start from tail
        current = tail;
        for (int i = size - 1; i > index; i--) {
          current = current.prev;
        }
      }
      newNode.next = current;
      newNode.prev = current.prev;
      current.prev.next = newNode;
      current.prev = newNode;
    }

    size++;
    return true;
  }

  @Override
  public boolean add(T elem) {
    return add(size, elem);
  }

  @Override
  public void addFirst(T elem) {
    add(0, elem);
  }

  @Override
  public void addLast(T elem) {
    add(size, elem);
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    Node2<T> current;
    if (index < size / 2) {
      current = head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    } else {
      current = tail;
      for (int i = size - 1; i > index; i--) {
        current = current.prev;
      }
    }
    return current.data;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    head = tail = null;
    size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public T getFirst() {
    return get(0);
  }

  @Override
  public int indexOf(T elem) {
    Node2<T> current = head;

    for (int i = 0; i < size; i++) { // start from the head
      if (current.data.equals(elem)) {
        return i;
      }
      current = current.next;
    }

    return -1; // wasn't found
  }

  @Override
  public int lastIndexOf(T elem) {
    Node2<T> current = tail;
    for (int i = size - 1; i >= 0; i--) { // start from the tail
      if (current.data.equals(elem)) {
        return i;
      }
      current = current.prev;
    }

    return -1; // wasn't found
  }

  @Override
  public boolean contains(T elem) {
    return indexOf(elem) != -1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T[] toArray() {
    T[] newArray = (T[]) new Comparable[size];

    Node2<T> current = head;
    for (int i = 0; i < size; i++) {
      newArray[i] = current.data;
      current = current.next;
    }

    return newArray;
  }

  public boolean addAll(Collection<? extends T> coll) {
    T[] array = coll.toArray();
    for (int i = 0; i < array.length; i++) {
      addLast(array[i]);
    }
    return true;
  }
}
