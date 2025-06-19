public class AVLTree {
    private Node root;

    public AVLTree() {
        root = null;
    }

    int height(Node node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));

        return y;
    }

    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.getValue())
            node.setLeft(insert(node.getLeft(), key));
        else if (key > node.getValue())
            node.setRight(insert(node.getRight(), key));
        else
            return node;

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && key < node.getLeft().getValue())
            return rotateRight(node);

        if (balance < -1 && key > node.getRight().getValue())
            return rotateLeft(node);

        if (balance > 1 && key > node.getLeft().getValue()) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balance < -1 && key < node.getRight().getValue()) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    Node remove(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.getValue())
            node.setLeft(remove(node.getLeft(), key));
        else if (key > node.getValue())
            node.setRight(remove(node.getRight(), key));
        else {
            if (node.getLeft() == null || node.getRight() == null) {
                Node temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                node = temp;
            } else {
                Node temp = minValue(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(remove(node.getRight(), temp.getValue()));
            }
        }

        if (node == null)
            return null;

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0)
            return rotateRight(node);

        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0)
            return rotateLeft(node);

        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    Node minValue(Node node) {
        Node current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null || node.getValue() == value)
            return node;

        if (value < node.getValue())
            return search(node.getLeft(), value);
        else
            return search(node.getRight(), value);
    }

}
