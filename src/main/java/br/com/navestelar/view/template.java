package br.com.navestelar.view;

import java.util.Scanner;

/**
 * A classe {@code template} fornece métodos utilitários para interações básicas com o usuário através do console.
 *
 * <p>Esta classe inclui métodos para ler entradas do usuário, exibir mensagens formatadas e gerenciar o layout da saída no console.</p>
 */
public class template {

    public static int TAM = 60;
    static Scanner read = new Scanner(System.in);

    /**
     * Lê uma linha de entrada do usuário como uma {@code String}.
     *
     * @param message A mensagem a ser exibida antes da entrada do usuário.
     * @return O texto inserido pelo usuário.
     */
    public static String readString(String message) {
        System.out.print(message + ": ");
        String input = read.nextLine();
        return input;
    }

    /**
     * Lê uma entrada do usuário como um {@code int}.
     *
     * @param message A mensagem a ser exibida antes da entrada do usuário.
     * @return O valor inteiro inserido pelo usuário.
     */
    public static int readInt(String message) {
        System.out.print(message + ": ");
        int input = read.nextInt();
        read.nextLine(); // Consome o restante da linha
        return input;
    }

    /**
     * Lê uma entrada do usuário como um {@code double}.
     *
     * @param message A mensagem a ser exibida antes da entrada do usuário.
     * @return O valor {@code double} inserido pelo usuário.
     */
    public static double readDouble(String message) {
        System.out.print(message + ": ");
        Double input = read.nextDouble();
        return input;
    }

    /**
     * Exibe uma linha horizontal no console.
     *
     * <p>A linha tem um comprimento fixo definido pela constante {@code TAM}.</p>
     */
    public static void Line() {
        StringBuilder result = new StringBuilder();
        result.append("+");
        result.append("-".repeat(TAM));
        result.append("+");
        System.out.println(result.toString());
    }

    /**
     * Exibe uma mensagem pedindo ao usuário para pressionar Enter para continuar.
     */
    public static void pressToContinue() {
        System.out.println(" ");
        System.out.println("Pressione enter para continuar...");
    }

    /**
     * Exibe uma palavra centralizada no console com uma borda de tamanho fixo definida pela constante {@code TAM}.
     *
     * @param palavra A palavra a ser centralizada e exibida.
     */
    public static void centralizarPalavra(String palavra) {
        int tamanhoPalavra = palavra.length();
        int espacosEsquerda = (TAM - tamanhoPalavra) / 2;
        int espacosDireita = TAM - tamanhoPalavra - espacosEsquerda;

        StringBuilder resultado = new StringBuilder();
        resultado.append("|");
        resultado.append(" ".repeat(espacosEsquerda));
        resultado.append(palavra);
        resultado.append(" ".repeat(espacosDireita));
        resultado.append("|");

        System.out.println(resultado.toString());
    }

    /**
     * Exibe uma palavra alinhada à esquerda no console com uma borda de tamanho fixo definida pela constante {@code TAM}.
     *
     * @param word A palavra a ser alinhada à esquerda e exibida.
     */
    public static void alignLeft(String word) {
        int lengthOfString = word.length();
        StringBuilder result = new StringBuilder();

        int spacesToAdd = Math.max(0, TAM - lengthOfString);

        result.append("| ").append(word);
        result.append(" ".repeat(spacesToAdd - 1));
        result.append("|");

        System.out.println(result.toString());
    }

    /**
     * Exibe um título centralizado com linhas acima e abaixo dele.
     *
     * @param title O título a ser exibido.
     */
    public static void title(String title) {
        template.clear();
        template.Line();
        centralizarPalavra(title);
        template.Line();
    }

    /**
     * Limpa a tela do console com uma linha de espaços em branco.
     */
    public static void clear() {
        System.out.println(
                "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ");
    }

    /**
     * Exibe um prompt para o usuário selecionar uma opção desejada.
     */
    public static void input() {
        System.out.print("| Selecione a opção desejada: ");
    }
}
