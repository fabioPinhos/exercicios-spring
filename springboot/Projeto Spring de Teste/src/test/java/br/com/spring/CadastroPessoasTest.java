package br.com.spring;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CadastroPessoasTest {

    @Test
    public void deveCriarOCadastroDePessoas(){

        //cenario e execucao
        CadastroPessoas cadastro = new CadastroPessoas();

        //verificacao
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
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

    @Test(expected = PessoaSemNotException.class)
    public void naoDeveAdicionarPessoaComNomeVazio(){
        //cenario
        CadastroPessoas cadastroPessoas = new CadastroPessoas();

        Pessoa pessoa = new Pessoa();

        //execucao
        cadastroPessoas.adicionar(pessoa);


    }

    @Test
    public void deveRemoverUmaPessoa(){

        CadastroPessoas cadastroPessoas = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Fabio");

        cadastroPessoas.remover(pessoa);

        Assertions.assertThat(cadastroPessoas.getPessoas()).isEmpty();


    }

    @Test(expected = CadastroVazioException.class)
    public void naoDeveRemoverUmaPessoa(){

        CadastroPessoas cadastroPessoas = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Fabio");

        cadastroPessoas.remover(pessoa);

    }
}
