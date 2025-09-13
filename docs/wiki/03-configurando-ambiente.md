# 03. Configurando o ambiente

Objetivo: deixar seu computador pronto para compilar e executar o projeto.

## 1) Instalar o JDK 17+
- Baixe uma distribuição LTS (Ex.: Temurin/Adoptium, Oracle JDK).
- Após instalar, verifique:
```
java -version
javac -version
```
Ambos devem responder com versão 17 ou superior.

## 2) Instalar o Maven 3.8+
- Verifique com: `mvn -version`.
- Se usar VS Code com Java Extension Pack, o Maven é integrado na extensão, mas o CLI é prático.

## 3) VS Code (opcional, recomendado)
- Instale o Java Extension Pack (Microsoft) para ter suporte a Java, Maven e execução direta.

## 4) Testar o build do projeto
Na raiz do projeto, execute:
```
mvn -q -DskipTests package
```
Se compilar sem erros, o ambiente está ok.

## 5) Executar uma classe principal (exemplo)
```
mvn -q exec:java -Dexec.mainClass=com.profluizricardomantovani.labjdbc.ConexaoDB
```
Troque o nome da classe conforme os exercícios listados no README.

