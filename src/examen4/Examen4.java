package examen4;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Examen4 {

	public static void main(String[] args) {
		
		try {
			System.out.println("El productorio de los 4 primeros dígitos impares es : "
					+ Examen4.productoImpares(4)
					+ "\n");
			System.out.println("La suma de la secuencia geométrica alternada con a1 = 2.0, r = 3.0, y k = 4 es : " 
            		+ Examen4.sumaGeometricaAlternada(2., 3., 4 )
            		+ "\n");
			System.out.println("El combinatorio sin múltiplos de 3 de 7 y 3 es : " 
            		+ Examen4.combinatorioSinMultiplosDeTres(7, 3)
            		+ "\n");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
        try {
		System.out.println("Líneas que cumplen la condición : " 
				+ Examen4.filtrarLineasConsecutivas("resources/archivo_palabras.txt", List.of("salud", "software", "codigo"))
				+ "\n");
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int productoImpares(Integer n) {
		
		if ( n == null || n <= 0 ) {
			throw new IllegalArgumentException("n debe ser mayor a 0.");
		}
		
		int prod = 1;
		int i = 1;
		int impar = 1;
		
		while( i < n ) {
			impar += 2;
			prod *= impar;
			
			i++;
		}
		
		return prod;
	}
	
	
	public static double sumaGeometricaAlternada(Double a1, Double r, Integer k) {
		
		if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0.");
        }
        if (a1 == null || r == null || a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos.");
        }
		
		double suma = 0.;
		
		for (int n = 1; n <= k; n++) {
            double an = Math.pow(-1, n) * a1 * Math.pow(r, n - 1);
            suma += an;
        }
		
		return suma;
	}
	
	private static long factorialSinMultiplosDeTres(Integer n, Integer limit) {
		long fact = 1;
        for (int i = n; i > limit; i--) {
            if (i % 3 != 0) {
                fact *= i;
            }
        }
        
        return fact;
	}
	
	public static long combinatorioSinMultiplosDeTres(Integer n, Integer k) {
		
        if (n < k || n <= 0 || k < 0) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k y ambos deben ser positivos.");
        }

        return factorialSinMultiplosDeTres(n, n-k) / factorialSinMultiplosDeTres(k, 0);
    }
	
	
	public static List<String> filtrarLineasConsecutivas(String archivo, List<String> palabrasClave) throws IOException {
		
		String sep = archivo.substring(archivo.length() - 3, archivo.length()).equals("csv") ? "," : " ";
        List<String> lineasFiltradas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(archivo)))) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] palabras = linea.trim().split(sep);
                for (int i = 0; i < palabras.length - 1; i++) {
                    if (palabrasClave.contains(palabras[i].toLowerCase()) && palabrasClave.contains(palabras[i + 1].toLowerCase())) {
                        lineasFiltradas.add(linea.trim());
                        break;
                    }
                }
            }
            
        }

        return lineasFiltradas;
    }
	
}

