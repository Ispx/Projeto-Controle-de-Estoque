package DataBase;


import ProdutoDAOExceptions.*;
import Projeto_Controle_de_Estoque.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO  {
    private static Connection connection = null;


    public ProdutoDAO(Connection connection) {
        //Inicializando a variavél de conexão e assumindo o controle dos comitis no banco de dados
        try {
            this.connection = connection;
            connection.setAutoCommit(false);
        }catch (SQLException e){
            new ConnectionException("Falha ao obter conexão com banco de dados! \nGentileza tentar novamente mais tarde.");
            e.printStackTrace();
        }
    }

    public void adicionar(Produto produto) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUTOS(NOME,PRECO,QUANTIDADE,CATEGORIA_ID) VALUES(?,?,?,?)");
            preparedStatement.setString(1,produto.getName());
            preparedStatement.setDouble(2, produto.getPrice());
            preparedStatement.setInt(3,produto.getQuantity());
            preparedStatement.setInt(4,produto.getCategoriaId());

            preparedStatement.execute(); //executando tarefas
            connection.commit(); //comitando alterações

        }catch (SQLException e) {
            new AdcionaException("Falha ao adicionar produto no banco de dados! \nGentileza tentar novamente mais tarde.");
            e.printStackTrace();
            try {
                connection.rollback(); //refazendo alterações
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void ler(String sql){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
                System.out.println("CategoriaId: " + resultSet.getInt("CATEGORIA_ID") + " Nome: " + resultSet.getString("NOME") + " Preço: R$ " + resultSet.getDouble("PRECO"));
            }

        } catch (SQLException e) {
            new LerException("Falha ao fazer leitura no banco de dados! \nGentileza tentar novamente mais tarde.");
            e.printStackTrace();
        }

    }

    public static void atualizarPreco(Double preço, String Id){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUTOS SET PRECO = ? WHERE ID = ?");

            preparedStatement.setDouble(1,preço);
            preparedStatement.setString(2,Id);
            preparedStatement.execute();
            connection.commit();

        }catch (SQLException e){
            new UpdateException("Falha ao atualizar dados! \nGentileza tenta novamente mais tarde.");
            try {
                connection.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void atualizarQuantidade(int id, int qtd){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
            preparedStatement.setInt(1,qtd);
            preparedStatement.setInt(2,id);

            preparedStatement.execute();

            connection.commit();

        }catch (SQLException e){
            new UpdateException("Falha ao atualizar dados. \nGentileza tente novamente mais tarde.");
            e.printStackTrace();

            try{
                connection.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void deletar(){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ID WHERE QUANTIDADE = 0");
            preparedStatement.execute();

            connection.commit();

        }catch (SQLException e){
            new DeletarException("Falha ao deletar itens do banco de dados! \nGentileza tente novamente mais tarde.");
        }

    }
}
