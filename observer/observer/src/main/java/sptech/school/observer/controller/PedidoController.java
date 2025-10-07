package sptech.school.observer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.observer.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    //Criar o pedido
    @PostMapping("/criar")
    public String criarPedido(@RequestParam String numero){
        pedidoService.criarPedido(numero);
        return "Pedido criado com sucesso!";
    }
}
