package sptech.projeto11.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentosService {

    @Scheduled(fixedRate = 3000) // Executa a cada 3 segundos
    public void enviarCuponsAniversario() {
        System.out.println("Enviando cupons aniversário...");
    }

    @Scheduled(cron = "0 25 17 * * 1")
    public void desafio() {
        System.out.println("Hey!");
    }
}
