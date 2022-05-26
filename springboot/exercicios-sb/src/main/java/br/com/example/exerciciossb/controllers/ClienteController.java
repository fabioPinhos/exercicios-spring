package br.com.example.exerciciossb.controllers;

import br.com.example.exerciciossb.model.entities.Cliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @RequestMapping(path = "/qualquer")
    public Cliente obterClienteCliente(){
        return new Cliente(28, "Pedro", "192.884.618-17");
    }

    @GetMapping("/{id}")
    public Cliente obterClientePorId1(@PathVariable int id){
        return new Cliente(id, "Maria", "192.884.618-17");
    }

    @GetMapping
    public Cliente obterClientePorId2(@RequestParam(name = "id", defaultValue = "1") int id){
        return new Cliente(id, "João", "111.621.333-44");
    }
}
