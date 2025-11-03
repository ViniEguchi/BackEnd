package sptech.projeto11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

// @EnableScheduling // habilita o agendamento de tarefas
@EnableCaching // habilita o cache
@SpringBootApplication
public class Projeto11Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto11Application.class, args);
	}

}
