package sorting;

public final class SortingUtils {

    private SortingUtils() {
        throw new AssertionError("No instanciar");
    }

    // Imprimir barras horizontales
    public static void printBars(int[] a) {
        if (a == null) {
            System.out.println("[null]");
            return;
        }

        for (int value : a) {
            System.out.printf("%3d: ", value);
            for (int k = 0; k < value; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    // Imprimir el arreglo en una línea
    public static void printArray(int[] a) {
        if (a == null) {
            System.out.println("[null]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + (i + 1 < a.length ? ", " : ""));
        }
        System.out.println("]");
    }

    /**
     * Crea y devuelve una copia independiente del arreglo.
     * Si se modifica la copia, el arreglo original NO cambia.
     */
    public static int[] copyArray(int[] a) {
        if (a == null) return null;
        return a.clone();  // copia eficiente nativa de Java
    }
}