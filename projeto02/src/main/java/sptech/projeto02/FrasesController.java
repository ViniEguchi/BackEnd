package sptech.projeto02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController -> indica que a classe pode possuir
funcionalidades da API
 */
@RestController
public class FrasesController {

    /*
@GetMapping -> Indica que o método é uma funcionalidade da API
"/cumprimentar" -> Indica a URI
     */
    @GetMapping("/cumprimentar") // localhost:8080/cumprimentar
    public String cumprimentar() {
        return "É nóis no REST";
    }

    // URI: boa-noite
    // URIs são, por convensão, kebab-case (hifen-case ou css cases)
    @GetMapping("/boa-noite")
    public String boaNoite() {
        return "Tenha uma boa noite";
    }

    // Os métodos que são funcionalidades da API
    // podem retornar QUALQUER coisa
    @GetMapping("/numero-favorito")
    public int numFavorito() {
        return 7;
    }

    /*
Aqui usamos o recurso de PATH PARAM
{nomeProduto} ficou mapeado para o parâmetro de mesmo nome
devido ao uso da anotação @PathVariable
     */
    @GetMapping("/produto/{nomeProduto}")
    public String descrever(@PathVariable String nomeProduto) {
        return "Produto %s cadastrado".formatted(nomeProduto);
    }

    @GetMapping("/somar/{n1}/{n2}")
    public String somar(@PathVariable Double n1,
                        @PathVariable Double n2) {
        return "A soma entre %.1f e %.1f é %.1f"
                .formatted(n1, n2, (n1+n2));
    }

    @GetMapping("/repetir/{vezes}/{frase}")
    public String repetir(@PathVariable int vezes,
                        @PathVariable String frase) {
        /*String resultado = "";
        for (int i = 0; i < vezes; i++) {
            resultado += frase + " ";
        }
        return resultado;*/
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < vezes; i++) {
            resultado.append(frase + " ");
        }
        return resultado.toString();
    }



}
