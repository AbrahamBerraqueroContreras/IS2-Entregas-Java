package entrega6.test;

import java.util.List;

import entrega6.preguntas.PreguntasBiblioteca;
import us.lsi.biblioteca.Libros;

public class TestPreguntasBiblioteca {
    public static void main(String[] args) {
        PreguntasBiblioteca pb = new PreguntasBiblioteca();

        System.out.println("== Test: Libro más veces prestado ==");
        System.out.println("Buscando el libro con mayor número de préstamos...");
        System.out.println("Imperativo: " + pb.masVecesPrestadoImperativa());
        System.out.println("Funcional: " + pb.masVecesPrestadoFuncional());

        System.out.println("\n== Test: Libros por autor ==");
        List<String> autores = List.of(
            "Lorenzo Costa Ocaña",
            "Victoria Agustín Zurita",
            "Mirta del Ferrero"
        );
        System.out.println("Lista de autores proporcionada: " + autores);
        System.out.println("Imperativo: " + pb.librosPorAutorImperativo(Libros.of(), autores));
        System.out.println("Funcional: " + pb.librosPorAutorFuncional(Libros.of(), autores));
    }
}
