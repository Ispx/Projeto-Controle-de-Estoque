package Classes;


import DataBase.ProdutoDAO;

import java.util.Scanner;

public class Programas {

    public static void removerProduto(ProdutoDAO produtoDAO){
        Scanner sc = new Scanner(System.in);
        System.out.print("Id: ");
        produtoDAO.deletar(sc.nextInt());
    }
    public static void cunsultarEstoque(ProdutoDAO produtoDAO){
        produtoDAO.consultaProdutos();
    }


    public static void atualizarQuantidade(ProdutoDAO produtoDAO){
        Scanner sc = new Scanner(System.in);
        System.out.print("Id: ");
        int id = sc.nextInt();
        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();
        produtoDAO.atualizarQuantidade(id,qtd);

    }


    public static void adicionarProdutos(ProdutoDAO produtoDAO){
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantidade de produtos a ser adicionado: ");
        int i = sc.nextInt();
        while(i >= 0) {
            System.out.print("Nome: ");
            String nome = sc.next();
            System.out.print("Preço: R$ ");
            Double price = sc.nextDouble();
            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            System.out.print("Categoria: " +
                    "1 - ALIMENTO\n" +
                    "2 - ELETRÔNICO\n" +
                    "3 - VESTUÁRIO\n" +
                    "4 - HIGIENE");
            Byte ctg = sc.nextByte();
            produtoDAO.adicionar(new Produto(nome, price,qtd,ctg));

            i --;

            break;
        }


    }
}
