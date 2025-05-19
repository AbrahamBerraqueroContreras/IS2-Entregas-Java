package entrega6.preguntas;

import us.lsi.aeropuerto.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;

public class PreguntasAeropuertos {

	public static void main(String[] args) {
		PreguntasAeropuertos pa = new PreguntasAeropuertos();
		
		LocalDateTime a = LocalDateTime.of(2020,03,01,00,00,00);
		LocalDateTime b = LocalDateTime.of(2020,06,01,00,00,00);
		System.out.println(pa.ciudadAeropuertoMayorFacturacionImperativo(a, b));
		System.out.println(pa.ciudadAeropuertoMayorFacturacionFuncional(a, b));
	}
	
	public String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime a, LocalDateTime b) {
		if (a.isAfter(b) && Duration.between(a,b).toDays() < 1) {
			throw new IllegalArgumentException("a y b deben llevarse como máximo 1 día de diferencia");
		}
		
		Aeropuerto aeropuertoMayorFacturacion = null;
		Double maxFacturacion = 0.;
		
		for (Aeropuerto ae : Aeropuertos.of().todos()) {
			Double facturacion = 0.;
			
			for (Vuelo v : Vuelos.of().todos()) {
				if (ae.codigo().equals(v.codigoOrigen())) {
					for (OcupacionVuelo ov : OcupacionesVuelos.of().todas()) {
						if (v.codigo().equals(ov.codigoVuelo()) && ov.fecha().isAfter(a) && ov.fecha().isBefore(b)) {
							facturacion += v.precio() * ov.numPasajeros();
							break;
						}
					}	
				}
			}
			
			if (facturacion > maxFacturacion) {
				maxFacturacion = facturacion;
				aeropuertoMayorFacturacion = ae;
			}
		}
		
		return aeropuertoMayorFacturacion.ciudad();
	}
	
	public String ciudadAeropuertoMayorFacturacionFuncional(LocalDateTime a, LocalDateTime b) {
	    if (a.isAfter(b) || Duration.between(a, b).toDays() < 1) {
	        throw new IllegalArgumentException("Las fechas deben tener más de un día de diferencia");
	    }

	    return Aeropuertos.of().todos().stream()
	        .map(ae -> Map.entry(
	            ae, Vuelos.of().todos().stream()
	                .filter(v -> v.codigoOrigen().equals(ae.codigo()))
	                .flatMap(v -> OcupacionesVuelos.of().todas().stream()
	                    .filter(ov -> ov.codigoVuelo().equals(v.codigo()) && ov.fecha().isAfter(a) && ov.fecha().isBefore(b))
	                    .map(ov -> v.precio() * ov.numPasajeros()))
	                .reduce(0.0, Double::sum) // Calcula la facturación total por aeropuerto
	        ))
	        .max(Comparator.comparingDouble(Map.Entry::getValue)) // Encuentra el aeropuerto con mayor facturación
	        .map(Map.Entry::getKey)
	        .map(Aeropuerto::ciudad)
	        .orElse("No se encontró un aeropuerto con facturación válida.");
	}
}