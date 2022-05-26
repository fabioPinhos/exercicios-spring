package br.com.example.exerciciossb.controllers;

import br.com.example.exerciciossb.model.entities.Produto;
import br.com.example.exerciciossb.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Passando parametros separados
     * @param produto
     * @return
     */
//    @PostMapping
//    public @ResponseBody Produto novoProduto(
//            @RequestParam String nome,
//            @RequestParam double preco,
//            @RequestParam double desconto){
//        Produto produto = new Produto(nome, preco, desconto);
//
//        produtoRepository.save(produto);
//
//        return produto;
//    }

    /**
     * Passando Objeto como parametro
     *
     * Podemos tbm fazer um RequestMapping e no method dizer que vamos Inserir(POST) e tambem Alterar(PUT)
     * @param produto
     * @return
     */
    @PostMapping
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody Produto novoProduto(@Valid Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping
    public Iterable<Produto> obterProdutos(){
        System.out.println("Aqui !!!");
        return produtoRepository.findAll();
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
    public Iterable<Produto> obterProdutosPorPagina(
            @PathVariable int numeroPagina,
            @PathVariable int qtdePagina){

        if(qtdePagina >= 5) qtdePagina = 5;

        Pageable page = PageRequest.of(numeroPagina,qtdePagina);
        return produtoRepository.findAll(page);
    }

    @GetMapping(path = "/{id}")
    public Optional obterProdutoPorId(@PathVariable int id){
        return produtoRepository.findById(id);
    }

    @PutMapping
    public Produto alterarProduto(@Valid Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    @DeleteMapping(path = "/{id}")
    public void excluirProduto(@PathVariable int id){
        produtoRepository.deleteById(id);
    }

    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Produto> obterProdutosPorNome(@PathVariable String parteNome){
        System.out.println("chegou: " + parteNome);
        return produtoRepository.findByNomeNotContaining(parteNome);
//        return produtoRepository.searchByNameLike(parteNome);

    }

}
