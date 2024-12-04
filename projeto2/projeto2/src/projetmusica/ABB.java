package projetmusica;


public class ABB {
    private Node root;

    ABB() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node root() {
        return root;
    }

    public void insere(Palavra palavra) {
        Node novoNo = new Node(palavra);
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            int cmp = palavra.palavra.compareTo(x.elemento.palavra);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                x.elemento.incrementaOcorrencias();
                return;
            }
        }

        novoNo.parent = y;
        if (y == null) {
            root = novoNo;
        } else if (palavra.palavra.compareTo(y.elemento.palavra) < 0) {
            y.left = novoNo;
        } else {
            y.right = novoNo;
        }
    }

    public void executaInOrdemComQuebraDeLinha(Node no) {
        if (no == null) return;
        executaInOrdemComQuebraDeLinha(no.left);
        System.out.println(no.elemento); // Exibe cada palavra em uma linha
        executaInOrdemComQuebraDeLinha(no.right);
    }

    public Node busca(Palavra palavra) {
        Node atual = root;
        while (atual != null) {
            int cmp = palavra.palavra.compareTo(atual.elemento.palavra);
            if (cmp == 0) {
                return atual;
            } else if (cmp < 0) {
                atual = atual.left;
            } else {
                atual = atual.right;
            }
        }
        return null;
    }
}
