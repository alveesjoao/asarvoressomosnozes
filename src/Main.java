public class Main {
    public static void main(String[] args) {

        BinaryTree tree = BinaryTree.getExampleTree();
        int quantity = tree.nodeCount();
        // contar elementos
        System.out.println(quantity);
        //Em ordem
        tree.printValuesInOrder();
        // pre order
        tree.printValuesPreOrder();
        // post order
        tree.printValuesPostOrder();
        // por nivel
        tree.printValuesByLevel();

        //recursivo
        System.out.println(tree.countLeafsNode(tree.root));

        //iterativo
        tree.countLeafsIterative();

    }
}