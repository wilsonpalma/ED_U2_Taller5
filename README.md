# KaprekarTest

Pequeño programa en **Java** que prueba la constante de Kaprekar (6174) usando **10** números aleatorios válidos de 4 dígitos. Cada semilla se itera hasta un máximo de **500** iteraciones (para evitar bucles infinitos).  
Incluye un InsertionSort con la firma requerida: `public static void sort(int[] a, boolean trace)` (se llama con `trace = false` por defecto).

## Descripción
Por cada semilla válida (4 dígitos y no todos iguales) el programa:
1. Descompone el número en 4 dígitos (con ceros a la izquierda).
2. Ordena los dígitos ascendentemente (usando `sort(a, false)`).
3. Construye el número descendente invirtiendo el arreglo y resta: `desc - asc`.
4. Repite hasta alcanzar `6174` o agotar `MAX_ITER`.

## Requisitos
- Java 8+ (JDK instalado)

## Archivos
- KaprekarTest.java — implementación principal.

## Compilar y ejecutar
```
javac KaprekarTest.java
java KaprekarTest
```

## Parámetros / Constantes (en el código)
- `KAPREKAR = 6174`
- `MAX_ITER = 500` — límite de iteraciones por semilla.
- `SEEDS_TO_TEST = 10` — número de semillas aleatorias a probar.
- Firma de ordenación usada: `public static void sort(int[] a, boolean trace)` (in-place).

## Salida esperada

```
+-----------+----------------------+
| Semilla   | Iteraciones a 6174   |
+-----------+----------------------+
|     3524  |                   3  |
|     3087  |                   5  |
|    9990   |           No alcanzó |
... 
+-----------+----------------------+
```

## Notas
- El método `sort(int[] a, boolean trace)` es in-place: **no** devuelve el array porque la modificación por referencia es suficiente y más eficiente.
- Se descartan semillas con todos los dígitos iguales (ej. 1111) porque producen 0000 y no permiten iniciar el ciclo correcto.