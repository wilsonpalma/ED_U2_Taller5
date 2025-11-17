package sorting;

/**
 * SortingDemo
 *
 * Ejecuta InsertionSort, SelectionSort y BubbleSort sobre los datasets
 * definidos en la guía (A..E). Por defecto ejecuta con trazas (trace = true).
 * Para ejecutar sin trazas pasar "notrace" como primer argumento.
 *
 * Ejemplo:
 *   java ed.u2.sorting.SortingDemo         // con trazas
 *   java ed.u2.sorting.SortingDemo notrace // sin trazas
 *
 * Recomendación para evidencias:
 * Redirige la salida a un archivo:
 *   java ed.u2.sorting.SortingDemo > salida.txt
 */
public final class SortingDemo {

    private SortingDemo() { throw new AssertionError("No instanciar"); }

    public static void main(String[] args) {
        boolean trace = true;
        if (args != null && args.length > 0 && "notrace".equalsIgnoreCase(args[0])) {
            trace = false;
        }

        // Datasets de la guía
        int[][] datasets = {
                {8, 3, 6, 3, 9},   // A
                {5, 4, 3, 2, 1},   // B (inverso)
                {1, 2, 3, 4, 5},   // C (ya ordenado)
                {2, 2, 2, 2},      // D (duplicados)
                {9, 1, 8, 2}       // E
        };

        String[] names = {"A", "B", "C", "D", "E"};

        System.out.println("========================================");
        System.out.println("SortingDemo - Ejecutando algoritmos");
        System.out.println("Trazas: " + (trace ? "ACTIVADAS" : "DESACTIVADAS"));
        System.out.println("========================================\n");

        // Por cada dataset, ejecutar los tres algoritmos sobre copias independientes
        for (int i = 0; i < datasets.length; i++) {
            int[] original = datasets[i];
            String dsName = names[i];

            System.out.printf("=== Dataset %s: %s ===%n", dsName, arrayToString(original));
            System.out.println();

            // InsertionSort
            int[] arrInsertion = SortingUtils.copyArray(original);
            System.out.printf(">>> InsertionSort sobre dataset %s%n", dsName);
            if (trace) {
                System.out.print("Arreglo inicial: ");
                SortingUtils.printArray(arrInsertion);
            }
            InsertionSort.sort(arrInsertion, trace);
            if (!trace) {
                System.out.print("Resultado InsertionSort: ");
                SortingUtils.printArray(arrInsertion);
            }
            System.out.println();

            // SelectionSort
            int[] arrSelection = SortingUtils.copyArray(original);
            System.out.printf(">>> SelectionSort sobre dataset %s%n", dsName);
            if (trace) {
                System.out.print("Arreglo inicial: ");
                SortingUtils.printArray(arrSelection);
            }
            SelectionSort.sort(arrSelection, trace);
            if (!trace) {
                System.out.print("Resultado SelectionSort: ");
                SortingUtils.printArray(arrSelection);
            }
            System.out.println();

            // BubbleSort
            int[] arrBubble = SortingUtils.copyArray(original);
            System.out.printf(">>> BubbleSort sobre dataset %s%n", dsName);
            if (trace) {
                System.out.print("Arreglo inicial: ");
                SortingUtils.printArray(arrBubble);
            }
            BubbleSort.sort(arrBubble, trace);
            if (!trace) {
                System.out.print("Resultado BubbleSort: ");
                SortingUtils.printArray(arrBubble);
            }
            System.out.println("========================================\n");
        }

        System.out.println("SortingDemo: ejecución finalizada.");
    }

    // Pequeña utilidad local para mostrar el arreglo en el encabezado del dataset
    private static String arrayToString(int[] a) {
        if (a == null) return "[null]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i + 1 < a.length) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}