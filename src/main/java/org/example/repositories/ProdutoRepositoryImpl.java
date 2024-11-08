package org.example.repositories;

import org.example.models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProdutoRepositoryImpl implements ProdutoRepository{
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public Produto buscarPorId(UUID id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deletar(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public List<Produto> listar() {

        return produtos;
    }
}
