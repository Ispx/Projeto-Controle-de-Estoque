package Classes;

import DataBase.ConnectionFactory;
import DataBase.ProdutoDAO;

import java.util.Scanner;

/*
Criar um método para apresentar uma listagem dos x produtos mais vendidos.
Apresentar a quantidade de produtos vendidos de determinado elemento e o total;


 */
public class Main {
    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Scanner sc = new Scanner(System.in);
        int opc;
        Boolean condicao = true;


        try(ProdutoDAO produtoDAO = new ProdutoDAO(connectionFactory.getConnection())) {
            while(condicao == true) {

                System.out.println("1 - Adicionar produto:" + "\n" + "2 - Atualizar quantidade:" + "\n" + "3 - Remover produto" + "\n" + "4 - Consutar estoque:" + "\n" + "5 - Sair do programa:");

                opc = sc.nextInt();

                switch (opc) {

                    case 1:
                        Programas.adicionarProdutos(produtoDAO);
                        break;
                    case 2:
                        Programas.atualizarQuantidade(produtoDAO);
                        break;
                    case 3:
                        Programas.removerProduto(produtoDAO);
                        break;
                    case 4:
                        Programas.cunsultarEstoque(produtoDAO);
                        break;
                    case 5:
                        condicao = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }


    }
}
