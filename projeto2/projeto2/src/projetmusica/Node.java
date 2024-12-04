package projetmusica;

public class Node {

    Palavra elemento;
    Node left, right, parent;

    Node(Palavra elemento) {
        this.elemento = elemento;
        left = right = parent = null;
    }

    public void mostraNo() {
        System.out.print(elemento + " ");
    }
}
