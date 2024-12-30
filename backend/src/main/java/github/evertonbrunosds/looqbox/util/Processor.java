package github.evertonbrunosds.looqbox.util;

/**
 * Interface funcional responsável por abstrair o processamento de um parâmetro de entrada.
 *
 * @param I Refere-se ao tipo do parâmetro de entrada.
 * @param O Refere-se ao tipo de retorno obtido por meio do parâmetro de entrada.
 * @author Everton Bruno Silva dos Santos
 */
@FunctionalInterface
public interface Processor<I, O> {

    /**
     * Método responsável por executar o processamento do parâmetro de entrada.
     *
     * @param inputParam Refere-se ao parâmetro de entrada.
     * @return Retorna o resultado obtido por meio do processamento do parâmetro de entrada.
     */
    public O perform(final I inputParam);

}
