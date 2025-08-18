package sptech.projeto03;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroisController {
/*
Para gerar as propriedades do JSON, o Spring Boot usa
os getters
 */
    private List<Heroi> herois = new ArrayList<>(List.of(
        new Heroi("Saitama", "super força", 100_000.0, false),
        new Heroi("Batman", "super riqueza", 5.0, true),
        new Heroi("Mulher Maravilha", "super força", 3_000.0, false)
    ));

    @GetMapping // URI: /herois
        // aqui definimos um Parâmetro de Requisição (request param) no final da URL, acrescentamos: ?nome=blablabla
        // por padrão, um requestparam é OBRIGATÓRIO
    public ResponseEntity<List<Heroi>> getHerois(@RequestParam(required = false) String nome) {
        if (nome != null) {
            var encontrados = herois.stream().filter(heroi -> heroi.getNome().contains(nome)).toList();

            return (encontrados.isEmpty()) ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(encontrados);
        }

        if (herois.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(herois);
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Heroi> getHeroi(@PathVariable int indice) {
        if (indice >= herois.size() || indice < 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(herois.get(indice));
    }

    /*
    @RequestBody -> indica que espera-se um JSON
    no corpo de requisição compatível com a classe do parâmetro
    (no caso Heroi)
     */
    @PostMapping // URI: /herois
    public ResponseEntity<Heroi> cadastrarHeroi(@RequestBody Heroi novoHeroi) {
        herois.add(novoHeroi);
        return ResponseEntity.status(201).body(novoHeroi);
    }

    /*
GET /herois/aposentados
Ele retorna um array de JSONs com todos os heróis aposentados
(aposentado = true)
     */
    @GetMapping("/aposentados")
    public ResponseEntity<List<Heroi>> aposentados() {
        // solução com stream
        // return herois.stream().filter(Heroi::isAposentado).toList();

        // solução clássica
        List<Heroi> list = new ArrayList<>();
        for (Heroi heroi : herois) {
            if (heroi.isAposentado()) {
                list.add(heroi);
            }
        }

        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(list);
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deleteHeroi(@PathVariable int indice) {
        if (indice >= herois.size() || indice < 0) {
            return ResponseEntity.status(404).build();
        }

        herois.remove(indice);
        return ResponseEntity.status(204).build();
    }
}
