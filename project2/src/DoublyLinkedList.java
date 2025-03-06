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
}
