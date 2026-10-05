# Lab_Aeds2

![testes](https://github.com/mavilaa/Lab_Aeds2/actions/workflows/testes.yml/badge.svg)

Exercícios e trabalhos práticos de Algoritmos e Estruturas de Dados II (PUC Minas).
Os enunciados estão dentro de cada pasta (`tp1.pdf`, `tp2.pdf`).

## TP_01a

| Exercício | Linguagem | Recursivo |
|---|---|---|
| Alteração Aleatória | Java | |
| Ciframento (César) | Java | |
| CifraR | C | sim |
| Inversão de String | C | |
| Inversão de String RECURSIVO | Java | sim |
| Is | Java | |
| IsR | C | sim |
| Soma de Dígitos | C | |
| Soma R | Java | sim |
| Substring Mais Longa Sem Repetição | C | |
| Validação de Senha | Java | |
| Verificação de Anagrama | C | |

## TP_02

Todos usam a base de veículos do enunciado ([`TP_02/tp2.pdf`](TP_02/tp2.pdf)), lida de `/tmp/veiculos.csv` como no Verde.

| Exercício | Linguagem |
|---|---|
| Modelagem | C e Java |
| Pesquisa Binária | C |
| Ordenação por Seleção | C |
| Ordenação por Inserção | Java |
| Ordenação por Counting Sort | C |
| Ordenação por Radixsort | C |
| Ordenação por Bucketsort | Java |
| Lista com Alocação Sequencial | Java |
| Lista com Alocação Flexível | C |
| Lista Dupla com Alocação Flexível | Java |
| Pilha com Alocação Flexível | Java |
| Fila Circular com Alocação Sequencial | C |

Cada pasta tem o código, o `pub.in` (entrada) e o `pub.out` (saída esperada).

## Testar

```bash
gcc -o cesar TP_01a/CifraR/cesar.c
./cesar < TP_01a/CifraR/pub.in | diff - TP_01a/CifraR/pub.out
```

```bash
javac -d build TP_01a/Is/Is.java
java -cp build Is < TP_01a/Is/pub.in | diff - TP_01a/Is/pub.out
```

O GitHub Actions faz isso para todos os exercícios a cada push (badge lá em cima).
