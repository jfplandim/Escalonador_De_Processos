# Escalonador com Listas de Prioridade e Prevenção de Inanição

**Disciplina:** Algoritmos e Estrutura de Dados I  
**Professor:** Dimmy Magalhães

**Integrantes:**
- José Francisco Paes Landim Sobrinho — Matrícula: 0030574
- João Guilhermme Aragão Malta — Matrícula: 0030617
- Ricardo Cronemberger Cruz Ruben Pereira — Matrícula: 0030620

**Link do repositório:** [https://github.com/jfplandim/Escalonador_De_Processos](https://github.com/jfplandim/Escalonador_De_Processos)

---

## Descrição do Projeto

Este projeto implementa o escalonador de processos do sistema operacional fictício **iCEVOS**, desenvolvido como parte da disciplina Algoritmos e Estrutura de Dados I.

O escalonador é responsável por decidir qual processo terá acesso à CPU em cada ciclo, considerando listas de prioridade (alta, média e baixa), prevenindo inanição (*starvation*) e gerenciando processos bloqueados que necessitam de recursos externos (como o **DISCO**).

---

## Objetivos do Projeto

- Simular o funcionamento de um escalonador real, utilizando estruturas de dados implementadas do zero (sem uso de bibliotecas prontas como `ArrayList`, `LinkedList`, `Queue`, etc.).
- Aplicar conceitos de **listas duplamente encadeadas** e **listas circulares** para o gerenciamento de processos.
- Implementar regras de **anti-inanição**, garantindo que processos de média e baixa prioridade também sejam executados.
- Controlar processos que necessitam de recursos externos, movendo-os entre filas de bloqueados e execução.

---

## Principais Funcionalidades

- **Escalonamento por prioridade:** processos de alta prioridade têm preferência, mas a cada 5 execuções seguidas, um processo de média/baixa prioridade deve ser escalonado.
- **Ciclos de execução:** cada execução reduz os ciclos necessários do processo; quando chegam a zero, o processo é finalizado.
- **Gestão de bloqueio e desbloqueio:** processos que solicitam o recurso `"DISCO"` são movidos para a lista de bloqueados e só retornam após liberação.
- **Execução circular:** processos não finalizados são reinseridos no fim da sua fila original.

---

## Entrada e Saída

**Entrada:**  
Arquivo de configuração (`.txt`) com a lista de processos.

**Saída:**  
Logs no console mostrando os ciclos, estado das listas, bloqueios, desbloqueios e finalizações. Inclui:
- Filas de prioridade (alta, média e baixa)
- Lista de processos bloqueados
- Processo em execução no ciclo
- Eventos de desbloqueio e finalização de processos

---

## Instruções de Compilação e Execução

1. **Verificar instalação do Java:**  
   Abra o terminal e digite:
   ```bash
   java -version
2. **Clone o Repositório:**
   ```bash
   git clone [URL_DO_SEU_REPOSITORIO]
   cd nome-do-repositorio

3. **Compile o Projeto:**
   ```bash
   cd src
   
4. **Execute o comando abaixo para compilar todas as classes do projeto de uma só vez:**
      ```bash
      javac *.java 
5. **Execute a Simulação:**

   **Volte para a pasta raiz do projeto (o nível acima da src).**
   ```bash
   cd ..
   
6. **Execute o programa, passando o arquivo de entrada (processos_1000.txt, que já está no repositório) como parâmetro.**
   ```bash
   java -cp src Main processos_1000.txt
   
