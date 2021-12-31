package RBTreeQuestion;

public class RedBlackTree implements RedBlackTreeInterface {

    Node root;
    Node TNULL;

    public RedBlackTree() {
        TNULL = new Node();
        TNULL.color = Color.BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    @Override
    public void insert(int key) {
        Node node = new Node();
        node.key = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key < y.key) {
            y.left = node;
        } else {
            y.right = node;
        }
        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }
        if (node.parent.parent == null) {
            return;
        }
        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    @Override
    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Node maximum(Node node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    @Override
    public Node successor(Node node) {
        if (node.right != TNULL && node.right != null) {
            // find successor node in the right subtree
            return minimum(node.right);
        }
        Node p = node.parent;
        while (p != TNULL && p != null && node == p.right) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    @Override
    public Node predecessor(Node node) {
        if (node.left != TNULL && node.left != null) {
            // find predecessor node in the left subtree
            return maximum(node.left);
        }
        Node p = node.parent;
        while (p != TNULL && p != null && node == p.left) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    @Override
    public void leftRotate(Node node) {
        Node y = node.right;
        node.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = node;
        }
        y.parent = node.parent;
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.left) {
            node.parent.left = y;
        } else {
            node.parent.right = y;
        }
        y.left = node;
        node.parent = y;
    }

    @Override
    public void rightRotate(Node node) {
        Node y = node.left;
        node.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = node;
        }
        y.parent = node.parent;
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.right) {
            node.parent.right = y;
        } else {
            node.parent.left = y;
        }
        y.right = node;
        node.parent = y;
    }

    @Override
    public Node search(int target) {
        return searchHelper(this.root, target);
    }

    private Node searchHelper(Node node, int target) {
        if (node == TNULL || target == node.key) {
            return node;
        }
        if (target < node.key) {
            return searchHelper(node.left, target);
        }
        return searchHelper(node.right, target);
    }

    @Override
    public Integer getHeight() {
        return this.getHeightHelper(this.root);
    }

    private Integer getHeightHelper(Node node) {
        if (node == null) {
            return -1;
        }
        Integer leftTreeHeight = this.getHeightHelper(node.left);
        Integer rightTreeHeight = this.getHeightHelper(node.right);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }

    @Override
    public String inorder() {
        return inOrderHelper(this.root);
    }

    private String inOrderHelper(Node node) {
        String result = "";
        if (node != TNULL) {
            result = inOrderHelper(node.left) + node.key + " " + inOrderHelper(node.right);
        }
        return result;
    }

    @Override
    public void print(Node node) {
        printHelper(node, "", true);
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    private void printHelper(Node node, String s, boolean l) {
        if (node != TNULL) {
            System.out.print(s);
            if (l) {
                if (this.rootChecker(node)) {
                    System.out.print("ROOT--");
                } else {
                    System.out.print("R-----");
                }
                s += "      ";
            } else {
                System.out.print("L-----");
                s += "|     ";
            }
            String colorChecker = node.color == Color.RED ? "<R>" : "<B>";
            System.out.println(node.key + " " + colorChecker);
            printHelper(node.left, s, false);
            printHelper(node.right, s, true);
        }
    }

    private boolean rootChecker(Node node) {
        return (this.root == node);
    }

}