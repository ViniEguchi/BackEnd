package sptech.school.observer.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sptech.school.observer.evento.PedidoCriadoEvento;

//Observador que irá enviar um e-mail
@Component
public class NotificacaoEmailObserver {
    @EventListener
    public void aoCriarPedido(PedidoCriadoEvento evento) {
        System.out.println("Enviando e-mail: Seu pedido "
                + evento.getNumeroPedido() + " Foi criado com sucesso!");
    }
}
