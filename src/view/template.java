package src.view;

import java.util.Scanner;

public class template {

    public static int TAM = 60;
    static Scanner read = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message + ": ");
        String input = read.nextLine();
        return input;
    }

    public static int readInt(String message) {
        System.out.print(message + ": ");
        int input = read.nextInt();
        read.nextLine();
        return input;
    }

    public static double readDouble(String message) {
        System.out.print(message + ": ");
        Double input = read.nextDouble();
        return input;
    }

    public static void Line() {

        StringBuilder result = new StringBuilder();
        result.append("+");
        result.append("-".repeat(TAM));
        result.append("+");
        System.out.println(result.toString());
    }

    public static void pressToContinue() {
        System.out.println(" ");
        System.out.println("Pressione enter para continuar...");
    }

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

    public static void alignLeft(String word) {

        int lengthOfString = word.length();
        StringBuilder result = new StringBuilder();

        int spacesToAdd = Math.max(0, TAM - lengthOfString);

        result.append("| ").append(word);
        result.append(" ".repeat(spacesToAdd - 1));
        result.append("|");

        System.out.println(result.toString());
    }

    public static void title(String title) {
        template.clear();
        template.Line();
        centralizarPalavra(title);
        template.Line();
    }

    public static void clear() {
        System.out.println(
                "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ");
    }

    public static void input() {

        System.out.print("| Selecione a opção desejada: ");
    }
}
