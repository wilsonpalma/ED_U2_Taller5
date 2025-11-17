package sorting;

/**
 * BubbleSort optimizado con corte temprano.
 *
 * Trazas (cuando trace==true):
 *  - En cada pasada externa i:
 *      Iteración i
 *      (varias líneas Swap: pos X <-> pos Y | valores: A <-> B)
 *      O si no hubo swaps: "No hubo swaps, corte temprano"
 *      Barras del estado del arreglo
 *
 * Contadores:
 *  - comparisons: comparaciones realizadas a[j] > a[j+1]
 *  - swaps: intercambios realizados
 *  - passes: número de pasadas externas (i)
 */
public final class BubbleSort {

    private BubbleSort() { throw new AssertionError("No instanciar"); }

    public static void sort(int[] a) {
        sort(a, false);
    }

    public static void sort(int[] a, boolean trace) {
        if (a == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }

        final int n = a.length;
        if (n <= 1) {
            if (trace) {
                System.out.println("Arreglo de tamaño " + n + " — nada que ordenar.");
            }
            return;
        }

        long comparisons = 0;
        long swaps = 0;
        long passes = 0;

        if (trace) {
            System.out.println("== Inicio BubbleSort ==");
            System.out.print("Arreglo inicial: ");
            SortingUtils.printArray(a);
            System.out.println("--------------------------------");
        }

        // Pasadas externas
        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;

            if (trace) {
                System.out.println("Iteración " + i);
            }

            // Comparación de pares adyacentes
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;

                if (a[j] > a[j + 1]) {
                    // Traza del swap
                    if (trace) {
                        System.out.printf("Swap: pos %d <-> pos %d | valores: %d <-> %d%n",
                                j, j + 1, a[j], a[j + 1]);
                    }

                    // Swap real
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    swaps++;
                    swapped = true;
                }
            }

            if (trace) {
                if (!swapped) {
                    System.out.println("No hubo swaps, corte temprano");
                }
                SortingUtils.printBars(a);
            }

            if (!swapped) break;  // Optimización
        }

        if (trace) {
            System.out.println("== Fin BubbleSort ==");
            System.out.println("Comparaciones totales: " + comparisons);
            System.out.println("Swaps totales: " + swaps);
            System.out.println("Pasadas totales: " + passes);
            System.out.println("--------------------------------");
        }
    }
}
