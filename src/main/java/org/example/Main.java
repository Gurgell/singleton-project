package org.example;
import org.example.services.ProdutoService;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ProdutoService produtoService = ProdutoService.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSistema de Gerenciamento de Produtos");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Adicionar Produto");
            System.out.println("3. Buscar Produto por ID");
            System.out.println("4. Deletar Produto");
            System.out.println("5. Atualizar produto");
            System.out.print("Digite qualquer outro número para sair: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    produtoService.listar();
                    break;

                case 2:
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    try{
                        System.out.print("Digite o preço do produto: ");
                        Double preco = scanner.nextDouble();
                        produtoService.salvar(nome, preco);
                    }catch (InputMismatchException ex){
                        System.out.println("O preço foi digitado incorretamente. Casas decimais devem ser separadas por vírgula e este campo não aceita caracteres.");
                        scanner.nextLine();
                    }

                    break;

                case 3:
                    try{
                        System.out.print("Digite o ID do produto para buscar: ");
                        String idBuscarString = scanner.nextLine();
                        UUID idBuscar = UUID.fromString(idBuscarString);
                        System.out.println(produtoService.buscarPorId(idBuscar));
                    }catch(IllegalArgumentException ex){
                        System.out.println("O ID inserido está incorreto.");
                    }
                    break;

                case 4:
                    try{
                        System.out.print("Digite o ID do produto para deletar: ");
                        String idDeletarString = scanner.nextLine();
                        UUID idDeletar = UUID.fromString(idDeletarString);
                        produtoService.deletar(idDeletar);
                    }catch(IllegalArgumentException ex){
                        System.out.println("O ID inserido está incorreto.");
                    }
                    break;

                case 5:
                    try{
                        System.out.print("Digite o ID do produto que deseja atualizar: ");
                        String idAtualizarString = scanner.nextLine();
                        UUID idAtualizar = UUID.fromString(idAtualizarString);
                        System.out.print("Digite o novo nome do produto: ");
                        String nomeProduto = scanner.nextLine();
                        System.out.print("Digite o novo valor do produto: ");
                        Double valorProduto = scanner.nextDouble();
                        produtoService.atualizar(idAtualizar, nomeProduto, valorProduto);
                    }catch(IllegalArgumentException ex){
                        System.out.println("O ID inserido está incorreto.");
                    }catch (InputMismatchException ex){
                        System.out.println("O preço foi digitado incorretamente. Utilize a vírgula para separar as casas decimais e certifique-se de que o campo não contenha caracteres inválidos.");
                        scanner.nextLine();
                    }
                    break;
                default:
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;
            }
        }

        scanner.close();
    }
}