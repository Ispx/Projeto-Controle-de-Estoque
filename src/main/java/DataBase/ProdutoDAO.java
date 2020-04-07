package DataBase;


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
            e.printStackTrace();
        }

    }

    //public static void atualizar()


    //public static void deletar(int categoriaId, int qtd)
}
