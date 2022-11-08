package Java;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("Algoritmo de Huffman");
        System.out.println("--------------------------------------------");

        while (true) {
            System.out.println("digite o texto para ser codificado");
            System.out.println("Use Huffman para um texto padrão ou deixe em branco para parar.");
            String text = in.nextLine().trim();

            if (text.isEmpty()) {
                System.out.println("sessao terminada!");
                return;
            }

            if (text.equalsIgnoreCase("Huffman")) {
                text =
                        "Em ciência da computação e teoria da informação, um código Huffman é um tipo particular\n" +
                                 "de código de prefixo ideal que é comumente usado para compactação de dados sem perdas. O\n" +
                                 "processo de encontrar e/ou usar tal código procede por meio da codificação Huffman,\n" +
                                 "um algoritmo desenvolvido por David A. Huffman enquanto ele era um estudante de doutorado no MIT,\n" +
                                 "e publicado no artigo de 1952 \"Um Método para a Construção de\n" +
                                 "Códigos de redundância mínima\".";
            }

            System.out.println();
            Huffman huff = new Huffman();
            String data = huff.encode(text);

            int normalSize = text.length() * 8;
            int compressedSize = data.length();
            double rate = 100.0 - (compressedSize * 100.0 / normalSize);
            System.out.println("tamnaho normal: " + normalSize);
            System.out.println("tamanho compactado: " + compressedSize);
            System.out.printf("foi compactado e esta %.2f%% menor que o original. %n", rate);
            System.out.println();
            System.out.println("dados codificados:");
            System.out.println(data);
            System.out.println();
            System.out.println("texto decodificado:");
            System.out.println(huff.decode(data));
            System.out.println();
            System.out.println();
        }
    }
}
