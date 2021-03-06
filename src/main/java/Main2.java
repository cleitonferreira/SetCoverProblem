/*
* Programa Java para resolver o problema da capa do conjunto assumindo no máximo 2 elementos em um subconjunto
*
*
* Este é um programa java para resolver o problema da cobertura do conjunto. O conjunto que cobre o problema (SCP) é uma questão
* clássica em combinatória, ciência da computação e teoria da complexidade. Dado um conjunto de elementos \ {1,2,…, m \}
* (chamado de universo) e um conjunto S de n conjuntos cuja união igual ao universo, o problema de cobertura do conjunto
* é identificar o menor subconjunto de S cuja união é igual ao universo. Por exemplo, considere o universo U = {1, 2, 3, 4, 5}
* e o conjunto de conjuntos S = {{1, 2, 3}, {2, 4}, {3, 4}, {4, 5}}. Claramente a união de S é U. No entanto, podemos cobrir
* todos os elementos com o seguinte, menor número de conjuntos: {{1, 2, 3}, {4, 5}}.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main2
{
    interface Filter<T>
    {
        boolean matches(T t);
    }

    private static <T> Set<T> shortestCombination(Filter<Set<T>> filter,
                                                  List<T> listOfSets)
    {
        final int size = listOfSets.size();
        if (size > 20)
            throw new IllegalArgumentException("Muitas combinações");
        int combinations = 1 << size;
        List<Set<T>> possibleSolutions = new ArrayList<Set<T>>();
        for (int l = 0; l < combinations; l++)
        {
            Set<T> combination = new LinkedHashSet<T>();
            for (int j = 0; j < size; j++)
            {
                if (((l >> j) & 1) != 0)
                    combination.add(listOfSets.get(j));
            }
            possibleSolutions.add(combination);
        }
        // the possible solutions in order of size.
        Collections.sort(possibleSolutions, new Comparator<Set<T>>()
        {
            public int compare(Set<T> o1, Set<T> o2)
            {
                return o1.size() - o2.size();
            }
        });
        for (Set<T> possibleSolution : possibleSolutions)
        {
            if (filter.matches(possibleSolution))
                return possibleSolution;
        }
        return null;
    }

    public static void main(String[] args)
    {
        Integer[][] arrayOfSets = { { 1, 2 }, { 3, 8 }, { 9, 10 }, { 1, 10 },
                { 2, 3 }, { 4, 5 }, { 5, 7 }, { 5, 6 }, { 4, 7 }, { 6, 7 },
                { 8, 9 }, };
        Integer[] solution = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Set<Integer>> listOfSets = new ArrayList<Set<Integer>>();
        for (Integer[] array : arrayOfSets)
            listOfSets.add(new LinkedHashSet<Integer>(Arrays.asList(array)));
        final Set<Integer> solutionSet = new LinkedHashSet<Integer>(
                Arrays.asList(solution));
        Filter<Set<Set<Integer>>> filter = new Filter<Set<Set<Integer>>>()
        {
            public boolean matches(Set<Set<Integer>> integers)
            {
                Set<Integer> union = new LinkedHashSet<Integer>();
                for (Set<Integer> ints : integers)
                    union.addAll(ints);
                return union.equals(solutionSet);
            }
        };
        Set<Set<Integer>> firstSolution = shortestCombination(filter,
                listOfSets);
        System.out.println("A combinação mais curta foi: " + firstSolution);
    }
}