package br.com.example.exerciciossb.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    private int id;
    private String nome;
    private String cpf;

}
