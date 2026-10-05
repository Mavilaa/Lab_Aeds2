# Lab_Aeds2

![testes](https://github.com/mavilaa/Lab_Aeds2/actions/workflows/testes.yml/badge.svg)

Exercícios do laboratório de Algoritmos e Estruturas de Dados II (PUC Minas).
Enunciado do TP1 em [`TP_01a/tp1.pdf`](TP_01a/tp1.pdf).

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
