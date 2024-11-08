package org.example.services;

import org.example.models.Produto;
import org.example.repositories.ProdutoRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepositoryImpl produtoRepository;

    private ProdutoService produtoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        produtoService = ProdutoService.getInstance();
        produtoService.setProdutoRepository(produtoRepository);
    }

    @Test
    public void testSalvarProduto() {
        Produto produto = new Produto(UUID.randomUUID(), "Produto Teste", 10.0);
        produtoService.salvar(produto.getNome(), produto.getPreco());
        verify(produtoRepository, times(1)).salvar(any(Produto.class));
    }

    @Test
    public void testBuscarPorIdProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Produto Teste", 10.0);
        when(produtoRepository.buscarPorId(id)).thenReturn(produto);

        Produto produtoEncontrado = produtoService.buscarPorId(id);
        assertEquals(produto, produtoEncontrado);
    }

    @Test
    public void testAtualizarProdutoExistente() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Chave de fenda", 5.8);
        when(produtoRepository.buscarPorId(id)).thenReturn(produto);

        produtoService.atualizar(id, "chave philips", 6.3);
        assertEquals("chave philips", produto.getNome());
        assertEquals(6.3, produto.getPreco(), 0);
    }

    @Test
    public void testAtualizarProdutoInexistente() {
        UUID id = UUID.randomUUID();
        when(produtoRepository.buscarPorId(id)).thenReturn(null);

        produtoService.atualizar(id, "Produto Atualizado", 15.0);
        verify(produtoRepository, times(1)).buscarPorId(id);
    }

    @Test
    public void testDeletarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Produto Teste", 10.0);
        when(produtoRepository.buscarPorId(id)).thenReturn(produto);

        produtoService.deletar(id);
        verify(produtoRepository, times(1)).deletar(produto);
    }

    @Test
    public void testListarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(UUID.randomUUID(), "Produto 1", 10.0));
        produtos.add(new Produto(UUID.randomUUID(), "Produto 2", 20.0));
        when(produtoRepository.listar()).thenReturn(produtos);

        List<Produto> produtosListados = produtoService.listar();

        verify(produtoRepository, times(1)).listar();
        assertEquals(produtos, produtosListados);
    }
}

