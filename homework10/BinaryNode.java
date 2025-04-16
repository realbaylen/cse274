import java.util.LinkedList;

// Do not import anything else

public class BinaryNode<T extends Comparable<T>> {
  T data;
  BinaryNode<T> left, right;

  public BinaryNode(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  /**
   * Recursively calculates the total number of nodes in the subtree rooted at this node.
   *
   * @return the number of nodes in the subtree, including this node
   */
  public int size() {
    int leftSize = (left != null) ? left.size() : 0;
    int rightSize = (right != null) ? right.size() : 0;
    return 1 + leftSize + rightSize;
  }

  /**
   * Prints the pre-order traversal of the subtree rooted at this node. Order: root, left subtree,
   * right subtree.
   *
   * @return a String
   */
  public String preOrderTraversal() {
    StringBuilder output = new StringBuilder();

    output.append(data + " ");
    if (left != null) output.append(left.preOrderTraversal());
    if (right != null) output.append(right.preOrderTraversal());

    return output.toString();
  }

  /**
   * Prints the post-order traversal of the subtree rooted at this node. Order: left subtree, right
   * subtree, root.
   *
   * @return a String
   */
  public String postOrderTraversal() {
    StringBuilder output = new StringBuilder();

    if (left != null) output.append(left.postOrderTraversal());
    if (right != null) output.append(right.postOrderTraversal());
    output.append(data + " ");

    return output.toString();
  }

  /**
   * Prints the in-order traversal of the subtree rooted at this node. Order: left subtree, root,
   * right subtree.
   *
   * @return a String
   */
  public String inOrderTraversal() {
    StringBuilder output = new StringBuilder();

    if (left != null) output.append(left.inOrderTraversal());
    output.append(data + " ");
    if (right != null) output.append(right.inOrderTraversal());

    return output.toString();
  }

  /**
   * Performs a level-order (breadth-first) traversal of the tree and returns a space-separated
   * string of the node data. It uses a LinkedList to simulate queue behavior.
   *
   * @return a String representing the level-order traversal of the tree
   */
  public String levelOrderTraversal() {
    StringBuilder output = new StringBuilder();
    LinkedList<BinaryNode<T>> queue = new LinkedList<>();
    queue.add(this);

    while (!queue.isEmpty()) {
      BinaryNode<T> current = queue.removeFirst();
      output.append(current.data + " ");

      if (current.left != null) queue.add(current.left);
      if (current.right != null) queue.add(current.right);
    }

    return output.toString();
  }
}
