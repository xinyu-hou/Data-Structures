package SkiplistQuestion;

import java.util.ArrayList;
import java.util.List;

public class Skiplist implements SkiplistInterface {

    private List<Node> levels;

    public Skiplist() {
        this.levels = new ArrayList<>();
        this.levels.add(new Node(Integer.MIN_VALUE));
    }

    @Override
    public void insert(int target) {
        boolean findResult = this.search(target);
        if (!findResult) { // target does not exist
            Node NodeToAdd = new Node(target);
            Node SmallerOrEqualNode = this.getSmallerOrEqual(target);
            this.insertHelper(SmallerOrEqualNode, NodeToAdd);
            this.populateLevel(NodeToAdd);
        }
    }

    @Override
    public boolean delete(int target) {
        boolean findResult = this.search(target);
        if (findResult) { // target already exists
            // Get node that has the target value
            Node curr = this.getSmallerOrEqual(target);
            while (curr != null) {
                Node prev = curr.prev;
                Node next = curr.next;
                prev.next = next; // Remove the node with the target value
                if (next != null) {
                    next.prev = prev;
                }
                curr = curr.above;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean search(int target) {
        Node SmallerOrEqualNode = this.getSmallerOrEqual(target);
        return SmallerOrEqualNode.value == target;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node levelHead = this.getTopLevelHead(); // Get top level head
        int levelNum = this.levels.size() - 1;
        while (levelHead != null) {
            Node iter = levelHead;
            sb.append("Level Num " + levelNum + ": ");
            while (iter != null) { // Loop through every level
                sb.append(iter.value + ", ");
                iter = iter.next;
            }
            sb.append("\n");
            levelHead = levelHead.below;
            levelNum -= 1;
        }
        return sb.toString();
    }

    private void insertHelper(Node currNode, Node nodeToAdd) {
        // Update pointers
        Node nextNode = currNode.next;
        currNode.next = nodeToAdd;
        nodeToAdd.prev = currNode;
        if (nextNode != null) {
            nodeToAdd.next = nextNode;
            nextNode.prev = nodeToAdd;
        }
    }

    private void populateLevel(Node nodeToAdd) {
        Node prevNode = nodeToAdd.prev;
        Node currNode = nodeToAdd;
        while (this.flipCoin()) { // Keep populate levels if true
            while (prevNode.prev != null && prevNode.above == null) {
                prevNode = prevNode.prev; // Find a prev Node that has an above Node
            }
            if (prevNode.above == null) { // Add a new level
                Node dummyNode = new Node(Integer.MIN_VALUE);
                prevNode.above = dummyNode;
                dummyNode.below = prevNode;
                this.levels.add(prevNode.above);
            }
            prevNode = prevNode.above;
            Node originalNextNode = prevNode.next;
            Node newNode = new Node(nodeToAdd.value);
            currNode.above = newNode; // Link the levels between added nodes
            newNode.below = currNode;
            currNode = currNode.above;
            prevNode.next = currNode;
            currNode.prev = prevNode;
            if (originalNextNode != null) { // Link with the following nodes
                currNode.next = originalNextNode;
                originalNextNode.prev = currNode;
            }
        }
    }

    /**
     * Return the node with a value smaller than or equal to the
     * target value.
     *
     * @param target the target value
     * @return node with value smaller than or equal to the target value
     */
    private Node getSmallerOrEqual(int target) {
        Node curr = this.getTopLevelHead();
        while (curr != null) {
            if (curr.next == null || curr.next.value > target) {
                if (curr.below == null) {
                    break;
                }
                curr = curr.below;
            } else {
                curr = curr.next;
            }
        }
        return curr;
    }

    private boolean flipCoin() {
        return Math.random() < 0.5;
    }

    private Node getTopLevelHead() {
        int topLevel = this.levels.size() - 1;
        return this.levels.get(topLevel);
    }
}
