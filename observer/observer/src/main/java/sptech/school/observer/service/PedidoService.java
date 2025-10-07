package sptech.school.observer.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import sptech.school.observer.evento.PedidoCriadoEvento;

@Service
public class PedidoService {
    private final ApplicationEventPublisher publisher;
    public PedidoService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public String criarPedido(String numero){
        // Aqui poderiamos salvar no BD, validar, etc..
        System.out.println("Pedido criado no sistema");

        //Dispara o evento para notificar
        publisher.publishEvent(new PedidoCriadoEvento(this, numero));
        return numero;
    }
}
