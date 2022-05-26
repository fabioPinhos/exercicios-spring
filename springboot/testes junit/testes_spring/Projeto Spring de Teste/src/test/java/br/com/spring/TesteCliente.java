package br.com.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TesteCliente {

    @Test
    @DisplayName("Deve criar o cadastro de pessoas")
    public void deveCriarOCadastroDePessoas(){

        //cenario e execucao
        CadastroPessoas cadastro = new CadastroPessoas();

        //verificacao
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
    @DisplayName("Deve criar adicionar na lista de pessoas")
    public void deveAdicionarUmaPessoa(){

        //cenario
        CadastroPessoas cadastroPessoas = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Fabio");

        //execucao
        cadastroPessoas.adicionar(pessoa);

        Assertions
                .assertThat(cadastroPessoas.getPessoas())
                .isNotEmpty()
                .hasSize(1)
                .contains(pessoa);
    }

    @Test
    @DisplayName("Não deve adicionar pessoas")
    public void naoDeveAdicionarPessoaComNomeVazio(){
        //cenario
        CadastroPessoas cadastroPessoas = new CadastroPessoas();

        Pessoa pessoa = new Pessoa();

        //execucao
        assertThrows(PessoaSemNotException.class, () -> cadastroPessoas.adicionar(pessoa));


    }

    @Test
    @DisplayName("Deve remover pessoas")
    public void deveRemoverUmaPessoa(){

        CadastroPessoas cadastroPessoas = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Fabio");

        cadastroPessoas.adicionar(pessoa);

        cadastroPessoas.remover(pessoa);

        Assertions.assertThat(cadastroPessoas.getPessoas()).isEmpty();


    }

    @Test
    @DisplayName("Não deve remover pessoas")
    public void naoDeveRemoverUmaPessoa(){

        CadastroPessoas cadastroPessoas = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Fabio");

        assertThrows(PessoaSemNotException.class, () -> cadastroPessoas.remover(pessoa));

    }

}
