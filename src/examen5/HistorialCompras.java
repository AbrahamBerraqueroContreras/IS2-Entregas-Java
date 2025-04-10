package examen5;

import entrega5.Pila;
import java.util.List;
import java.util.stream.Collectors;


public class HistorialCompras extends Pila<Compra> {

	public double totalGastadoPor(Cliente cliente) {
        return this.elements().stream()
            .filter(compra -> compra.cliente().equals(cliente))
            .mapToDouble(Compra::importe) 
            .sum(); 
    }


	public List<Compra> comprasMayoresA(double cantidad) {
        return this.elements().stream()
            .filter(compra -> compra.importe() > cantidad) 
            .collect(Collectors.toList());
    }
}
