package sorting;

import java.util.Arrays;

/**
 * InsertionSort con trazas por iteración.
 * - sort(int[] a)           : ordena sin trazas.
 * - sort(int[] a, boolean)  : ordena e imprime trazas por iteración si trace == true.
 *
 * Trazas (cuando trace==true):
 *  - Para cada desplazamiento: "Shift: pos X -> pos Y | valor: V"
 *  - Al insertar la clave:     "Insert: valor K en posición P"
 *  - Después de la iteración:  barras horizontales del arreglo (SortingUtils.printBars)
 *  - Al final: suma total de comparaciones y shifts.
 */
public final class InsertionSort {

    private InsertionSort() { throw new AssertionError("No instanciar"); }

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
                System.out.println("Arreglo de tamaño " + n + " — nada que ordenar: " + Arrays.toString(a));
            }
            return;
        }

        long shifts = 0;        // desplazamientos (assign a[j+1] = a[j])
        long comparisons = 0;   // veces que se evalúa a[j] > key

        if (trace) {
            System.out.println("== Inicio InsertionSort ==");
            System.out.println("Arreglo inicial: " + Arrays.toString(a));
            System.out.println("--------------------------------");
        }

        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            int shiftsThisIter = 0;

            if (trace) {
                System.out.println("Iteración " + i + " — insertando " + key);
            }

            // Mover elementos mayores que key una posición a la derecha
            while (j >= 0) {
                comparisons++; // contamos la comparación a[j] > key
                if (a[j] > key) {
                    if (trace) {
                        System.out.printf("Shift: pos %d -> pos %d | valor: %d%n", j, j + 1, a[j]);
                    }
                    a[j + 1] = a[j];
                    shifts++;
                    shiftsThisIter++;
                    j--;
                } else {
                    // cuando a[j] <= key, dejamos de desplazar
                    break;
                }
            }

            // Aquí se coloca la key en su posición correcta
            a[j + 1] = key;
            if (trace) {
                System.out.printf("Insert: valor %d en posición %d%n", key, j + 1);
                // Mostrar barras horizontales para visualizar el arreglo
                SortingUtils.printBars(a);
            }
        }

        if (trace) {
            System.out.println("== Fin InsertionSort ==");
            System.out.println("Comparaciones totales: " + comparisons);
            System.out.println("Shifts totales: " + shifts);
            System.out.println("--------------------------------");
        }
    }
}
