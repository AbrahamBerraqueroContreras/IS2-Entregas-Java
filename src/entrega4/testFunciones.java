package entrega4;

public class testFunciones {

	public static void main(String[] args) {
		System.out.println("Función 1 : PRODUCTORIO\nEl productorio con de 4 con 2 repeticiones es: "
				+ Funciones.productorio(4, 2)
				+ "\n");
		System.out.println(
				"Función 2 : PRODUCTO DE SECUENCIA GEOMÉTRICA\nEl producto de la secuencia geométrica con a1 = 3, r = 5 y k = 2 es: "
				+ Funciones.productorioSecuenciaGeometrica(3, 5, 2)
				+ "\n");
		System.out.println(
				"Función 3 : NÚMERO COMBINATORIO\nEl número combinatorio de 4 y 2 es: "
				+ Funciones.numeroCombinatorio(4, 2)
				+ "\n");
		System.out.println("Función 4 : SUMATORIO Y NÚMERO COMBINATORIO\nEl número S(n,k) siendo n = 4 y k = 2 es: "
				+ Funciones.sumatorioS(4, 2)
				+ "\n");
		System.out.println("Función 5 : MÉTODO DE NEWTON\nEl resultado del método de Newton de f(x) = 2*x^2 y f´(x) = 4*x con x0 = 3 y error <= 0.001 es: " 
				+ Funciones.newton(3., 0.001) 
				+ "\n");
	}
	
}
