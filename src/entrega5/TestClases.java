package entrega5;

import java.util.Arrays;

public class TestClases {
    public static void main(String[] args) {
    	System.out.println("===== INICIANDO PRUEBAS DE ESTRUCTURAS LINEALES =====\n");

        // ----- Prueba de ListaOrdenada -----
        System.out.println("----- Prueba de ListaOrdenada -----");
        ListaOrdenada<Integer> listaOrdenada = ListaOrdenada.of(Integer::compareTo);
        listaOrdenada.addAll(Arrays.asList(5, 2, 8, 1, 3));
        System.out.println("Elementos en la lista: " + listaOrdenada.elements());
        System.out.println("Tamaño de la lista: " + listaOrdenada.size());

        listaOrdenada.remove();
        System.out.println("Elementos después de eliminar: " + listaOrdenada.elements());

        listaOrdenada.addAll(Arrays.asList(4, 6, 7));
        System.out.println("Elementos después de añadir lote: " + listaOrdenada.elements());

        System.out.println("Eliminando todos los elementos: " + listaOrdenada.removeAll());
        System.out.println("¿Está vacía? " + listaOrdenada.isEmpty() + "\n");

        // ----- Prueba de ListaOrdenadaSinRepeticion -----
        System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");
        ListaOrdenadaSinRepeticion<Integer> listaSinRepeticion = ListaOrdenadaSinRepeticion.of(Integer::compareTo);
        listaSinRepeticion.addAll(Arrays.asList(5, 2, 8, 1, 3, 5, 2));
        System.out.println("Elementos en la lista: " + listaSinRepeticion.elements());
        System.out.println("Tamaño de la lista: " + listaSinRepeticion.size());

        listaSinRepeticion.addAll(Arrays.asList(4, 6, 7, 4));
        System.out.println("Elementos después de añadir lote: " + listaSinRepeticion.elements());
        System.out.println("¿Está vacía? " + listaSinRepeticion.isEmpty() + "\n");

        // ----- Prueba de Cola (FIFO) -----
        System.out.println("----- Prueba de Cola (FIFO) -----");
        Cola<String> cola = Cola.of();
        cola.addAll(Arrays.asList("primero", "segundo", "tercero"));
        System.out.println("Elementos en la cola: " + cola.elements());
        System.out.println("Tamaño de la cola: " + cola.size());

        System.out.println("Desencolando elementos:");
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elements());
        }
        System.out.println("¿Está vacía? " + cola.isEmpty() + "\n");

        // ----- Prueba de Pila (LIFO) -----
        System.out.println("----- Prueba de Pila (LIFO) -----");
        Pila<Double> pila = new Pila<>();
        pila.addAll(Arrays.asList(1.1, 2.2, 3.3));
        System.out.println("Elementos en la pila: " + pila.elements());
        System.out.println("Tamaño de la pila: " + pila.size());
        System.out.println("Elemento en el tope: " + pila.top());

        System.out.println("Desapilando elementos:");
        while (!pila.isEmpty()) {
            System.out.println("Desapilado: " + pila.remove());
            System.out.println("Pila restante: " + pila.elements());
        }
        System.out.println("¿Está vacía? " + pila.isEmpty() + "\n");

        // ----- Prueba de ColaPrioridad -----
        System.out.println("----- Prueba de ColaPrioridad -----");
        ColaPrioridad<String, Integer> colaPrioridad = ColaPrioridad.ofPriority();
        colaPrioridad.add("Crítico", 1);
        colaPrioridad.add("Normal", 3);
        colaPrioridad.add("Urgente", 2);
        colaPrioridad.add("Bajo", 4);

        System.out.println("Elementos en la cola por prioridad: " + colaPrioridad.valuesAsList());
        System.out.println("Elementos con sus prioridades: " + colaPrioridad.elements());

        colaPrioridad.decreasePriority("Normal", 1);
        System.out.println("Elementos con prioridad actualizada: " + colaPrioridad.valuesAsList());

        System.out.println("Desencolando elementos por prioridad:");
        while (!colaPrioridad.isEmpty()) {
            System.out.println("Desencolado: " + colaPrioridad.removeValue());
            System.out.println("Cola restante: " + colaPrioridad.valuesAsList());
        }
        System.out.println("¿Está vacía? " + colaPrioridad.isEmpty());

        colaPrioridad.addAllValues(Arrays.asList("Tarea A", "Tarea B", "Tarea C"), 2);
        System.out.println("Elementos añadidos en lote con prioridad 2: " + colaPrioridad.valuesAsList());

        colaPrioridad.add("Tarea Urgente", 1);
        System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + colaPrioridad.valuesAsList());

        System.out.println("\n===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }
}