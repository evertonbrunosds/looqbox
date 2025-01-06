# AVISO
ELABOREI [PULL REQUESTS](https://github.com/evertonbrunosds/looqbox/pulls?q=is%3Apr+is%3Aclosed) COMO FORMA DE DOCUMENTAR O DESENVOLVIMENTO DO PROJETO, COM ISSO, RECOMENDO A LEITURA DOS MESMOS COMO FONTE ESCLARECEDORA DE EVENTUAIS DÚVIDAS, SEGUE ABAIXO AS DEMAIS SESSÕES.

----

## Crie um diagrama explicando sua arquitetura
![Diagrama sem nome drawio (1)](https://github.com/user-attachments/assets/2106333b-b329-4a87-8f1c-065ad9b8e2f2)

----

## Identifique pontos de gargalo no código (se houver) e sugira uma solução.
### Obtenção e Deserialização
- O primeiro foi identificado no processo de obtenção e deserialização dos dados presentes no endpoint [pokemon-species](https://pokeapi.co/api/v2/pokemon-species). Para isso, a solução tomada foi o uso de cache na camada de serviço, conforme descrito nos PRs abaixo:
1. > [Implementação da Camada de Serviço](https://github.com/evertonbrunosds/looqbox/pull/8)
2. > [Refatoração do Cache da Camada de Serviço](https://github.com/evertonbrunosds/looqbox/pull/9)
3. > [Refatoração do Cache nas Camadas de Serviço e Controle para ser Configurado via Variáveis de Ambiente](https://github.com/evertonbrunosds/looqbox/pull/15)

### Busca, Ordenação e Manipulação de Strings
- O segundo foi identificado no processo de busca e ordenação tanto no endpoint `pokemons`, quanto no endpoint `pokemons/highlight`, indo além, este último possui um agravante: a manipulação de strings. Para isso, a solução tomada foi o uso de cache na camada de controle, conforma descrito nos PRs abaixo:
1. > [Implementação da Camada de Controle](https://github.com/evertonbrunosds/looqbox/pull/12)
2. > [Refatoração do Cache nas Camadas de Serviço e Controle para ser Configurado via Variáveis de Ambiente](https://github.com/evertonbrunosds/looqbox/pull/15)

----

## Explique o Big-θ dos algoritmos de ordenação
Os detalhes sobre a implementação dos algoritmos de ordenação usados no projeto podem ser observados nos PRs abaixo:
1. > [Documentação do Algoritmo de Ordenação Quick Sort](https://github.com/evertonbrunosds/looqbox/pull/2)
2. > [Implementação do Algoritmo de Ordenação Quick Sort](https://github.com/evertonbrunosds/looqbox/pull/3)
3. > [Documentação do Algoritmo de Ordenação Merge Sort](https://github.com/evertonbrunosds/looqbox/pull/4)
4. > [Substituição do Algoritmo Quick Sort pelo Merge Sort Visando o Aumento de Performasse](https://github.com/evertonbrunosds/looqbox/pull/5)
5. > [Correção de NullPointerException em Ordenações MergeSort](https://github.com/evertonbrunosds/looqbox/pull/13)

Além disso, existem diversos tipos de notação Big-O que descrevem o comportamento de algoritmos em termos de sua eficiência. Aqui estão alguns deles:

### 1. **(O(1)) - Tempo constante**
   - O tempo de execução não varia com o tamanho da entrada. Exemplo: Acesso direto a um elemento de um array pelo índice.

### 2. **(O(log N)) - Tempo logarítmico**
   - O tempo de execução cresce proporcionalmente ao logaritmo do tamanho da entrada. Exemplo: Busca binária.

### 3. **(O(N log N)) - Tempo quase linear**
   - O tempo de execução é proporcional ao tamanho da entrada multiplicado pelo logaritmo do tamanho. Exemplo: Algoritmos eficientes de ordenação, como mergesort e heapsort.

### 4. **(O(N²)) - Tempo quadrático**
   - O tempo de execução cresce proporcionalmente ao quadrado do tamanho da entrada. Exemplo: Ordenação por inserção ou seleção em seu pior caso.

### 5. **(O(N³)) - Tempo cúbico**
   - O tempo de execução cresce proporcionalmente ao cubo do tamanho da entrada. Exemplo: Algoritmos básicos para multiplicação de matrizes.

### 6. **(O(2<sup>N</sup>)) - Tempo exponencial**
   - O tempo de execução dobra a cada incremento do tamanho da entrada. Exemplo: Resolver problemas da abordagem de força bruta em algoritmos de conjuntos (como o problema do caixeiro-viajante).

### 7. **(O(N!)) - Tempo fatorial**
   - O tempo de execução cresce com o fatorial do tamanho da entrada. Exemplo: Algoritmos que geram todas as permutações possíveis de um conjunto.

### 8. **(O(&radic;N)) - Raiz quadrada**
   - O tempo de execução é proporcional à raiz quadrada do tamanho da entrada. Exemplo: Alguns algoritmos baseados em fatoração.

### 9. **(O(log log N)) - Logaritmo iterado**
   - Muito eficiente, comum em algoritmos que dividem repetidamente a entrada, como em algumas variações de buscas.
