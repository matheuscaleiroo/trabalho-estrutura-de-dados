package projetmusica;


public class Palavra {
    String palavra;
    private int ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = 1;
    }

    public void incrementaOcorrencias() {
        this.ocorrencias++;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    @Override
    public String toString() {
        return palavra + " (" + ocorrencias + ")";
    }
}

