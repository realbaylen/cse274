// Do not import any java libraries such as Arrays , etc.
// Do not add any packages here

public class FixedSizeArrayList<T extends Comparable<T>> implements List<T> {

    private final T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public FixedSizeArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        // Java does not allow you to do it this way: this.array = new T[capacity];
        // Java also don't like this: this.array = (T[]) new Object[capacity];
        array = (T[]) new Comparable[capacity];
        size = 0;
    }

    public FixedSizeArrayList() {
        this(10);
    }

    @Override
    public boolean add(T elem) {
        if (size >= array.length) {
            return false;
        }
        array[size++] = elem;

        return true;
    }

    @Override
    public boolean add(int index, T elem) {
        if (index < 0 || index > size || size >= array.length) {
            return false;
        }

        // pushing elements to the right
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        // You can also do the loop this way
        // not recommended though
        // for (int i = size; i > index; array[i] = array[i-- - 1]);

        // putting the new element at the given index
        array[index] = elem;
        size++; // increasing the value of the size by 1

        return true;
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
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }

        return array[index];
    }

    // 
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        T removedElement = array[index];
        // pushing elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        // reducing the size by 1
        // then setting the element at the index of size to null
        array[--size] = null;

        return removedElement;
    }

    // removes the first element of the list using the remove(int) method
    @Override
    public T removeFirst() {
        return remove(0);
    }

    // removes the last element of the list using the remove(int) method
    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    // returns the size instance variable
    @Override
    public int size() {
        return size;
    }

    // returns true if the size is 0, and false otherwise
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Sets the given elem at the given index
    // returns the element that was replaced at the index
    // if the index is not valied, it sould throw:
    //    IndexOutOfBoundsException("Index out of bounds")
    @Override
    public T set(int index, T elem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        T oldElem = array[index];
        array[index] = elem;
        return oldElem;
    }

    // Sets all elements of the array with null using the set(int, T) method
    // and resets the size to 0
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Returns a seperate, independant copy of the array
    @Override
    public T[] toArray() {
        T[] newArray = (T[]) new Comparable[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    // Adds the given coll to the array. goes through the given coll
    // and using the add(T) method add all the elements of coll to 
    // the array.
    //
    // For looping through the coll, you should call the toArray() method
    // on it to make the coll iterable so you can loop through it
    // 
    // if the given coll is null it should throw: 
    //     NullPointerException("Collection cannot be null")
    // if there is not enough space in the array it should throw:
    //     IllegalStateException("Not enough space to add all elements");
    // 
    @Override
    public boolean addAll(Collection<? extends T> coll) {
        if (coll == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        T[] collArray = (T[]) coll.toArray();
        if (size + collArray.length > array.length) {
            throw new IllegalStateException("Not enough space to add all elements");
        }

        for (T elem : collArray) {
            add(elem);
        }

        return true;
    }

    // Returns the index of the first occurrence of the specified element.
    // Returns -1 if the element is not found.
    @Override
    public int indexOf(T elem) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    // Checks if the list contains a specific element.
    // using indexOf(T) method
    @Override
    public boolean contains(T elem) {
        return indexOf(elem) != -1;
    }

    // Removes the first occurrence of the given element from the list.
    // calls the other remove(i) method once finds the index of the given element
    // using indexOf(T) method
    @Override
    public boolean remove(T elem) {
        int index = indexOf(elem);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    // Returns the first element in the list
    // calls other get(int) method
    @Override
    public T getFirst() {
        // TODO
        return null;
    }

    // Returns a new list containing the elements in reverse order.
    @Override
    public List<T> reversed() {
        // TODO
        return array;
    }

    // Returns a sublist from the given start index (inclusive) to end index (exclusive).
    // Should check if the indices are valid.
    // Throws an IndexOutOfBoundsException if indices are invalid.
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO
        return array;
    }

    // Returns the index of the last occurrence of the specified element.
    // Returns -1 if the element is not found.
    @Override
    public int lastIndexOf(T elem) {
        // TODO
        return -100;
    }

    // Creates and returns a deep copy of this object
    // Overriding this method from the Object class
    @Override
    public Object clone() {
        // TODO
        return this;
    }

}
