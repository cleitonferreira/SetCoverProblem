// Programa Java para resolver o problema de cobertura de conjunto
// assumindo no máximo 2 elementos em um subconjunto

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// Main class
public class Main3 {

    // Interface
    // Declarando a interface, tomando
    // métodos abstratos da interface
    interface Filter<T> {

        boolean matches(T t);
    }

    // Método 1
    // Declarando um método-'shortcombo '
    // Declarando na forma de conjunto também retornando um conjunto
    private static <T> Set<T>
    shortcombo(Filter<Set<T> > filter, List<T> sets)
    {
        // Tomando o tamanho do conjunto
        final int size = sets.size();

        // Verificação de condição
        // Se o tamanho do conjunto for maior que 25
        // Lançamos uma exceção como muitas combinações
        if (size > 20)
            throw new IllegalArgumentException(
                    "Muitas combinações");

        // Agora o comb irá deslocar para a esquerda 1 vez do tamanho
        int comb = 1 << size;

        // Tomando um conjunto com reg = ference possível
        // este Arraylist conterá todas as possíveis soluções
        List<Set<T> > possible = new ArrayList<Set<T> >();

        // Taking a loop which iterates till comb
        for (int i = 0; i < comb; i++) {

            // Tomando um lInkedHashSet de referência
            // combinação
            Set<T> combination = new LinkedHashSet<T>();

            // Fazendo um loop e iterando até o tamanho
            for (int j = 0; j < size; j++) {

                // Se agora mudarmos para a direita i e j
                // e terminando com 1

                // Esta possível lógica nos dará quantos
                // combinações são possíveis
                if (((i >> j) & 1) != 0)

                    // Agora as combinações são adicionadas
                    combination.add(sets.get(j));
            }

            // É adicionado à referência possível
            possible.add(combination);
        }

        // As coleções agora podem ser classificadas de acordo
        // usando o método sort () na classe Collections
        Collections.sort(
                possible, new Comparator<Set<T> >() {

                    // Podemos encontrar o comprimento mínimo tomando
                    // a diferença entre os tamanhos possíveis
                    // Lista
                    public int compare(Set<T> a1, Set<T> a2)
                    {
                        return a1.size() - a2.size();
                    }
                });

        // Agora fazemos a iteração até que seja possível
        for (Set<T> possibleSol : possible) {

            // Em seguida, verificamos a correspondência do possível
            // solução

            // Se isso acontecer, nós retornamos a solução
            // Se não, retornamos nulo
            if (filter.matches(possibleSol))
                return possibleSol;
        }
        return null;
    }

    // Método principal
    public static void main(String[] args)
    {

        // Tomando todas as combinações possíveis
        // Entradas personalizadas na matriz
        Integer[][] all = {
                { 1, 2 }, { 3, 4 }, { 8, 9 },
                { 10, 7 }, { 5, 8 }, { 11, 6 },
                { 4, 5 }, { 6, 7 }, { 10, 11 },
        };

        // Aqui está a lista de números a serem escolhidos
        // Novamente, entradas personalizadas na matriz
        Integer[] solution
                = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        // Aqui, vamos tomar set como um objeto de um ArrayList
        List<Set<Integer> > sets
                = new ArrayList<Set<Integer> >();

        // Agora, pegando um array da função all
        for (Integer[] array : all)

            // Agora pegando esses elementos e adicionando-os
            // um LinkedHashSet
            sets.add(new LinkedHashSet<Integer>(
                    Arrays.asList(array)));

        // Agora, pegando um conjunto inteiro sol e
        // definindo-o como solução
        final Set<Integer> sol = new LinkedHashSet<Integer>(
                Arrays.asList(solution));

        // Agora pegando um filtro para verificar os valores
        Filter<Set<Set<Integer> > > filter
                = new Filter<Set<Set<Integer> > >() {
            // Agora obtendo correspondências de função booleana

            // Esta função ajuda a iterar todos os valores
            // sobre a variável inetegrs que adiciona
            // tudo isso para um sindicato que dará
            // nos o resultado desejado
            public boolean matches(
                    Set<Set<Integer> > integers)
            {
                Set<Integer> union
                        = new LinkedHashSet<Integer>();

                // Iterando usando for-each loop
                for (Set<Integer> ints : integers)
                    union.addAll(ints);

                return union.equals(sol);
            }
        };

        // Agora o conjunto abaixo chamará o combo curto
        // função Esta função irá ordenar o mais curto
        Set<Set<Integer> > firstSol
                = shortcombo(filter, sets);

        // Imprima e exiba o mesmo
        System.out.println("A combinação curta foi: "
                + firstSol);
    }
}
