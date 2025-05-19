package entrega6.test;

import java.time.LocalDate;

import entrega6.preguntas.PreguntasBancos;

public class TestPreguntasBancos {
    public static void main(String[] args) {
        PreguntasBancos pb = new PreguntasBancos();

        System.out.println("== Test: Valor total de préstamos para clientes de edad inferior a un límite ==");
        int edadMaxima = 21;
        double valorMinimo = 2000.0;
        double valorMaximo = 90000.0;
        LocalDate fechaMinima = LocalDate.of(2018, 1, 1);

        System.out.println("Edad máxima del cliente: " + edadMaxima);
        System.out.println("Rango de valores de préstamo: " + valorMinimo + " - " + valorMaximo);
        System.out.println("Fecha mínima de concesión del préstamo: " + fechaMinima);

        System.out.println("Imperativo: " + pb.valorTotalPrestamosImperativo(edadMaxima, valorMinimo, valorMaximo, fechaMinima));
        System.out.println("Funcional: " + pb.valorTotalPrestamosFuncional(edadMaxima, valorMinimo, valorMaximo, fechaMinima));
    }
}