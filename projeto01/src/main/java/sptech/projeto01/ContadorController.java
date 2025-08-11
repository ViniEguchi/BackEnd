package sptech.projeto01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
A Rest Controller são SINGLETONs num projeto Spring Boot
 */
@RestController
public class ContadorController {

    int contador = 0;

    @GetMapping("/contar")
    public int getContador() {
        return ++contador;
    }
}
