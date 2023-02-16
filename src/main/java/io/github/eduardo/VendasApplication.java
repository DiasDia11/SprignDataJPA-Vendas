package io.github.eduardo;

import io.github.eduardo.domain.entity.Cliente;
import io.github.eduardo.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando");
            clientes.salva(new Cliente("Eduardo"));
            clientes.salva(new Cliente("Outro Cliente"));

            System.out.println("Listando");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            System.out.println("Buscando");
            clientes.buscarPorNome("Eduardo").forEach(System.out::println);

            System.out.println("Buscando");
            clientes.obterTodos().forEach(c-> {
                clientes.deletar(c);
            });

            System.out.println("Listando Denovo");
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum encontrado");
            }else{
                todosClientes.forEach(System.out::println);
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}