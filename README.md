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

### **1. (O(1)): Espaço Constante**  
Embora (O(1)) seja uma notação de espaço, ela é destacada por sua eficiência em termos de uso de memória. Algoritmos que operam "in-place" não utilizam espaço auxiliar adicional significativo, tornando-os ideais em dispositivos com memória limitada.

- **Características:**  
  - Não consome espaço adicional além da lista original.  
  - É especialmente útil em dispositivos com memória restrita.

- **Exemplos de algoritmos de ordenação:**  
  - **Heap Sort:** Utiliza a estrutura de heap diretamente na lista.  
  - **Insertion Sort:** Realiza operações diretamente na lista sem criar cópias.  
  - **Selection Sort:** Ordena diretamente na lista sem alocação extra.

### **2. (O(n)): Complexidade Linear**  
Essa notação descreve algoritmos que percorrem a lista apenas uma vez ou com poucas passagens, sendo extremamente rápidos para listas específicas.

- **Características:**  
  - A eficiência depende de propriedades específicas da lista, como valores limitados ou uma distribuição uniforme.  
  - Não é aplicável para listas genéricas.

- **Exemplos de algoritmos de ordenação:**  
  - **Counting Sort:** Baseado na contagem de frequências.  
  - **Bucket Sort (em melhores condições):** Divide os elementos em baldes e os ordena individualmente.  
  - **Radix Sort:** Ordena números com base em seus dígitos.

### **3. (O(n + k)): Complexidade Linear Adicional**  
Essa notação expande a eficiência linear ao adicionar uma dependência de ( k ), como o intervalo máximo ou o número de dígitos nos valores. É eficiente quando ( k ) é pequeno em relação a ( n ).

- **Características:**  
  - Depende fortemente de ( k ), sendo eficiente para listas com valores inteiros em intervalos pequenos.  
  - Pode ser ineficiente para intervalos muito grandes.

- **Exemplos de algoritmos de ordenação:**  
  - **Counting Sort:** ( k ) representa o maior valor na lista.  
  - **Radix Sort:** ( k ) é o número de dígitos do maior número.  
  - **Bucket Sort:** ( k ) é o número de baldes usados para dividir os elementos.

### **4. (O(n log n)): Complexidade Log-Linear**  
Algoritmos com essa notação são altamente eficientes e frequentemente usados para listas grandes devido à sua consistência e escalabilidade.

- **Características:**  
  - Usam estratégias como "dividir e conquistar" para reduzir o número de operações.  
  - Aplicáveis a listas genéricas, independentemente da ordem inicial.  

- **Exemplos de algoritmos de ordenação:**  
  - **Merge Sort:** Divide e combina recursivamente as sublistas.  
  - **Heap Sort:** Usa a estrutura de heap para organizar os elementos.  
  - **Quick Sort:** Divide a lista em torno de um pivô e ordena recursivamente (no caso médio e no melhor caso).

### **5. (O(log n)): Complexidade Logarítmica (em Quick Sort)**  
Embora essa complexidade não se aplique ao tempo principal de execução dos algoritmos de ordenação, ela aparece no espaço auxiliar necessário para operações recursivas.

- **Características:**  
  - Surge da profundidade da pilha de chamadas recursivas em algoritmos como o Quick Sort.  
  - Representa uma eficiência significativa em termos de uso de memória.

- **Exemplo de algoritmo:**  
  - **Quick Sort (espaço auxiliar):** A profundidade máxima da recursão é (O(log n)).

### **6. (O(n²)): Complexidade Quadrática**  
Essa notação representa os algoritmos menos eficientes para listas grandes, pois a quantidade de operações cresce exponencialmente com o tamanho da lista.

- **Características:**  
  - Úteis para listas pequenas ou quase ordenadas.  
  - Geralmente simples de implementar, mas ineficientes para listas maiores.  

- **Exemplos de algoritmos de ordenação:**  
  - **Bubble Sort:** Compara e troca elementos adjacentes repetidamente.  
  - **Insertion Sort:** Insere elementos em suas posições corretas, um por vez.  
  - **Selection Sort:** Seleciona o menor elemento e o posiciona no início em cada iteração.
