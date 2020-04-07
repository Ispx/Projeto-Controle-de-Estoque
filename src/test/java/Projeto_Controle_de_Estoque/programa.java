package Projeto_Controle_de_Estoque;

import DataBase.ConnectionFactory;
import DataBase.ProdutoDAO;

/*
Criar um método para apresentar uma listagem dos x produtos mais vendidos.
Apresentar a quantidade de produtos vendidos de determinado elemento e o total;


 */
public class programa {
    public static void main(String[] args) {

        try(ConnectionFactory connectionFactory = new ConnectionFactory()) {
            System.out.println("1 - Adicionar produto:" + "\n" + "2 - Retirar produto:" + "\n" + "3 - Dados do estoque:" + "\n" + "4 - Produtos mais vendidos: (Em Desenvolvimento)" + "\n" + "5 - Sair do programa:");

            ProdutoDAO produtoDAO = new ProdutoDAO(connectionFactory.getConnection());

            produtoDAO.adicionar(new Produto("Pera",5.5,7, (byte) 1));



        }







    }
}
