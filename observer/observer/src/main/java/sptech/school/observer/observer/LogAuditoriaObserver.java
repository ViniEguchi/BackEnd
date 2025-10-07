package sptech.school.observer.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sptech.school.observer.evento.PedidoCriadoEvento;

@Component
public class LogAuditoriaObserver {
    @EventListener
    public void registraAuditoria(PedidoCriadoEvento evento) {
        System.out.println("Log de auditoria: " + evento.getNumeroPedido());
    }
}
