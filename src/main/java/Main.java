import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* Cobertura de conjunto gananciosa
* Definição de problema
* No problema de cobertura de conjunto, temos um universo U, tal que | U | = n, e conjuntos S1, ...,
* k ⊆ U. Uma cobertura de conjunto é uma coleção C de alguns dos conjuntos de S1, ..., Sk cuja união
* é todo o universo U. Formalmente, C é uma cobertura de conjunto se S Si∈C Si = U. Gostaríamos de minimizar | C |.
* */

public class Main {
	// LinkedHashMap para manter a ordem !!
	private static LinkedHashMap<String, Set<Integer>> sets = new LinkedHashMap<String, Set<Integer>>();
	private static HashSet<Integer> universe = new HashSet<Integer>();
	private static Set<Integer> intersection;

	public static void main(String[] args) {

		List<String> listaArquivos = Arrays.asList("subSets.txt", "subSets2.txt", "subSets3.txt", "subSets4.txt",
				"subSets5.txt", "subSets6.txt", "subSets7.txt", "subSets8.txt", "subSets9.txt", "subSets10.txt", "subSets11.txt");

		for(String arquivo: listaArquivos) {
			readFile(arquivo);
		}

		/*readFile("scp-instances/scp42.txt");
		  printSets();
		  greedySetCover();*/
	}

	private static void readFile(String filename) {
		int set = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
				String elements[] = line.split(",");
				//String elements[] = line.trim().split(" ");
				Set<Integer> list = new HashSet<Integer>();
				for (String e : elements) {
					list.add(Integer.parseInt(e));
					universe.add(Integer.parseInt(e));
				}
				sets.put("S" + set, list);
				set++;
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		printSets();
	}

	private static void printSets() {
		for (String key : sets.keySet()) {
			System.out.printf("%-3s = ",key);
			System.out.println(sets.get(key));
		}
		System.out.println("\nConjunto Universo  = " + universe);

		greedySetCover();
	}

	private static void greedySetCover() {
		ArrayList<String> coverKeys = new ArrayList<String>();

		while (!universe.isEmpty()) {
			int max = Integer.MIN_VALUE;
			String maxKey = "";
			Set<Integer> maxIntersection = new HashSet<Integer>();
			for (String key : sets.keySet()) {
				intersection = new HashSet<Integer>(sets.get(key));
				intersection.retainAll(universe);
				if (max < intersection.size()) {
					maxKey = key;
					max = intersection.size();
					maxIntersection = intersection;
				}
			}
			universe.removeAll(maxIntersection);
			coverKeys.add(maxKey);
		}
		System.out.println("Set-cover = " + coverKeys);
		System.out.println("------------------------------------------------------------------------------------");
	}
}
