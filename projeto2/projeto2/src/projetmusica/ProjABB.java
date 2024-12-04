package projetmusica;

import java.io.*;
import java.util.*;

public class ProjABB {

    private static ABB arvore = new ABB();
    private static int totalLinhas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    carregarTexto(scanner);
                    break;
                case 2:
                    contadorDePalavras();
                    break;
                case 3:
                    buscaPorPalavra(scanner);
                    break;
                case 4:
                    exibirTexto();
                    break;
                case 5:
                    exibirTotalLinhas();
                    break;
                case 6:
                    rankLetras();
                    break;
                case 7:
                    System.out.println("Encerrando o programa...\nDesenvolvido por Leonardo De Castro Tonon e Matheus Caleiro Pinheiro");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1- Carregar o texto");
        System.out.println("2- Contador de palavras");
        System.out.println("3- Busca por palavra");
        System.out.println("4- Exibição do texto");
        System.out.println("5- Total de Linhas");
        System.out.println("6- Rank de Letras");
        System.out.println("7- Encerrar");
        System.out.print("Escolha uma opção: ");
    }

    public static void carregarTexto(Scanner scanner) {
        System.out.print("Digite o nome do arquivo .txt (com extensão): ");
        String caminho = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            totalLinhas = 0;
            while ((linha = br.readLine()) != null) {
                totalLinhas++;
                String[] palavras = linha.split("\\s+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        Palavra novaPalavra = new Palavra(palavra);
                        arvore.insere(novaPalavra);
                    }
                }
            }
            System.out.println("TEXTO CARREGADO COM SUCESSO!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    public static void contadorDePalavras() {
        if (arvore.isEmpty()) {
            System.out.println("A árvore está vazia. Carregue o texto primeiro.");
            return;
        }
        int contador = contarPalavras(arvore.root());
        System.out.println("Número total de palavras: " + contador);
    }

    private static int contarPalavras(Node no) {
        if (no == null) return 0;
        return no.elemento.getOcorrencias() + contarPalavras(no.left) + contarPalavras(no.right);
    }

    public static void buscaPorPalavra(Scanner scanner) {
        if (arvore.isEmpty()) {
            System.out.println("A árvore está vazia. Carregue o texto primeiro.");
            return;
        }
        System.out.print("Digite a palavra que deseja buscar: ");
        String palavraBuscada = scanner.nextLine();
        Node resultado = arvore.busca(new Palavra(palavraBuscada));
        if (resultado == null) {
            System.out.println("Palavra não encontrada na árvore.");
        } else {
            System.out.println("Palavra encontrada: " + resultado.elemento);
        }
    }

    public static void exibirTexto() {
        if (arvore.isEmpty()) {
            System.out.println("A árvore está vazia. Carregue o texto primeiro.");
            return;
        }
        System.out.println("Texto em ordem alfabética:");
        arvore.executaInOrdemComQuebraDeLinha(arvore.root());
    }

    public static void exibirTotalLinhas() {
        if (totalLinhas == 0) {
            System.out.println("Nenhum texto foi carregado. Total de linhas: 0");
        } else {
            System.out.println("Total de linhas no texto: " + totalLinhas);
        }
    }

    public static void rankLetras() {
        if (arvore.isEmpty()) {
            System.out.println("A árvore está vazia. Carregue o texto primeiro.");
            return;
        }

        // Map para armazenar a frequência de cada letra
        Map<Character, Integer> frequencias = new HashMap<>();

        // Percorrer a árvore e calcular a frequência das letras
        calcularFrequencias(arvore.root(), frequencias);

        // Ordenar as letras por frequência
        List<Map.Entry<Character, Integer>> ranking = new ArrayList<>(frequencias.entrySet());
        ranking.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nRanking das Letras:");
        for (Map.Entry<Character, Integer> entry : ranking) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void calcularFrequencias(Node no, Map<Character, Integer> frequencias) {
        if (no == null) return;

        // Processar cada letra da palavra
        String palavra = no.elemento.palavra;
        for (char letra : palavra.toCharArray()) {
            frequencias.put(letra, frequencias.getOrDefault(letra, 0) + no.elemento.getOcorrencias());
        }

        // Processar os filhos
        calcularFrequencias(no.left, frequencias);
        calcularFrequencias(no.right, frequencias);
    }
}
