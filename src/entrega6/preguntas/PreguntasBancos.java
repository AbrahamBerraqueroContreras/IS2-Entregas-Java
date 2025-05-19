package entrega6.preguntas;

import us.lsi.bancos.*;
import us.lsi.ejemplos_b1_tipos.Persona;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PreguntasBancos {
	
	private Banco bc = Banco.of();
	
	public static void main(String[] args) {
		PreguntasBancos pb = new PreguntasBancos();
		
		LocalDate date = LocalDate.of(2018, 1, 1);
		System.out.println(pb.valorTotalPrestamosImperativo(21, 2000., 90000., date));
		System.out.println(pb.valorTotalPrestamosFuncional(21, 2000., 90000., date));
	}
	
	public Map<Persona,List<Double>> valorTotalPrestamosImperativo(Integer e, Double a, Double b, LocalDate f) {
		if (e <= 18 ) {
			throw new IllegalArgumentException("La edad debe ser superior a 18 años");
		}
		if (a < 0 || b < 0 || a >= b) {
			throw new IllegalArgumentException("a debe ser menor que b (ambos positivos)");
		}
		
		Map<Persona,List<Double>> valorTotalPrestamosCliente = new HashMap<>();
		
		for (Persona pe : bc.personas().todos()) {
			List<Double> valores = new ArrayList<>();
			
			if (pe.edad() < e) {
				for (Prestamo pr : bc.prestamos().todos()) {
					if (pe.dni().equals(pr.dniCliente()) && pr.cantidad() > a && pr.cantidad() < b && pr.fechaComienzo().isBefore(f)){
						valores.add(pr.cantidad());
					}
				}
			}
			if (!valores.isEmpty()) {
				valorTotalPrestamosCliente.put(pe, valores);
			}
		}
		
		return valorTotalPrestamosCliente;
	}
	
	public Map<Persona,List<Double>> valorTotalPrestamosFuncional(Integer e, Double a, Double b, LocalDate f) {

	    return bc.personas().todos().stream()
	        .filter(pe -> pe.edad() < e)
	        .collect(Collectors.toMap(
	            pe -> pe,
	            pe -> bc.prestamos().todos().stream()
	                .filter(pr -> pe.dni().equals(pr.dniCliente()) 
	                        && pr.cantidad() > a && pr.cantidad() < b 
	                        && pr.fechaComienzo().isBefore(f))
	                .map(Prestamo::cantidad)
	                .collect(Collectors.toList())
	        ))
	        .entrySet().stream()
	        .filter(entry -> !entry.getValue().isEmpty()) // Elimina claves sin préstamos
	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  // Reconstruye el Map sin valores vacíos
	}
}
