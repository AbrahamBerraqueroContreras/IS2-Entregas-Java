package examen5;

import java.util.List;
import java.util.Map;

public class TestClases {
    public static void main(String[] args) {
        // Crear clientes
        Cliente cliente1 = new Cliente("Juan", 5);
        Cliente cliente2 = new Cliente("Ana", 3);
        Cliente cliente3 = new Cliente("Luis", 10);

        // Crear compras
        Compra compra1 = new Compra(cliente1, "Taza personalizada", 15.00);
        Compra compra2 = new Compra(cliente2, "Cuadro decorativo", 30.00);
        Compra compra3 = new Compra(cliente1, "Libro de cuentos", 12.50);
        Compra compra4 = new Compra(cliente1, "Lámpara de noche", 45.00);
        Compra compra5 = new Compra(cliente2, "Taza con foto", 20.00);
        Compra compra6 = new Compra(cliente3, "Camiseta especial", 25.00);

        // *** Pruebas para ClientesPorAntiguedad ***
        ClientesPorAntiguedad clientesPorAntiguedad = new ClientesPorAntiguedad();
        clientesPorAntiguedad.addAll(List.of(cliente1, cliente2, cliente3));

        // Obtener los 2 clientes con más antigüedad
        List<Cliente> topClientes = clientesPorAntiguedad.topClientes(2);
        System.out.println("Top 2 clientes más antiguos: " + topClientes);

        // *** Pruebas para HistorialCompras ***
        HistorialCompras historial = new HistorialCompras();
        historial.addAll(List.of(compra1, compra2, compra3, compra4, compra5, compra6));

        // Total gastado por Juan
        double totalJuan = historial.totalGastadoPor(cliente1);
        System.out.println("Total gastado por Juan: " + totalJuan);

        // Compras mayores a 20.00
        List<Compra> comprasMayores = historial.comprasMayoresA(20.00);
        System.out.println("Compras mayores a 20.00: " + comprasMayores);

        // *** Pruebas para ColaComprasPendientes ***
        ColaComprasPendientes colaPendientes = new ColaComprasPendientes();
        colaPendientes.addAll(List.of(compra1, compra2, compra3, compra4, compra5, compra6));

        // Buscar compra por descripción
        Compra compraEncontrada = colaPendientes.buscarCompraPorDescripcion("Taza");
        System.out.println("Compra encontrada con 'Taza': " + compraEncontrada);

        // Filtrar compras por cliente y producto
        List<Compra> comprasFiltradas = colaPendientes.filtrarPorClienteYProducto(cliente1, "Lámpara");
        System.out.println("Compras filtradas para Juan con 'Lámpara': " + comprasFiltradas);

        // *** Pruebas para EstadisticasClientes ***
        List<Compra> todasLasCompras = List.of(compra1, compra2, compra3, compra4, compra5, compra6);

        // Versión funcional
        Map<Cliente, List<Compra>> mapaFuncional = EstadisticasClientes.agruparComprasPorClienteFuncional(todasLasCompras);
        System.out.println("Agrupación funcional de compras por cliente: " + mapaFuncional);

        // Versión imperativa
        Map<Cliente, List<Compra>> mapaImperativa = EstadisticasClientes.agruparComprasPorClienteImperativa(todasLasCompras);
        System.out.println("Agrupación imperativa de compras por cliente: " + mapaImperativa);
    }
}