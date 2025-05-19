package entrega6.preguntas;

import us.lsi.centro;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

public class PreguntasCentro {
	
	private Centro c = Centro.of();
	
	public static void main(String[] args) {
		PreguntasCentro pc = new PreguntasCentro();
		
		System.out.println(pc.promedioEdadProfesoresImperativo("72074089R"));
		System.out.println(pc.promedioEdadProfesoresFuncional("72074089R"));
		
		System.out.println(pc.grupoMayorDiversidadEdadImperativo());
		System.out.println(pc.grupoMayorDiversidadEdadFuncional());
		
		System.out.println(pc.alumnoMasMatriculasImperativo());
		System.out.println(pc.alumnoMasMatriculasFuncional());
		
		System.out.println(pc.rangosEdadPorAlumnoImperativo("20-23,24-26,26-28"));
		System.out.println(pc.rangosEdadPorAlumnoFuncional("20-23,24-26,26-28"));
		
		System.out.println(pc.nombreProfesorMasGruposImperativo(26,27));
		System.out.println(pc.nombreProfesorMasGruposFuncional(26,27));
		
		LocalDateTime anio = LocalDateTime.of(2003,1,1,0,0);
		System.out.println(pc.nombresAlumnosMayorNotaImperativo(4, anio));
		System.out.println(pc.nombresAlumnosMayorNotaFuncional(4, anio));		
	}
	
	public double promedioEdadProfesoresImperativo(String dni) {
		Integer numProf = 0;
		double sumEdadProf = 0.;
		
		for (Matricula m : c.matriculas().todas()) { // Busca la matrícula con nuestro dni entre todas las matrículas
			if (m.dni().equals(dni)) {
				for (Asignacion a : c.asignaciones().todas()) { // Busca la asignación con ida y idg de la matrícula entre todas las asignaciones
					if (m.ida() == a.ida() && m.idg() == a.idg()) {
						for (Profesor p : c.profesores().todos()) { // Busca el profesor con dni de la asiganación de entre todos los profesores
							if (p.dni().equals(a.dni())) {
								numProf += 1;
								sumEdadProf += p.edad();
							}
						}
					}
				}
			}
		}
		
		return sumEdadProf/numProf;
	}

	public double promedioEdadProfesoresFuncional(String dni) {
		List<Matricula> ms = c.matriculas().todas().stream()
				.filter(x -> x.dni().equals(dni)).toList();
		List<Asignacion> as = c.asignaciones().todas().stream()
				.filter(x -> ms.stream().anyMatch(m -> m.ida() == x.ida() && m.idg() == x.idg()))
				.toList();
		List<String> dnisAsignados = as.stream().map(Asignacion::dni).toList();

	    return c.profesores().todos().stream()
	        .filter(x -> dnisAsignados.contains(x.dni()))
	        .collect(Collectors.averagingDouble(Profesor::edad));
	}
	
	public Grupo grupoMayorDiversidadEdadImperativo() {
		Grupo grupoMayorDiversidadEdad = null;
		Integer difmax = Integer.MIN_VALUE;
		
		for (Grupo gr : c.grupos().todos()) {
			List<Alumno> alumnosDeGrupo = new ArrayList<>();
			Integer emin = Integer.MAX_VALUE;
			Integer emax = Integer.MIN_VALUE;
			Integer dif = Integer.MIN_VALUE;
			
			for (Matricula m : c.matriculas().todas()) {
				if (m.ida().equals(gr.ida()) && m.idg().equals(gr.idg())) {
					Alumno a = c.alumnos().alumno(m.dni());
					if (a != null) alumnosDeGrupo.add(a);
				}
			}
			
			if (!alumnosDeGrupo.isEmpty()) {
				for (Alumno a : alumnosDeGrupo) {
					if (a.edad() < emin) {
						emin = a.edad();
					}
					if (a.edad() > emax) {
						emax = a.edad();
					}
				}
				
				dif = emax - emin;

				if (dif > difmax) {
					difmax = dif;
					grupoMayorDiversidadEdad = gr;
				}
			}
		}
		
		return grupoMayorDiversidadEdad;
	}
	
	public Grupo grupoMayorDiversidadEdadFuncional() {
	    return c.grupos().todos().stream()
	        .map(gr -> {
	            List<Alumno> alumnosDeGrupo = c.matriculas().todas().stream()
	                .filter(m -> m.ida().equals(gr.ida()) && m.idg().equals(gr.idg()))
	                .map(m -> c.alumnos().alumno(m.dni()))
	                .collect(Collectors.toList());

	            if (alumnosDeGrupo.isEmpty()) {
	                return null;
	            }

	            Integer emin = alumnosDeGrupo.stream().mapToInt(Alumno::edad).min().orElse(Integer.MAX_VALUE);
	            Integer emax = alumnosDeGrupo.stream().mapToInt(Alumno::edad).max().orElse(Integer.MIN_VALUE);
	            Integer dif = emax - emin;

	            return new Object[] { gr, dif };
	        })
	        .max(Comparator.comparingInt(arr -> (Integer) arr[1]))
	        .map(arr -> (Grupo) arr[0])
	        .orElse(null);
	}
	
	public Alumno alumnoMasMatriculasImperativo() {
		HashMap<Alumno,Integer> numMatriculasAlumnos = new HashMap<>();
		Alumno alumnoMasMatriculas = null;
		
		for (Alumno a : c.alumnos().todos()) { // Rellena el Map con cada Alumno y el número de matrículas a su nombre
			Integer numMatriculasAlumno = 0;
			for (Matricula m : c.matriculas().todas()) {
				if (a.dni().equals(m.dni())){
					numMatriculasAlumno++;
				}
			}
			numMatriculasAlumnos.put(a, numMatriculasAlumno);
		}
		
		for (Alumno i : c.alumnos().todos()) { // Busca el alumno con más matrículas
			if (alumnoMasMatriculas == null || numMatriculasAlumnos.get(i) > numMatriculasAlumnos.get(alumnoMasMatriculas)) {
				alumnoMasMatriculas = i;
			}
		}
		/* ==>
		 * Una forma alternativa de buscar el máximo de numMatriculasAlumnos sería :
		 * return Collections.max(numMatriculasAlumnos.entrySet(), Map.Entry.comparingByValue()).getKey();
		 */
		
		return alumnoMasMatriculas;
	}
	
	public Alumno alumnoMasMatriculasFuncional() {
	    return c.matriculas().todas().stream()
	        .collect(Collectors.groupingBy(m -> c.alumnos().todos().stream()
	            .filter(a -> a.dni().equals(m.dni()))
	            .findFirst()
	            .orElse(null), Collectors.counting())) // Agrupa por Alumno directamente
	        .entrySet().stream()
	        .max(Map.Entry.comparingByValue()) // Encuentra el Alumno con más matrículas
	        .map(Map.Entry::getKey)
	        .orElse(null);
	}
	
	public Map<String,Map<String,Integer>> rangosEdadPorAlumnoImperativo(String rangoStr) {
		Map<String,Map<String,Integer>> rangosEdadAlumnos = new HashMap<>();
		String[] rangos = rangoStr.split(",");
		
		for (String r : rangos) {
			// r = r.replaceAll("\\s+", "");
			if (!r.matches("\\d+-\\d+")) {
    			throw new IllegalArgumentException("Formato incorrecto: " + r);
    		}
			
			String[] valores = r.split("-");
			Integer min = Integer.parseInt(valores[0].strip());
			Integer max = Integer.parseInt(valores[1].strip());
			Map<String,Integer> alumnoEdad = new HashMap<>();
			
			for (Alumno a : c.alumnos().todos()) {
				if (a.edad() >= min && a.edad() <= max) {
					alumnoEdad.put(a.nombre(), a.edad());
				}
			}
			rangosEdadAlumnos.put(r,alumnoEdad);
		}
		
		return rangosEdadAlumnos;
	}
	
	public Map<String, Map<String, Integer>> rangosEdadPorAlumnoFuncional(String rangoStr) {
	    return Arrays.stream(rangoStr.split(",")) // Convierte la cadena de rangos en un stream de Strings, separando por comas
	    	.peek(r -> {
	    		// r = r.replaceAll("\\s+", ""); // Quita espacios en blanco
	    		if (!r.matches("\\d+-\\d+")) { // Verifica que el formato sea "número-número"
	    			throw new IllegalArgumentException("Formato incorrecto: " + r);
	    		}
	        })
	        .collect(Collectors.toMap( // Convierte el rango "min-max" en un array de enteros [min, max]
	            r -> r,
	            r -> {
	                String[] valores = r.split("-");
	                int min = Integer.parseInt(valores[0].strip());
	                int max = Integer.parseInt(valores[1].strip());
	                return c.alumnos().todos().stream()
	                    .filter(a -> a.edad() >= min && a.edad() <= max)
	                    .collect(Collectors.toMap(
	                    		Alumno::nombre, 
	                    		Alumno::edad,
	                    		(a,b)->a // Evita nombres duplicados
	                    		));
	                }
	        ));
	}
	
	public String nombreProfesorMasGruposImperativo(Integer min, Integer max) {
		if (min > max) {
			throw new IllegalArgumentException("Parámetros no válidos : min debe ser mero que max");
		}
		
		HashMap<Profesor,Integer> numGruposProfesores = new HashMap<>();
		Profesor profesorMasGrupos = null;
		
		for (Profesor p : c.profesores().todos()) { // Rellena el Map con cada Profesor y el número de asignaciones a su nombre
			HashSet<Integer> gruposProfesor = new HashSet<Integer>(); // GruposProgesor lleva la cuenta de en qué grupos está asignado un Profesor
			for (Asignacion a : c.asignaciones().todas()) {
				if (p.dni().equals(a.dni()) && p.edad() >= min && p.edad() <= max){
					gruposProfesor.add(a.idg());
				}
			}
			if (gruposProfesor.size() != 0) { // Reduce las búsquedas a solo aquellos que tengan al menos un grupo asignado
				numGruposProfesores.put(p, gruposProfesor.size());
			}
		}
		
		if (numGruposProfesores.isEmpty()) { // Verifica que el map esté vacío, es decir, que no existan profesores con ese rango de edad
			return null;
		} else {
			for (Profesor i : numGruposProfesores.keySet()) { // Busca el Profesor con más asignaciones
				if (profesorMasGrupos == null || numGruposProfesores.get(i) > numGruposProfesores.get(profesorMasGrupos)) {
					profesorMasGrupos = i;
				}
			}
			/* ==>
			 * Una forma alternativa de buscar el máximo de numGruposProfesores sería :
			 * return Collections.max(numGruposProfesores.entrySet(), Map.Entry.comparingByValue()).getKey().nombre();
			 */
			
			return profesorMasGrupos.nombre();
		}
	}

	public String nombreProfesorMasGruposFuncional(Integer min, Integer max) {
	    if (min > max) {
	        throw new IllegalArgumentException("Parámetros no válidos: min debe ser mero que max");
	    }

	    Map<Profesor, Integer> numGruposProfesores = c.profesores().todos().stream()
	        .filter(p -> p.edad() >= min && p.edad() <= max)
	        .collect(Collectors.toMap(
	            p -> p,
	            p -> (int) c.asignaciones().todas().stream()
	                .filter(a -> a.dni().equals(p.dni()))
	                .map(Asignacion::idg)
	                .distinct() // Evita repeticiones de idg
	                .count()
	        )); // Aquellos profesores con 0 grupos asignados no se incluirán al Map
	    
	    return numGruposProfesores.isEmpty()
	        ? null
	        : numGruposProfesores.entrySet().stream()
	            .max(Map.Entry.comparingByValue())
	            .map(Map.Entry::getKey)
	            .map(Profesor::nombre)
	            .orElse(null);
	}

	public List<String> nombresAlumnosMayorNotaImperativo(Integer n, LocalDateTime a){
		if (n == null || a == null) {
			throw new IllegalArgumentException("Los parámetros n y a no pueden ser null");
		}
		if (n < 1 || n > 10) {
			throw new IllegalArgumentException("El parámetro n debe estar entre 1 y 10 (inclusive)");
		}
		
		List<Alumno> alumnosMayorNota = new ArrayList<>();
		List<String> nombresAlumnosMayorNota = new ArrayList<>();
		
		for (Alumno al : c.alumnos().todos()) { // Filtra los alumnos según su fecha de nacimiento
			if (al.fechaDeNacimiento().isAfter(a)) {
				alumnosMayorNota.add(al);
			}
		}
		
		Collections.sort(alumnosMayorNota, Comparator.comparing(Alumno::nota).reversed()); // Ordena de mayor a menor nota
		List<Alumno> alumnosMayorNotaLimitado = alumnosMayorNota.subList(0, Math.min(n, alumnosMayorNota.size())); // Limita a los primeros n alumnos

		for (Alumno al : alumnosMayorNotaLimitado) { // Extrae los nombres de los alumnos seleccionados
			nombresAlumnosMayorNota.add(al.nombre());
		}
		
		return nombresAlumnosMayorNota;
	}
	
	public List<String> nombresAlumnosMayorNotaFuncional(Integer n, LocalDateTime a){
		if (n == null || a == null) {
			throw new IllegalArgumentException("Los parámetros n y a no pueden ser null");
		}
		if (n < 1 || n > 10) {
			throw new IllegalArgumentException("El parámetro n debe estar entre 1 y 10 (inclusive)");
		}
		
		return c.alumnos().todos().stream()
				.filter(al -> al.fechaDeNacimiento().isAfter(a))
				.sorted(Comparator.comparing(Alumno::nota).reversed())
				.map(Alumno::nombre)
				.limit(n)
				.collect(Collectors.toList()); // Podría utilizarse .toList() directamente, pero versiones de Java >16 no lo soportarían
	}
}