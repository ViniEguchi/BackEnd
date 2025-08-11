package sptech.projeto02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
A Rest Controllers são SINGLETONs num projeto Spring Boot
Ou seja, é criada apenas 1(uma) instância de cada
 */
@RestController
public class ContadorController {

    int contador = 0;

    @GetMapping("/contar")
    public int getContador() {
        return ++contador;
    }
}
