package examen5;

import entrega5.ListaOrdenada;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

	public ClientesPorAntiguedad() {
		super(Comparator.comparingInt(Cliente::antiguedad).reversed());
	}
	
	public List<Cliente> topClientes(int n) {
        List<Cliente> topClientes = new ArrayList<>();
        
        for (int i = 0; ( i < n && i < this.elements().size() ); i++) { // 'i < this.elements().size()' evita que index quede fuera de rango
            topClientes.add(this.elements().get(i));
        }
        
        return topClientes;
    }
}
