# 🚀 Escalonador com Listas de Prioridade e Prevenção de Inanição

<div align="center">

![Java](https://img.shields.io/badge/Java-8+-orange?style=flat-square&logo=java)
![Status](https://img.shields.io/badge/Status-Concluído-success?style=flat-square)
![Commits](https://img.shields.io/github/last-commit/jfplandim/Escalonador_De_Processos?style=flat-square)

**Sistema Operacional iCEVOS - Escalonador de Processos**

</div>

---

## 📋 Informações do Projeto

**Disciplina:** Algoritmos e Estrutura de Dados I  
**Professor:** Dimmy Magalhães  
**Período:** 2025.2

### 👥 Equipe de Desenvolvimento
| Nome | Matrícula |
|------|-----------|
| José Francisco Paes Landim Sobrinho | 0030574 |
| João Guilherme Aragão Malta | 0030617 |
| Ricardo Cronemberger Cruz Ruben Pereira | 0030620 |

**🔗 Repositório:** [https://github.com/jfplandim/Escalonador_De_Processos](https://github.com/jfplandim/Escalonador_De_Processos)

---

## 📖 Descrição do Projeto

Este projeto implementa o **escalonador de processos** do sistema operacional fictício **iCEVOS**, desenvolvido como parte da disciplina Algoritmos e Estrutura de Dados I. O escalonador é responsável por decidir qual processo terá acesso à CPU em cada ciclo, considerando:

- 🎯 **Listas de prioridade** (alta, média e baixa)
- 🛡️ **Prevenção de inanição** (*starvation*)
- 🔒 **Gerenciamento de recursos** (processos bloqueados por DISCO)
- ⚡ **Execução em tempo real** com logs detalhados

---

## 🎯 Objetivos do Projeto

- ✅ **Simular escalonador real** utilizando estruturas de dados implementadas do zero
- ✅ **Aplicar conceitos práticos** de listas duplamente encadeadas e circulares
- ✅ **Implementar anti-inanição** garantindo execução justa de todos os processos
- ✅ **Controlar recursos externos** movendo processos entre filas de bloqueio
- ✅ **Proibição total** de bibliotecas prontas (`ArrayList`, `LinkedList`, `Queue`, etc.)

---

## ⚙️ Principais Funcionalidades

### 🎪 Sistema de Prioridades Inteligente
- **Alta Prioridade (1):** Processos críticos executados primeiro
- **Média Prioridade (2):** Processos importantes do usuário
- **Baixa Prioridade (3):** Processos em background

### 🛡️ Prevenção de Inanição
- **Regra dos 5:** Após 5 processos de alta prioridade consecutivos, **obrigatoriamente** executa um de média/baixa
- **Contador inteligente:** Rastreia execuções seguidas de alta prioridade
- **Garantia matemática:** Nenhum processo fica esperando indefinidamente

### 🔒 Gerenciamento de Recursos
- **Bloqueio por DISCO:** Processos que precisam de E/S são automaticamente bloqueados
- **Fila FIFO de desbloqueio:** Primeiro bloqueado é o primeiro liberado
- **Retorno inteligente:** Processo volta para sua fila de prioridade original

### 🔄 Execução Circular (Round-Robin)
- **Time-slice unitário:** Cada processo executa por exatamente 1 ciclo
- **Preempção automática:** Processos não finalizados voltam ao fim da fila
- **Justiça interna:** Execução equilibrada dentro da mesma prioridade

---

## 📊 Entrada e Saída

### 📥 **Entrada**
**Arquivo:** `processos_1000.txt` (já incluído no repositório)
```
Formato: arquivo de configuração (.txt) com lista de processos
Estrutura: Uma linha por processo com parâmetros separados
```

### 📤 **Saída**
**Console:** Logs detalhados mostrando:
- ✅ **Filas de prioridade** (alta, média e baixa) a cada ciclo
- ✅ **Lista de processos bloqueados** esperando recursos
- ✅ **Processo em execução** no ciclo atual
- ✅ **Eventos críticos:** desbloqueios e finalizações de processos
- ✅ **Estado completo do sistema** após cada operação

**Exemplo de saída:**
```
CICLO: 15
Tentando executar: Processo[7, P7, Prioridade=2]
Executado: Processo[7, P7, Prioridade=2] (Ciclos restantes: 3)

--- ESTADO DO SISTEMA ---
Lista alta prioridade
P1[2 ciclos], P4[1 ciclo]
Lista media prioridade  
P7[3 ciclos], P9[5 ciclos]
Lista baixa prioridade
P12[8 ciclos], P15[4 ciclos]
Lista bloqueados
P6[DISCO], P11[DISCO]
```

---

## 🚀 Instruções de Execução

### 📋 **1. Verificar Ambiente**
```bash
# Verificar instalação do Java
java -version

# Se não estiver instalado:
# Windows: Adicionar pasta JDK/bin ao PATH
# Linux/Mac: export PATH=$PATH:/caminho/para/jdk/bin
```

### 📥 **2. Obter o Projeto**
```bash
# Clone o repositório
git clone https://github.com/jfplandim/Escalonador_De_Processos.git
cd Escalonador_De_Processos
```

### 🔨 **3. Compilar o Projeto**
```bash
# Entre na pasta src
cd src

# Compile todas as classes Java
javac *.java

# Volte para a pasta raiz
cd ..
```

### ▶️ **4. Executar a Simulação**
```bash
# Execute com o arquivo de teste incluído (1000 processos)
java -cp src Main processos_1000.txt

# Ou crie seu próprio arquivo de processos
java -cp src Main meu_arquivo.txt
```

---

## 🏗️ Arquitetura do Sistema

### 📦 Estrutura de Classes

```
src/
├── 🔧 Main.java              # Classe principal e interface do usuário
├── ⚙️ Scheduler.java          # Núcleo do escalonador com toda a lógica  
├── 📋 Processos.java          # Classe representando um processo
├── 🔗 ListaDeProcessos.java   # Lista duplamente encadeada customizada
├── 🔄 ListaCircular.java      # Lista circular para round-robin
└── 📦 No.java                 # Nó das estruturas de dados encadeadas
```

### 🔄 Fluxo de Execução

```
[Novo Processo] → [Verificar Prioridade] → [Lista Correspondente]
                                              ↓
[Scheduler] ← [Regra Anti-inanição] ← [Seleção do Próximo]
    ↓
[Precisa DISCO?] → [SIM] → [Lista Bloqueados] → [Desbloqueio]
    ↓                                               ↑
   [NÃO]                                          ↓
    ↓                                        [Retorna para]
[Executar] → [Terminou?] → [SIM] → [Finalizar]   [Lista Original]
    ↓             ↓
    ↓           [NÃO]
    ↓             ↓
    ↓      [Volta para Lista] → [Round-Robin]
    ↓             ↑                 ↓
    └─────────────┴─────────────────┘
```

---

## 🧪 Casos de Teste

### 📊 **Arquivo Incluído**
- **`processos_1000.txt`** - Teste completo com 1000 processos
- **Cenários cobertos:**
    - ✅ Processos de todas as prioridades
    - ✅ Processos que necessitam DISCO
    - ✅ Diferentes durações de ciclo
    - ✅ Teste de stress para performance

### 🔬 **Cenários Validados**
- 🛡️ **Anti-inanição:** Processos de baixa prioridade são executados após regra dos 5
- 🔒 **Bloqueio/Desbloqueio:** Processos que precisam de DISCO são gerenciados corretamente
- 🔄 **Round-Robin:** Execução justa dentro de cada nível de prioridade
- ⚡ **Performance:** Sistema suporta grandes volumes de processos

---

## 📈 Análise de Performance

### ⏱️ **Complexidade Temporal**
| Operação | Complexidade | Justificativa |
|----------|--------------|---------------|
| Inserir processo | O(1) | Inserção no final da lista |
| Remover processo | O(1) | Remoção do início da lista |
| Selecionar próximo | O(1) | Acesso direto por ponteiros |
| Ciclo completo | O(1) | Operações constantes |

### 💾 **Complexidade Espacial**
- **Geral:** O(n) onde n = número total de processos
- **Por estrutura:** Cada processo ocupa espaço constante
- **Overhead:** Mínimo devido a implementação com ponteiros

### 🚀 **Performance Estimada**
- **1000 processos:** ~10-15 segundos
- **Limitado por:** Operações de console (I/O)
- **Otimização:** Sistema de buffer para grandes volumes

---

## 🛡️ Garantias do Sistema

- ✅ **Ausência de Deadlock:** Não há dependências circulares entre recursos
- ✅ **Ausência de Starvation:** Regra matemática garante execução de todos
- ✅ **Fairness:** Round-robin assegura justiça dentro de cada prioridade
- ✅ **Bounded Waiting:** Tempo máximo de espera é calculável e finito
- ✅ **Consistência:** Estado sempre válido após cada operação

---

## 🔍 Destaques Técnicos

### 🏆 **Implementação 100% Original**
- **Zero bibliotecas prontas:** Todas as estruturas implementadas do zero
- **Ponteiros manuais:** Controle total sobre alocação e navegação
- **Algoritmos próprios:** Lógica de escalonamento desenvolvida pela equipe

### ⚡ **Otimizações Implementadas**
- **Acesso O(1):** Ponteiros diretos para início/fim das listas
- **Memória eficiente:** Reutilização de nós quando possível
- **Lógica otimizada:** Decisões baseadas em comparações mínimas

### 🎯 **Conformidade Acadêmica**
- ✅ Atende **100%** dos requisitos do trabalho
- ✅ Implementa **todas** as regras de escalonamento
- ✅ Fornece **logs detalhados** conforme especificado
- ✅ **Performance adequada** para volumes de teste

---

## 🐛 Troubleshooting

### ❗ **Problemas Comuns e Soluções**

#### Java não encontrado
```bash
# Erro: 'java' is not recognized
# Solução: Verificar instalação
where java  # Windows
which java  # Linux/Mac

# Adicionar ao PATH se necessário
```

#### Erro de compilação
```bash
# Erro: cannot find symbol
# Solução: Compilar todas as classes juntas
cd src
javac *.java
```

#### Arquivo não encontrado
```bash
# Erro: FileNotFoundException  
# Solução: Verificar localização do arquivo
ls -la processos_1000.txt  # Linux/Mac
dir processos_1000.txt     # Windows
```

#### Saída truncada
```bash
# Problema: Console não mostra tudo
# Solução: Redirecionar para arquivo
java -cp src Main processos_1000.txt > saida.txt
```

---

## 📚 Documentação Técnica

### 📖 **Recursos de Estudo**
- 📄 **Código comentado:** Todas as classes possuem documentação inline
- 🎨 **Arquitetura visual:** Diagramas de fluxo e estrutura
- 📊 **Análise de algoritmos:** Complexidade e justificativas técnicas
- 🔬 **Casos de teste:** Cenários validados e resultados esperados

### 🎓 **Referências Acadêmicas**
- **Livro-texto:** Estruturas de Dados e Algoritmos
- **Sistemas Operacionais:** Conceitos de escalonamento
- **Teoria das Filas:** Aplicações em ciência da computação
- **Engenharia de Software:** Boas práticas de desenvolvimento

---

## 🏆 Resultados Esperados

### ✅ **Para o Professor**
- Demonstração clara do domínio das estruturas de dados
- Implementação correta de todos os requisitos
- Código bem estruturado e documentado
- Logs detalhados facilitando a avaliação

### ✅ **Para a Equipe**
- Experiência prática com estruturas de dados complexas
- Compreensão profunda de algoritmos de escalonamento
- Desenvolvimento de habilidades de trabalho em equipe
- Base sólida para disciplinas avançadas

---

## 📄 Licença e Uso

**📚 Projeto Acadêmico** desenvolvido para a disciplina Algoritmos e Estrutura de Dados I do Instituto ICEV.

**⚠️ Importante:** Este código é fornecido exclusivamente para fins educacionais. Respeite as políticas de integridade acadêmica de sua instituição.

---

<div align="center">

**🎓 Desenvolvido com dedicação pela equipe iCEVOS**

*José Francisco • João Guilherme • Ricardo Cronemberger*

**Instituto ICEV - AED I - 2025.2**

---

[![GitHub](https://img.shields.io/badge/GitHub-jfplandim-black?style=flat-square&logo=github)](https://github.com/jfplandim/Escalonador_De_Processos)
[![Java](https://img.shields.io/badge/Linguagem-Java-orange?style=flat-square&logo=java)](https://www.java.com)
[![AED](https://img.shields.io/badge/Disciplina-AED%20I-blue?style=flat-square)](https://icev.edu.br)

</div>
   
