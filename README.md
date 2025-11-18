# Taller 5: Ordenación básica en Java

## Decisiones de implementación
- Los algoritmos son in-place y siguen las firmas requeridas: `sort(int[] a)` y `sort(int[] a, boolean trace)`.
- Las trazas visuales se imprimen **por iteración externa** (variable `i`) y usan barras horizontales para visualizar el estado del arreglo.
- Símbolos y trazas:
  - Insertion: `Shift: pos X -> pos Y | valor: V` y `Insert: valor K en posición P`.
  - Selection: `Swap: pos X <-> pos Y | valores: A <-> B` (se añade `(sin cambio real)` cuando aplica).
  - Bubble: `Swap: pos X <-> pos Y | valores: A <-> B` por intercambio; si no hubo swaps en una pasada se imprime `No hubo swaps, corte temprano`.
- `SortingUtils` contiene utilidades: `printBars`, `printArray`, `copyArray`.
- No se imprimen comparaciones por cada evaluación para evitar ruido; solo los eventos relevantes (shifts/swaps/insert).
- Las trazas se activan con el modo por defecto; pasar el argumento `notrace` al ejecutar desactiva las trazas.

## Casos borde probados
- Arreglo vacío: `[]`.
- Arreglo de tamaño 1: `[x]`.
- Arreglo ya ordenado: `[1,2,3,4,5]`.
- Arreglo inverso: `[5,4,3,2,1]`.
- Arreglo con duplicados: `[2,2,2,2]`.

## Cómo compilar y ejecutar (desde la raíz del proyecto)
- Compilar:
```bash
javac -d out src/ed/u2/sorting/*.java
```
- Ejecutar (con trazas, por defecto):
```
java -cp out sorting.SortingDemo
```
- Ejecutar sin trazas:
```
java -cp out sorting.SortingDemo notrace
```
- Generar evidencias redirigiendo la salida a archivo:
```
java -cp out sorting.SortingDemo > evidencias_trazas.txt
```

## Notas finales

- Si los valores del dataset son muy grandes, las barras pueden quedar excesivamente largas; se recomienda **escalar** la longitud de la barra (por ejemplo usando `value / scale`) y elegir `scale` de modo que la barra máxima tenga ~40–60 símbolos para mantener la legibilidad.  
- Para generar evidencias reproducibles, redirige la salida completa a un archivo:  
  `java -cp out ed.u2.sorting.SortingDemo > evidencias_trazas.txt`
- Para ejecutar sin trazas (modo de verificación rápida) usar:  
  `java -cp out ed.u2.sorting.SortingDemo notrace`
- Asegúrate de incluir en las evidencias los comandos exactos usados para generar los archivos (comando `javac` y `java`) para que el corrector pueda reproducir los resultados.
- La `tabla_comparativa.md` debe contener, para cada dataset A..E, los recuentos observados (shifts en Insertion, swaps en Selection y Bubble, comparaciones y si hubo corte temprano).

## Preguntas de control

**¿Por qué `Insertion` es preferible con datos casi ordenados?**  
Porque su coste depende de cuántos desplazamientos (shifts) son necesarios. En el mejor caso (arreglo ya ordenado) `Insertion` hace sólo ~n−1 comparaciones y 0 desplazamientos, es decir **O(n)**. Para arrays “casi ordenados” los elementos se mueven pocas posiciones, por eso realiza pocas operaciones y es muy eficiente en la práctica para ese tipo de entrada.

**¿Qué propiedad hace que `Selection` use pocos swaps? ¿Qué compromisos tiene?**  
`Selection` busca el mínimo en la porción no ordenada y hace **a lo sumo un swap por iteración externa** (≤ n−1 swaps en total). Esa es la razón por la que usa pocos swaps. El compromiso es que **hace muchas comparaciones** al buscar el mínimo (siempre explora la sublista completa cada iteración), y además la implementación estándar **no es estable** (puede cambiar el orden relativo de elementos iguales).

**¿Cómo implementarías el corte temprano en `Bubble` y qué caso reduce significativamente?**  
Implantar un booleano `swapped` por pasada: al inicio de cada pasada lo pones a `false`; cuando se realiza algún swap lo marcas `true`. Si al terminar la pasada `swapped == false`, no hubo intercambios y el arreglo está ordenado → terminar (break). Esto reduce **drásticamente** el coste en el caso de arreglos ya ordenados o casi ordenados (hace sólo 1 pasada en el mejor caso, O(n)).

**¿Cuál(es) de los tres puede(n) ser estable y en qué condiciones?**  
- `Insertion` — **estable** si se implementa usando desplazamientos (shifts) para insertar la clave (no intercambios que permuten iguales).  
- `Bubble` — **estable** en su implementación clásica (swap sólo cuando `a[j] > a[j+1]`, los iguales no se intercambian).  
- `Selection` — **no estable** en la versión estándar porque el swap del mínimo puede mover elementos iguales y alterar su orden relativo. Puede hacerse estable con cambios (p. ej. extraer el mínimo y desplazar en vez de swap), pero eso añade coste.

**Menciona dos casos borde que deben probarse siempre.**  
1. **Arreglo vacío** `[]` — debe manejarse sin excepciones y retornar silenciosamente (o mostrar mensaje corto en trace).  
2. **Arreglo de tamaño 1** `[x]` — debe tratarse como caso trivial (nada que ordenar).
