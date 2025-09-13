# 02. Orientação a Objetos — o básico necessário

Você não precisa dominar OO para este laboratório, mas ajuda conhecer as ideias-chave.

## Conceitos principais
- Classe: “molde” que define dados (atributos) e comportamentos (métodos).
- Objeto: instância de uma classe (um “exemplar” com valores reais).
- Encapsulamento: manter detalhes internos protegidos, expondo apenas o necessário (métodos públicos).
- Herança: uma classe pode herdar características de outra (não usamos ativamente aqui).
- Polimorfismo: objetos de diferentes classes podem ser usados por uma mesma interface (conceito mais adiante; não é exigido no lab).

## Exemplo simples
```java
class Cliente {
    String nome;
    String email;

    void imprimir() {
        System.out.println(nome + " <" + email + ">");
    }
}

public class Main {
    public static void main(String[] args) {
        Cliente c = new Cliente(); // objeto
        c.nome = "Maria";
        c.email = "maria@exemplo.com";
        c.imprimir();
    }
}
```

No nosso laboratório JDBC, você verá classes utilitárias que abrem conexão, executam SQL e mapeiam dados (quando usamos DAO). A ideia é separar responsabilidades: cada classe foca em uma tarefa.

