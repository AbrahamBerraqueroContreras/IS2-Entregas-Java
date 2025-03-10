package entrega4;

public class testLecturas {

	public static void main(String[] args) {
		System.out.println("Función 6 : REPETICIÓN DE PALABRAS\nLa palabra 'quijote' apararece " 
				+ Lecturas.numeroRepeticiones("resources/lin_quijote.txt", "quijote", " ") + " veces en lin_quijote.txt"
				+ "\n");
		System.out.println("Función 7 : PALABRA EN LÍNEAS\nLas líneas en las que aparece la palabra 'quijote' en lin_quijote.txt son: "
				+ Lecturas.palabraEnLinea("resources/lin_quijote.txt", "quijote")
				+ "\n");
		System.out.println("Función 8 : PALABRAS ÚNICAS\nLas palabras únicas en archivo_palabras.txt son: "
				+ Lecturas.palabrasUnicas("resources/archivo_palabras.txt")
				+ "\n");
		System.out.println("Función 9 : LONGITUD PROMEDIO DE LÍNEAS\nLa longitud promedio de las líneas en palabras_random.csv es de "
				+ Lecturas.longitudPromedioLinea("resources/palabras_random.csv") + " palabras"
				+ "\n");
	}
	
}
