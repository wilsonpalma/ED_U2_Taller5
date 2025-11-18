package sorting;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sorting.InsertionSort.sort;

public class KaprekarTest {
    // Constante de Kaprekar para 4 dígitos
    private static final int KAPREKAR = 6174;
    private static final int MAX_ITER = 500;
    private static final int SEEDS_TO_TEST = 10;

    public static void main(String[] args) {
        List<Integer> seeds = generateValidSeeds(SEEDS_TO_TEST);
        List<Result> results = new ArrayList<>();

        System.out.println("Prueba de la constante de Kaprekar (6174)");
        System.out.println("Semillas generadas (válidas): " + seeds);
        System.out.println();

        for (int seed : seeds) {
            int iter = runKaprekar(seed, MAX_ITER);
            results.add(new Result(seed, iter));
        }

        // Imprimir resumen en forma de tabla
        System.out.println("+-----------+----------------------+");
        System.out.println("| Semilla   | Iteraciones a 6174   |");
        System.out.println("+-----------+----------------------+");
        for (Result r : results) {
            String itersStr = (r.iterations >= 0) ? String.valueOf(r.iterations) : "No alcanzó";
            System.out.printf("| %8d | %20s |\n", r.seed, itersStr);
        }
        System.out.println("+-----------+----------------------+");
    }

    // Genera n semillas válidas (4 dígitos y no todos iguales)
    private static List<Integer> generateValidSeeds(int n) {
        Random rnd = new Random();
        List<Integer> seeds = new ArrayList<>();
        while (seeds.size() < n) {
            int candidate = rnd.nextInt(9000) + 1000; // 1000..9999
            if (isValidSeed(candidate)) {
                seeds.add(candidate);
            }
        }
        return seeds;
    }

    // Valida que el número sea apto para la prueba (4 dígitos y no todos iguales)
    private static boolean isValidSeed(int num) {
        if (num < 0 || num > 9999) return false;
        String s = String.format("%04d", num);
        // verificar si todos los caracteres son iguales
        char first = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != first) return true; // al menos uno distinto => válido
        }
        return false; // todos iguales => inválido (ej. 1111, 2222)
    }

    // Ejecuta la iteración de Kaprekar hasta MAX_ITER o hasta alcanzar 6174
    // Devuelve el número de iteraciones requeridas, o -1 si no llegó en MAX_ITER
    private static int runKaprekar(int seed, int maxIter) {
        int current = seed;
        for (int iter = 1; iter <= maxIter; iter++) {
            int[] digits = numberToDigits(current); // 4 dígitos con ceros a la izquierda
            // ordenar ascendente usando el algoritmo pedido (trace = false)
            sort(digits, false); // InsertSort implementado abajo

            int asc = digitsToNumber(digits); // número ascendente
            int desc = digitsToNumberDescending(digits); // número descendente
            int diff = desc - asc;

            if (diff == KAPREKAR) {
                return iter;
            }

            // si la diferencia no cambia (ejemplo 0000), evitar bucle (aunque validamos)
            if (diff == current) {
                return -1;
            }

            current = diff;
        }
        return -1; // no alcanzó en maxIter
    }

    // Convierte número (0..9999) en array de 4 dígitos [d0,d1,d2,d3] (orden natural izquierda->derecha)
    private static int[] numberToDigits(int num) {
        String s = String.format("%04d", num);
        int[] d = new int[4];
        for (int i = 0; i < 4; i++) {
            d[i] = s.charAt(i) - '0';
        }
        return d;
    }

    // Convierte array de dígitos ordenados ascendentemente a número (ej: [0,1,2,3] -> 123)
    private static int digitsToNumber(int[] digitsAsc) {
        int num = 0;
        for (int i = 0; i < digitsAsc.length; i++) {
            num = num * 10 + digitsAsc[i];
        }
        return num;
    }

    // Convierte array asc a número descendente (ej: [0,1,2,3] -> 3210)
    private static int digitsToNumberDescending(int[] digitsAsc) {
        int num = 0;
        for (int i = digitsAsc.length - 1; i >= 0; i--) {
            num = num * 10 + digitsAsc[i];
        }
        return num;
    }

    // Estructura simple para resultados
    private static class Result {
        int seed;
        int iterations; // -1 si no alcanzó

        Result(int seed, int iterations) {
            this.seed = seed;
            this.iterations = iterations;
        }
    }
}