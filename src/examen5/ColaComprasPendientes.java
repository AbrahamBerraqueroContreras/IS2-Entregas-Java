package examen5;

import entrega5.Cola;
import java.util.List;
import java.util.stream.Collectors;

public class ColaComprasPendientes extends Cola<Compra> {
	
	public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : this.elements()) {
            if (compra.descripcion().contains(desc)) {
                return compra; 
            }
        }
        return null; 
    }

	public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return this.elements().stream()
            .filter(compra -> compra.cliente().equals(cliente) && compra.descripcion().contains(producto))
            .collect(Collectors.toList()); 
    }
}
