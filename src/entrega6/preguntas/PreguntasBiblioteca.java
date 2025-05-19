package entrega6.preguntas;

import us.lsi.biblioteca.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PreguntasBiblioteca {
	
	public static void main(String[] args) {
		PreguntasBiblioteca pb = new PreguntasBiblioteca();
		
		System.out.println(pb.masVecesPrestadoImperativa());
		System.out.println(pb.masVecesPrestadoFuncional());
		
		List<String> autores = List.of("Lorenzo Costa Ocaña", "Victoria Agustín Zurita","Mirta del Ferrero"); 
		System.out.println(pb.librosPorAutorImperativo(Libros.of(), autores));
		System.out.println(pb.librosPorAutorFuncional(Libros.of(), autores));
	}
	
	public Libro masVecesPrestadoImperativa() {
		Libro libroMasPrestado = null;
		Integer maxPrestamos = Integer.MIN_VALUE;
		
		for (Libro l : Libros.of().todos()) {
			Integer numPrestamos = 0;
			
			for (Prestamo p : Prestamos.of().todos()) {
				if (l.isbn().equals(p.isbn())){
					numPrestamos++;
				}
			}	
			
			if (numPrestamos > maxPrestamos) {
				maxPrestamos = numPrestamos;
				libroMasPrestado = l;
			}
		}
		return libroMasPrestado;
	}

	public Libro masVecesPrestadoFuncional() {
	    return Libros.of().todos().stream()
	        .collect(Collectors.toMap(
	            l -> l,
	            l -> (int) Prestamos.of().todos().stream()
	                .filter(p -> p.isbn().equals(l.isbn()))
	                .count()
	        ))
	        .entrySet().stream()
	        .max(Comparator.comparingInt(Map.Entry::getValue)) // Busca el libro con más préstamos
	        .map(Map.Entry::getKey)
	        .orElse(null);
	}
	
	public Map<String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres){
		List<String> autores = new ArrayList<>();
		Map<String, Set<String>> librosPorAutor = new HashMap<>();
		
		for (Libro l : libros.todos()) {
			if ( (!autores.contains(l.autor())) && (nombres == null || nombres.contains(l.autor())) ) {
				autores.add(l.autor());
			}
		}
		
		for (String au : autores) {
			Set<String> librosDeAutor = new HashSet<>();
			
			for (Libro l : libros.todos())  {
				if (au.equals(l.autor())) {
					librosDeAutor.add(l.titulo());
				}
			}
			
			if (!librosDeAutor.isEmpty()) {
	            librosPorAutor.put(au, librosDeAutor);
	        }
		}
		
		return librosPorAutor;
	}
	
	public Map<String, Set<String>> librosPorAutorFuncional(Libros libros, List<String> nombres) {
	    return libros.todos().stream()
	        .filter(l -> nombres == null || nombres.contains(l.autor())) // Filtra autores según la lista nombres
	        .collect(Collectors.groupingBy(
	            Libro::autor,
	            Collectors.mapping(Libro::titulo, Collectors.toSet()) // Agrupa títulos por autor
	        ));
	}
}