package entrega1;

public class Funciones {

	public static void main(String[] args) {

	}

	public static Integer factorial(Integer n) {
		int rt = 1;
		for (int i = 1; i <= n; i++) {
			rt *= i;
		}

		return rt;
	}

	public static Double f(Double a) {
		return 2 * a * a;
	}

	public static Double fd(Double a) {
		return 4 * a;
	}

	public static Integer fun1(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		int rt = 1;
		for (int i = 0; i < k; i++) {
			rt *= (n - i + 1);
		}

		return rt;
	}

	public static Integer fun2(Integer a1, Integer r, Integer k) {
		double rt = 1.;
		for (int i = 0; i < k; i++) {
			rt *= a1 * Math.pow(r, i);
		}

		return (int) rt;
	}

	public static Integer fun3(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		return (Funciones.factorial(n)) / (Funciones.factorial(k) * Funciones.factorial(n - k));
	}

	public static Double fun4(Integer n, Integer k) {
		if (n <= k) {
			throw new ArithmeticException("n debe ser mayor a k");
		}

		double s = 0.;
		for (int i = 0; i < k; i++) {
			s += (Math.pow(-1, i)) * (Funciones.fun3(k + 1, i + 1)) * (Math.pow((k - i), n));
		}

		return s / Funciones.factorial(k);
	}

	public static Double fun5(Double a, Double e) {
		while (Math.abs(Funciones.f(a)) > e) {
			a -= Funciones.f(a) / Funciones.fd(a);
		}

		return a;
	}

}