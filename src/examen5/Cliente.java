package examen5;

public record Cliente(String nombre, int antiguedad) {

	public Cliente {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (antiguedad < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa.");
        }
    }

	public String toString() {
        return "Cliente[nombre=" + nombre + ", antigüedad=" + antiguedad + "]";
	}
}
