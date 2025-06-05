public class Node {
    int key;
    Node left;
    Node right;
    int height;


    public Node(int value) {
        this.key = value;
        this.height = 0;
    }

    public int getValue() {
        return key;
    }

    public void setValue(int value) {
        this.key = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}



