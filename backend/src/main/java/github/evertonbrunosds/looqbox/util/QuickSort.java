package github.evertonbrunosds.looqbox.util;

import static java.lang.Integer.compare;

import java.util.List;

/**
 * Classe responsável por ordenar elementos de coleções através do algoritmo
 * QuickSort que possui complexidade O(n log n). Uma característica relevante
 * sobre a classe em questão, é sua capacidade de ordenar qualquer tipo de dados
 * da forma como o desenvolvedor bem quiser, ou seja, pretende ordenar uma
 * coleção composta por objetos do tipo Z, Y ou Z por meio de algum de seus
 * atributos? Sim, isso é possível através do comparador genérico.
 *
 * @param T Refere-se ao tipo genérico dos elementos ordenados.
 * @author Everton Bruno Silva dos Santos
 */
public class QuickSort<T> {

    /**
     * Refere-se ao comparador que lida com as comparações em alto nível, ou
     * seja, de forma genérica. Sua implementação dinâmica, lhe permite alterar o
     * modo como as comparações irão ocorrer independente do tipo em questão, como
     * resultado disso, se torna possível ordenar qualquer tipo de dados de qualquer
     * forma imaginável.
     */
    private final Comparer<T, T, Integer> comparer;

    /**
     * Construtor responsável por instanciar a classe de ordenação QuickSort sem uso
     * de parâmetros, desse modo, as comparações irão ocorrer conforme já ocorrem
     * naturalmente no Java.
     */
    public QuickSort() {
        comparer = (final T o1, final T o2) -> compare(o1.hashCode(), o2.hashCode());
    }

    /**
     * Construtor responsável por instanciar a classe de ordenação QuickSort com uso
     * de parâmetros, desse modo, as comparações irão ocorrer conforme a
     * implementação do comparador fornecido, ou seja, o modo como as comparações
     * irão ocorrer é estabelecida durante o instanciamento.
     *
     * @param comparer Refere-se ao comparador que lida com as comparações em alto
     *                 nível, ou seja, de forma genérica. Sua implementação
     *                 dinâmica, lhe permite alterar o modo como as comparações irão
     *                 ocorrer independente do tipo em questão, como resultado
     *                 disso, se torna possível ordenar qualquer tipo de dados de
     *                 qualquer forma imaginável.
     */
    public QuickSort(final Comparer<T, T, Integer> comparer) {
        this.comparer = comparer;
    }

    /**
     * Método responsável por efetuar as ordenações usando o algoritmo QuickSort.
     *
     * @param list    Refere-se a coleção de dados a ser ordenada.
     * @param reverse Refere-se ao modo como a ordenação deve ocorrer, se deve
     *                ocorrer de forma ascendente (não reversa) ou descendente
     *                (reversa).
     */
    public void sort(final List<T> list, final boolean reverse) {
        final int low = 0;
        final int high = list.size() - 1;
        perform(list, low, high, reverse);
    }

    /**
     * Método responsável por efetuar a execução recursiva do algoritmo QuickSort.
     *
     * @param list    Refere-se a coleção de dados a ser ordenada.
     * @param low     Refere-se ao índice inicial da ordenação.
     * @param high    Refere-se ao índice final da ordenação.
     * @param reverse Refere-se ao modo como a ordenação deve ocorrer, se deve
     *                ocorrer de forma ascendente (não reversa) ou descendente
     *                (reversa).
     */
    private void perform(final List<T> list, final int low, final int high, final boolean reverse) {
        if (low < high) {
            final int pivotIndex = partition(list, low, high, reverse);
            perform(list, low, pivotIndex - 1, reverse);
            perform(list, pivotIndex + 1, high, reverse);
        }
    }

    /**
     * Método responsável por identificar e reorganizar os elementos da sub-lista em
     * relação ao pivô.
     *
     * @param list    Refere-se a coleção de dados a ser ordenada.
     * @param low     Refere-se ao índice final da ordenação.
     * @param high    Refere-se ao índice final da ordenação.
     * @param reverse Refere-se ao modo como a ordenação deve ocorrer, se deve
     *                ocorrer de forma ascendente (não reversa) ou descendente
     *                (reversa).
     * @return Retorna o índice do próximo pivô identificado.
     */
    private int partition(final List<T> list, final int low, final int high, final boolean reverse) {
        final T pivot = list.get(high);
        int i = low - 1;
        final Processor<Integer, Boolean> compareMode = reverse // estabelece o modo de comparação
                ? (index) -> comparer.compare(list.get(index), pivot) > 0 // compara se o elemento atual é maior que o pivô
                : (index) -> comparer.compare(list.get(index), pivot) < 0; // compara se o elemento atual é menor que o pivô
        for (int j = low; j < high; j++) {
            if (compareMode.perform(j)) { // compara o elemento atual com o pivô usando o modo de comparação anteriormente estabelecido
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Método responsável por efetuar a trocar dos elementos na lista.
     *
     * @param list Refere-se a coleção de dados a ser ordenada.
     * @param i    Refere-se ao índice 'i' do elemento que deve se trocado pelo
     *             elemento de índice 'j'.
     * @param j    Refere-se ao índice 'j' do elemento que deve se trocado pelo
     *             elemento de índice 'i'.
     */
    private void swap(final List<T> list, final int i, final int j) {
        final T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
