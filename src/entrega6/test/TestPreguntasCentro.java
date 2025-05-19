package entrega6.test;

import java.time.LocalDateTime;

import entrega6.preguntas.PreguntasCentro;

public class TestPreguntasCentro {
	public static void main(String[] args) {
        PreguntasCentro pc = new PreguntasCentro();

        System.out.println("== Test: Promedio de edad de profesores ==");
        String dniAlumno = "72074089R";
        System.out.println("DNI del alumno: " + dniAlumno);
        System.out.println("Imperativo: " + pc.promedioEdadProfesoresImperativo(dniAlumno));
        System.out.println("Funcional: " + pc.promedioEdadProfesoresFuncional(dniAlumno));

        System.out.println("\n== Test: Grupo con mayor diversidad de edad ==");
        System.out.println("Buscando el grupo con mayor diferencia de edad entre alumnos...");
        System.out.println("Imperativo: " + pc.grupoMayorDiversidadEdadImperativo());
        System.out.println("Funcional: " + pc.grupoMayorDiversidadEdadFuncional());

        System.out.println("\n== Test: Alumno con más matrículas ==");
        System.out.println("Buscando el alumno con mayor número de matrículas...");
        System.out.println("Imperativo: " + pc.alumnoMasMatriculasImperativo());
        System.out.println("Funcional: " + pc.alumnoMasMatriculasFuncional());

        System.out.println("\n== Test: Rangos de edad por alumno ==");
        String rangosEdad = "20-23,24-26,26-28";
        System.out.println("Rangos de edad proporcionados: " + rangosEdad);
        System.out.println("Imperativo: " + pc.rangosEdadPorAlumnoImperativo(rangosEdad));
        System.out.println("Funcional: " + pc.rangosEdadPorAlumnoFuncional(rangosEdad));

        System.out.println("\n== Test: Profesor con mayor número de grupos ==");
        int edadMin = 26;
        int edadMax = 27;
        System.out.println("Edad mínima: " + edadMin + ", Edad máxima: " + edadMax);
        System.out.println("Imperativo: " + pc.nombreProfesorMasGruposImperativo(edadMin, edadMax));
        System.out.println("Funcional: " + pc.nombreProfesorMasGruposFuncional(edadMin, edadMax));

        System.out.println("\n== Test: Nombres de alumnos con mayor nota ==");
        LocalDateTime anio = LocalDateTime.of(2003, 1, 1, 0, 0);
        int cantidadAlumnos = 4;
        System.out.println("Año de nacimiento después de: " + anio.getYear());
        System.out.println("Cantidad de alumnos a considerar: " + cantidadAlumnos);
        System.out.println("Imperativo: " + pc.nombresAlumnosMayorNotaImperativo(cantidadAlumnos, anio));
        System.out.println("Funcional: " + pc.nombresAlumnosMayorNotaFuncional(cantidadAlumnos, anio));
    }

}
