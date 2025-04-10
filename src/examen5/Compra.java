package examen5;

public record Compra(Cliente cliente, String descripcion, double importe) {

	public Compra {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede ser nula ni vacía.");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        }
    }

	public String toString() {
        return "Compra [Nombre de cliente= " + cliente.nombre() +
               ", descripción= " + descripcion +
               ", importe= " + String.format("%.2f", importe) + " €]";
    }
}
