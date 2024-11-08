package org.example.services;

import org.example.models.Produto;
import org.example.repositories.ProdutoRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProdutoService {
    private static ProdutoService instance;
    private ProdutoRepositoryImpl produtoRepository;

    private ProdutoService(){
        produtoRepository = new ProdutoRepositoryImpl();
    }

    public static ProdutoService getInstance(){
        synchronized (ProdutoService.class){
            if (Objects.isNull(instance)){
                instance = new ProdutoService();
            }
        }
        return instance;
    }

    //Apenas para injetar o mock manualmente
    void setProdutoRepository(ProdutoRepositoryImpl produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvar(String nome, Double preco) {

        Produto Produto = new Produto(UUID.randomUUID(), nome, preco);
        produtoRepository.salvar(Produto);
        System.out.println("Produto criado: " + Produto);
    }

    public Produto buscarPorId(UUID id) {
        return produtoRepository.buscarPorId(id);
    }

    public void atualizar(UUID id, String nome, Double preco) {
        Produto produto = produtoRepository.buscarPorId(id);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produto.setNome(nome);
        produto.setPreco(preco);

        System.out.println("Produto atualizado: " + produto);
    }

    public void deletar(UUID id) {
        Produto produtoExistente = buscarPorId(id);

        if (produtoExistente == null){
            System.out.println("Produto não encontrado.");
            return;
        }

        produtoRepository.deletar(produtoExistente);
        System.out.println("O produto: " + produtoExistente + " foi deletado com sucesso!");
    }

    public List<Produto> listar() {
        List<Produto> produtosListados = produtoRepository.listar();

        produtosListados.forEach(produto -> System.out.println(produto));
        return produtosListados;
    }
}
