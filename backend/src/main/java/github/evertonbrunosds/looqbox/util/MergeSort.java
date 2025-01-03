package github.evertonbrunosds.looqbox.util;

import static java.lang.Integer.compare;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por ordenar elementos de coleções através do algoritmo
 * MergeSort que possui complexidade O(n log n). Uma característica relevante
 * sobre a classe em questão, é sua capacidade de ordenar qualquer tipo de dados
 * da forma como o desenvolvedor bem quiser, ou seja, pretende ordenar uma
 * coleção composta por objetos do tipo Z, Y ou Z por meio de algum de seus
 * atributos? Sim, isso é possível através do comparador genérico que em síntese
 * é o padrão de projeto abstract method do ponto de vista criacional, porém do
 * ponto de vista comportamental, por visitar elementos de uma coleção, acaba
 * também por atuar como o padrão de projeto visitor.
 *
 * @param T Refere-se ao tipo genérico dos elementos ordenados.
 * @author Everton Bruno Silva dos Santos
 * @see <a href="https://github.com/evertonbrunosds/looqbox/pull/5">Para mais informações, leia o Pull Request presente no GitHub</a>
 */
public class MergeSort<T> {

    /**
     * Refere-se ao comparador que lida com as comparações em alto nível, ou
     * seja, de forma genérica gracas ao uso do padrão de projeto abstract method.
     * Sua implementação dinâmica, lhe permite alterar o modo como as comparações
     * irão ocorrer independente do tipo em questão, como resultado disso, se torna
     * possível ordenar qualquer tipo de dados de qualquer forma imaginável.
     */
    private final Comparer<T, T, Integer> comparer;

    /**
     * Construtor responsável por instanciar a classe de ordenação MergeSort sem uso
     * de parâmetros, desse modo, as comparações irão ocorrer conforme já ocorrem
     * naturalmente no Java.
     */
    public MergeSort() {
        this((leftElement, rightElement) -> {
            return compare(leftElement.hashCode(), rightElement.hashCode());
        });
    }

    /**
     * Construtor responsável por instanciar a classe de ordenação MergeSort com
     * uso de parâmetros, desse modo, as comparações irão ocorrer conforme a
     * implementação do comparador fornecido, ou seja, o modo como as comparações
     * irão ocorrer é estabelecida durante o instanciamento graças ao padrão de
     * projeto abstract method.
     *
     * @param comparer Refere-se ao comparador que lida com as comparações em alto
     *                 nível, ou seja, de forma genérica. Sua implementação
     *                 dinâmica, lhe permite alterar o modo como as comparações irão
     *                 ocorrer independente do tipo em questão, como resultado
     *                 disso, se torna possível ordenar qualquer tipo de dados de
     *                 qualquer forma imaginável.
     */
    public MergeSort(final Comparer<T, T, Integer> comparer) {
        this.comparer = (leftElement, rightElement) -> {
            return leftElement != null && rightElement != null
                    ? comparer.compare(leftElement, rightElement)
                    : leftElement == null && rightElement != null
                            ? -1
                            : leftElement != null && rightElement == null
                                    ? 1
                                    : 0;
        };
    }

    /**
     * Método responsável por efetuar as ordenações usando o algoritmo MergeSort.
     *
     * @param list    Refere-se a coleção de dados a ser ordenada.
     * @param reverse Refere-se ao modo como a ordenação deve ocorrer, se deve ocorrer
     *                de forma ascendente (não reversa) ou descendente (reversa).
     */
    public void sort(final List<T> list, final boolean reverse) {
        final Comparer<T, T, Boolean> comparerMode = reverse
                ? (leftElement, rightElement) -> comparer.compare(leftElement, rightElement) > 0
                : (leftElement, rightElement) -> comparer.compare(leftElement, rightElement) < 0;
        split(list, comparerMode);
    }

    /**
     * Método responsável por efetuar de forma recursiva as divisões da lista,
     * criando assim sub-listas à direita e a esquerda.
     *
     * @param list         Refere-se a coleção de dados a ser dividida em sub-listas
     *                     à direita e a esquerda.
     * @param comparerMode Refere-se ao modo como a comparação deverá ocorrer,
     *                     podendo ser de forma ascendente (não reversa) ou
     *                     descendente (reversa).
     */
    private void split(final List<T> list, final Comparer<T, T, Boolean> comparerMode) {
        if (list.size() > 1) {
            final int middle = list.size() / 2;
            final List<T> leftList = new ArrayList<>(list.subList(0, middle));
            final List<T> rightList = new ArrayList<>(list.subList(middle, list.size()));
            split(leftList, comparerMode);
            split(rightList, comparerMode);
            merge(list, leftList, rightList, comparerMode);
        }
    }

    /**
     * Método responsável por efetuar a mesclagem dos elementos obtidos por meio das
     * listas à esquerda e a direita.
     *
     * @param list         Refere-se a coleção de dados a receber a mesclagem que dá
     *                     origem a ordenação.
     * @param leftList     Refere-se a sub-coleção de dados à direita.
     * @param rightList    Refere-se a sub-coleção de dados a esquerda.
     * @param comparerMode Refere-se ao modo como a comparação deverá ocorrer,
     *                     podendo ser de forma ascendente (não reversa) ou
     *                     descendente (reversa).
     */
    private void merge(final List<T> list, final List<T> leftList, final List<T> rightList, final Comparer<T, T, Boolean> comparerMode) {
        int leftIndex = 0, rightIndex = 0, index = 0;
        // Compara os elementos de ambas as listas e insere o menor na lista original
        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (comparerMode.compare(leftList.get(leftIndex), rightList.get(rightIndex))) {
                list.set(index++, leftList.get(leftIndex++));
            } else {
                list.set(index++, rightList.get(rightIndex++));
            }
        }
        // Insere os elementos restantes da lista esquerda (se houver)
        while (leftIndex < leftList.size()) {
            list.set(index++, leftList.get(leftIndex++));
        }
        // Insere os elementos restantes da lista direita (se houver)
        while (rightIndex < rightList.size()) {
            list.set(index++, rightList.get(rightIndex++));
        }
    }

}
