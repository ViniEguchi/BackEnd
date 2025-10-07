package sptech.school.observer.evento;

import org.springframework.context.ApplicationEvent;

// Este é o evento que será disparado quando um pedido for criado
public class PedidoCriadoEvento extends ApplicationEvent {
    private final String numeroPedido;
    public PedidoCriadoEvento(Object source, String numeroPedido) {
        super(source);
        this.numeroPedido = numeroPedido;
    }
    public String getNumeroPedido() {return numeroPedido;}
}
