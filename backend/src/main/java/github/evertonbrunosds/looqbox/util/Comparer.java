package github.evertonbrunosds.looqbox.util;

/**
 * Interface funcional responsável por abstrair a comparação de dois tipos de entrada distintos.
 *
 * @param X Refere-se ao tipo do primeiro parâmetro de entrada.
 * @param Y Refere-se ao tipo do segundo parâmetro de entrada.
 * @param O Refere-se ao tipo de retorno obtido por meio dos dois parâmetros de entrada.
 * @author Everton Bruno Silva dos Santos
 */
@FunctionalInterface
public interface Comparer<X, Y, O> {

    /**
     * Método responsável por efetuar a comparação dos dois parâmetros de entrada.
     *
     * @param inputParamOne Refere-se ao primeiro parâmetro de entrada.
     * @param inputParamTwo Refere-se ao segundo parâmetro de entrada.
     * @return Retorna o resultado obtido por meio do processamento dos dois parâmetros de entrada.
     */
    public O compare(final X inputParamOne, final Y inputParamTwo);

}
