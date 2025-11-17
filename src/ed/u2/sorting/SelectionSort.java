package sorting;

/**
 * SelectionSort con trazas simplificadas.
 *
 * Firma:
 *  - sort(int[] a)              : ordena sin trazas.
 *  - sort(int[] a, boolean trace): ordena e imprime trazas por iteración si trace == true.
 *
 * Trazas (cuando trace==true):
 *  - En cada iteración externa i:
 *      Iteración i
 *      Swap: pos X <-> pos Y | valores: A <-> B
 *      (si X == Y se añade " (sin cambio real)")
 *      [barras horizontales del arreglo]
 *
 * Contadores:
 *  - comparisons: número de comparaciones realizadas buscando el mínimo
 *  - swaps: número de operaciones de intercambio ejecutadas (incluye swaps "sin cambio real")
 */
public final class SelectionSort {

    private SelectionSort() { throw new AssertionError("No instanciar"); }

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

        long comparisons = 0; // para contar comparaciones al buscar el mínimo
        long swaps = 0;       // para contar swaps (incluye swaps sin cambio real)

        if (trace) {
            System.out.println("== Inicio SelectionSort ==");
            System.out.print("Arreglo inicial: ");
            SortingUtils.printArray(a);
            System.out.println("--------------------------------");
        }

        for (int i = 0; i < n - 1; i++) {
            if (trace) {
                System.out.println("Iteración " + i);
            }

            int minIndex = i;
            // Buscar el índice del menor en [i+1 .. n-1]
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // Preparar mensaje de swap (aunque sea con la misma posición)
            int valI = a[i];
            int valMin = a[minIndex];

            if (trace) {
                if (i == minIndex) {
                    System.out.printf("Swap: pos %d <-> pos %d | valores: %d <-> %d (sin cambio real)%n",
                            i, minIndex, valI, valMin);
                } else {
                    System.out.printf("Swap: pos %d <-> pos %d | valores: %d <-> %d%n",
                            i, minIndex, valI, valMin);
                }
            }

            // Realizar el swap
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
            swaps++;

            // Imprimir barra del estado actual
            if (trace) {
                SortingUtils.printBars(a);
            }
        }

        if (trace) {
            System.out.println("== Fin SelectionSort ==");
            System.out.println("Comparaciones totales: " + comparisons);
            System.out.println("Swaps totales: " + swaps);
            System.out.println("--------------------------------");
        }
    }
}
