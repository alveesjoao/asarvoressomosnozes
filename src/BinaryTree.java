import java.util.*;

public class BinaryTree {
    Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public static BinaryTree getExampleTree() {
        Node root = new Node(6);

        // lvl1
        root.setLeft(new Node(4));
        root.setRight(new Node(7));

        // lvl 2
        root.getLeft().setLeft(new Node(2));
        root.getLeft().setRight(new Node(5));
        root.getRight().setRight(new Node(9));

        // lvl 3
        root.getRight().getRight().setRight(new Node(11));

        return new BinaryTree(root);
    }

    public int nodeCount() {
        return inOrder().size();
    }

    public List<Node> inOrder() {
        List<Node> values = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                current = stack.pop();
                values.add(current);
                current = current.getRight();
            }
        }
        return values;
    }

    public List<Node> preOrder() {
        List<Node> values = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            values.add(node);

            if (node.getRight() != null) stack.push(node.getRight());
            if (node.getLeft() != null) stack.push(node.getLeft());
        }
        return values;
    }

    public List<Node> postOrder() {
        List<Node> values = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                Node peek = stack.peek();
                if(peek.getRight() != null && lastVisited != peek.getRight()) {
                    current = peek.getRight();
                } else {
                    values.add(peek);
                    lastVisited = stack.pop();
                }

            }
        }
        return values;
    }

    public List<Node> byLevel() {
        List<Node> values = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            values.add(current);

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        return values;
    }

    public void printValuesInOrder() {
        List<Node> values = inOrder();
        System.out.print("Printing values in order: [");
        for (Node node : values) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println("]");
    }

    public void printValuesPreOrder() {
        List<Node> values = preOrder();
        System.out.print("Printing values pre order: [");
        for (Node node : values) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println("]");
    }

    public void printValuesPostOrder() {
        List<Node> values = postOrder();
        System.out.print("Printing values post order: [");
        for (Node node : values) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println("]");
    }

    public void printValuesByLevel () {
        List<Node> values = byLevel();
        System.out.print("Printing values by level: [");
        for (Node node : values) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println("]");
    }

    // recursivo
    public int countLeafsNode(Node node) {
        if(node == null) return 0;
        if(node.getLeft() == null && node.getRight() == null) return 1;
        return countLeafsNode(node.getLeft()) + countLeafsNode(node.getRight());


    }

    //iterativo
    public int countLeafsIterative() {
        if(root == null) return 0;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        int count = 0;
        while(!stack.isEmpty()) {
            Node current = stack.pop();
            if(current.getLeft() == null && current.getRight() == null) count++;
        }
        return count;
    }
}