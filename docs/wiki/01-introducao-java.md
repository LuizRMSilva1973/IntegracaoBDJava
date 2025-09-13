# 01. Introdução ao Java: JDK, JRE e JVM

Esta seção explica os termos essenciais para começar em Java.

## O que é Java?
- Java é uma linguagem de programação e também um ecossistema (bibliotecas, ferramentas, runtime) amplamente usado no mercado.
- O código Java é compilado para bytecode (arquivos `.class`), que roda em uma máquina virtual (JVM). Isso facilita portabilidade: o mesmo programa roda em Windows, Linux e macOS.

## JDK, JRE e JVM
- JVM (Java Virtual Machine)
  - É a “máquina” que executa o bytecode Java.
  - Faz otimizações (JIT) e gerenciamento de memória (coletor de lixo).
- JRE (Java Runtime Environment)
  - Conjunto mínimo para executar aplicações Java: inclui a JVM e bibliotecas padrão.
  - Hoje, o JRE veio “junto” nas distribuições do JDK moderno. Quando você instala o JDK, já consegue rodar programas.
- JDK (Java Development Kit)
  - Kit para desenvolvimento: inclui o compilador (`javac`), a JVM e as bibliotecas padrão.
  - É o que você precisa instalar para programar em Java.

Resumo prático: instale o JDK para compilar e rodar; a JVM está dentro dele.

## Versões recomendadas
- Este laboratório foi testado com Java 17+. Use uma distribuição LTS (por exemplo: Temurin/Adoptium ou Oracle JDK).
- Verifique no terminal: `java -version` e `javac -version`.

## Como um programa Java roda (alto nível)
1) Você escreve código `.java`.
2) O `javac` compila para `.class` (bytecode).
3) A JVM carrega esses `.class` e executa.

No nosso projeto, o Maven ajuda a compilar e executar as classes principais (método `main`).

