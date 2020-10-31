package clases;

import java.util.Scanner;

public class Main {

    private static Buffer buffer;
    private static Scanner scanner;
    private static ThreadPool threadPool;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        long time = System.currentTimeMillis();

        System.out.print("Indique la cantidad de Threads a utilizar: ");
	    String threads = scanner.nextLine();

	    System.out.print("Indique la dificultad a utilizar: ");
        String dificultad = scanner.nextLine();

        String texto = "";
        System.out.print("Indique un texto: ");
        texto = scanner.nextLine();


        int cantWorkers = Integer.parseInt(threads);
        buffer = new Buffer(cantWorkers);
        threadPool = new ThreadPool(cantWorkers, buffer);


    }
}
