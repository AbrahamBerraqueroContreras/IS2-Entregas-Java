package entrega6.test;

import java.time.LocalDateTime;

import entrega6.preguntas.PreguntasAeropuertos;

public class TestPreguntasAeropuertos {
    public static void main(String[] args) {
        PreguntasAeropuertos pa = new PreguntasAeropuertos();

        System.out.println("== Test: Ciudad con el aeropuerto de mayor facturación ==");
        LocalDateTime fechaInicio = LocalDateTime.of(2020, 3, 1, 0, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2020, 6, 1, 0, 0, 0);

        System.out.println("Rango de fechas: " + fechaInicio + " - " + fechaFin);
        System.out.println("Imperativo: " + pa.ciudadAeropuertoMayorFacturacionImperativo(fechaInicio, fechaFin));
        System.out.println("Funcional: " + pa.ciudadAeropuertoMayorFacturacionFuncional(fechaInicio, fechaFin));
    }
}
