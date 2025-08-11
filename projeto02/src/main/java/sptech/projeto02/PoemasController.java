package sptech.projeto02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/poemas") // esta anotação indica que todos os endpoint tenham o mesmo inicio
public class PoemasController {
    private List<String> poemas = new ArrayList<>();

    @GetMapping("/lista") // /poemas/lista
    public List<String> getPoemas() {
        return poemas;
    }

    @GetMapping("/cadastrar/{poema}") // /poemas/cadastrar/{poema}
    public String cadastrar(@PathVariable String poema) {
        poemas.add(poema);
        return "Poema cadastrado com sucesso";
    }

    @GetMapping("/excluir/{indice}") // /poemas/excluir/{indice}
    public String excluir(@PathVariable int indice) {
        poemas.remove(indice);
        return "Poema excluido";
    }
}
