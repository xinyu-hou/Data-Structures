package RBTreeQuestion;

public interface RedBlackTreeInterface {

    /**
     * Insert the key into the tree.
     *
     * @param key a key
     */
    void insert(int key);

    /**
     * Find the minimum using the given node.
     *
     * @param node a node in the tree
     * @return the minimum
     */
    Node minimum(Node node);

    /**
     * Find the maximum using the given node.
     *
     * @param node a node in the tree
     * @return the maximum
     */
    Node maximum(Node node);

    /**
     * Return the successor of the given node.
     *
     * @param node a node in the tree
     * @return the successor of the given node
     */
    Node successor(Node node);

    /**
     * Return the predecessor of the given node.
     *
     * @param node a node in the tree
     * @return the predecessor of the given node
     */
    Node predecessor(Node node);

    /**
     * Perform the left-rotate operation on the given node.
     *
     * @param node a node in the tree
     */
    void leftRotate(Node node);

    /**
     * Perform the right-rotate operation on the given node.
     *
     * @param node a node in the tree
     */
    void rightRotate(Node node);

    /**
     * Return the node with the target value.
     *
     * @param target the target value
     * @return the target value node
     */
    Node search(int target);

    /**
     * Return the height of the tree.
     *
     * @return tree height
     */
    Integer getHeight();

    /**
     * Return a string of the nodes in order.
     */
    String inorder();

    /**
     * Print out the tree using the given node.
     *
     * @param node a node in the tree
     */
    void print(Node node);

    /**
     * Return the root node.
     *
     * @return root node
     */
    Node getRoot();

}
