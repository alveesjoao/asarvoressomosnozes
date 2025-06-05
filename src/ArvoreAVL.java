public class ArvoreAVL {
    Node no;
    public ArvoreAVL() {
        no = null;
    }

    int height(Node no) {
        int leftHeight = height(no.left);
        int rightHeight = height(no.right);
        return Math.max(leftHeight, rightHeight);
    }

    int fatorBalanceamento(Node no) {
        if (no == null)
            return 0;
        return height(no.left) - height(no.right);
    }

    Node rotacaoDireita(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        //Realizar rotacao
        x.right = y;
        y.left = T2;

        //Atualizar ALturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node rotacaoEsquerda(Node x) {
        Node y = x.right;
        Node aux = y.left;

        y.left = x;
        x.right = aux;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node no, int key) {
        if (no == null)
            return new Node(key);

        if (key < no.key)
            no.left = insert(no.left, key);
        else if (key > no.key)
            no.right = insert(no.right, key);
        else
            return no;

        no.height = 1 + Math.max(height(no.left), height(no.right));
        int balanceamento = fatorBalanceamento(no);

        if (balanceamento > 1 && key < no.left.key)
            return rotacaoDireita(no);
    }
}
