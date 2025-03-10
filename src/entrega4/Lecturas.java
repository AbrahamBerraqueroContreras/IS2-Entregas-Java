package entrega4;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Lecturas {

	public static void main(String[] args) {

	}

	public static Integer numeroRepeticiones(String file, String cad, String sep) {
		int i = 0;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				for (String word : line.trim().split(sep)) {
					if (cad.equalsIgnoreCase(word)) {
						i++;
					}
				}
			}

			// No es necesario usar 'bufferedReader.close()' utilizando try()

		} catch (IOException e) {
			e.printStackTrace();
		}

		return i;
	}

	public static List<String> palabraEnLinea(String file, String cad) {
		List<String> ls = new ArrayList<String>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.toLowerCase().contains(cad.toLowerCase())) {
					ls.add(line.trim());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public static List<String> palabrasUnicas(String file) {
		List<String> ls = new ArrayList<String>();
		String sep = file.substring(file.length() - 3, file.length()).equals("csv") ? "," : " ";

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				for (String word : line.trim().split(sep)) {
					if (!ls.contains(word.toLowerCase())) {
						ls.add(word.toLowerCase());
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public static Double longitudPromedioLinea(String file) {
		String sep = file.substring(file.length() - 3, file.length()).equals("csv") ? "," : " ";
		double numLine = 0.;
		double numWord = 0.;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				numLine++;
				numWord += line.trim().split(sep).length;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return numLine == 0. ? 0. : numWord / numLine;
	}

}
