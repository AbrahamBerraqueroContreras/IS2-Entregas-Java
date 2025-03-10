package entrega4;

public class Funciones {

	public static void main(String[] args) {

	}

	public static Integer factorialEnteros(Integer n) {
		int rt = 1;
		for (int i = 1; i <= n; i++) {
			rt *= i;
		}

		return rt;
	}

	public static Double funcion(Double a) {
		return 2 * a * a;
	}

	public static Double funcionDerivada(Double a) {
		return 4 * a;
	}

	public static Integer productorio(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		int rt = 1;
		for (int i = 0; i < k; i++) {
			rt *= (n - i + 1);
		}

		return rt;
	}

	public static Integer productorioSecuenciaGeometrica(Integer a1, Integer r, Integer k) {
		double rt = 1.;
		for (int i = 0; i < k; i++) {
			rt *= a1 * Math.pow(r, i);
		}

		return (int) rt;
	}

	public static Integer numeroCombinatorio(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		return (Funciones.factorialEnteros(n)) / (Funciones.factorialEnteros(k) * Funciones.factorialEnteros(n - k));
	}

	public static Double sumatorioS(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		double s = 0.;
		for (int i = 0; i < k; i++) {
			s += (Math.pow(-1, i)) * (Funciones.numeroCombinatorio(k + 1, i + 1)) * (Math.pow((k - i), n));
		}

		return s / Funciones.factorialEnteros(k);
	}

	public static Double newton(Double a, Double e) {
		while (Math.abs(Funciones.funcion(a)) > e) {
			a -= Funciones.funcion(a) / Funciones.funcionDerivada(a);
		}

		return a;
	}

}