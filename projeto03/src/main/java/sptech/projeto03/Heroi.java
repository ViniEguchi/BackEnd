package sptech.projeto03;

public class Heroi {
    private String nome;
    private String poderes;

    // @JsonIgnore // essa anotação impede a geração do atributo no JSON e também que um objeto Java seja gerado com esse atributo a partir de um JSON
    private Double forca;

    private boolean aposentado;

    public Heroi(String nome, String poderes, Double forca, boolean aposentado) {
        this.nome = nome;
        this.poderes = poderes;
        this.forca = forca;
        this.aposentado = aposentado;
    }

    /*
Aqui criamos um campo Virtual ou Calculado
(atributo virtual ou calculado)
     */
    public Double getSalario() {
        return 15_000 * forca;
    }

    public String getNome() {
        return nome;
    }

    public String getPoderes() {
        return poderes;
    }

    public Double getForca() {
        return forca;
    }

    public boolean isAposentado() {
        return aposentado;
    }


}
