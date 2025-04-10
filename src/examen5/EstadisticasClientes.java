package examen5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstadisticasClientes {
	
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream()
            .collect(Collectors.groupingBy(Compra::cliente));
    }

    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativa(List<Compra> compras) {
        Map<Cliente, List<Compra>> mapaCompras = new HashMap<>();

        for (Compra compra : compras) {
            Cliente cliente = compra.cliente();
            if (!mapaCompras.containsKey(cliente)) {
                mapaCompras.put(cliente, new ArrayList<>());
            }
            mapaCompras.get(cliente).add(compra);
        }

        return mapaCompras;
    }
}
