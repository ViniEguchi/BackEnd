package sptech.projeto07.model;

public class Teste {
    public static void main(String[] args) {
        // Os construtores são criados automaticamente para os Records
        Especie e1 = new Especie("1", "cachorro");
        Especie e2 = new Especie("1", "cachorro");

        // Os records possuem um toString implicito
        System.out.println(e1);

        // Os recors possuem um equals implicito
        System.out.println(e1.equals(e2));

        // Os Records são imutaveis
        System.out.println(e1.codigo());
        System.out.println(e1.nome());


        Raca r1 = new Raca("1", "goldem");
        Raca r2 = new Raca();

        System.out.println(r1.getNome());

        Raca r3 = Raca.builder()
                .codigo("1")
                .nome("labrador")
                .build();

        Raca.RacaBuilder r4 = Raca.builder();
        // acontece alguma coisa
        r4 = r4.codigo("99");
        // acontece outra coisa
        r4 = r4.nome("labrador");
        // fim. Agora cria o objeto
        Raca r4final = r4.build();
    }
}
