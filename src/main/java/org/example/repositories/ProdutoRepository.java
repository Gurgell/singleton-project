package org.example.repositories;

import org.example.models.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository{

    void salvar(Produto produto);

    Produto buscarPorId(UUID id);

    void deletar(Produto produto);

    List<Produto> listar();
}
