package sptech.projeto02;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @RestController
@RequestMapping("/herois")
public class HeroisController {
/*
Para gerar as propriedades do JSON, o Spring Boot usa os getters
 */
    private List<Heroi> herois = new ArrayList<>(List.of(
            new Heroi("Saitama", "Super Força", 100_000.0, false),
            new Heroi("Batman", "Rico", 500.0, true),
            new Heroi("Mulher Maravilha", "Super Força", 10_000.0, false))
    );

    @GetMapping("/listar")
    public List<Heroi> getHerois() {
        return herois;
    }

    @GetMapping("/cadastrar/{nome}/{poderes}/{forca}/{aposentado}")
    public Heroi cadastrar(
            @PathVariable String nome,
            @PathVariable String poderes,
            @PathVariable Double forca,
            @PathVariable boolean aposentado
    ) {
        Heroi novo_heroi = new Heroi(nome, poderes, forca, aposentado);
        herois.add(novo_heroi);
        return novo_heroi;
    }
}
