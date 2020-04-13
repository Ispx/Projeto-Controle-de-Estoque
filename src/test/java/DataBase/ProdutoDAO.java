package DataBase;


import Classes.Produto;
import Exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO  implements AutoCloseable{
    private static Connection connection = null;


    public ProdutoDAO(Connection connection) {
        //Inicializando a variavél de conexão e assumindo o controle dos comitis no banco de dados
        try {
            this.connection = connection;
            connection.setAutoCommit(false);
        }catch (SQLException e){
           e = new ConnectionException("Falha ao obter conexão com banco de dados! \nGentileza tentar novamente mais tarde.");
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
            e = new AdcionaException("Falha ao adicionar produto no banco de dados! \nGentileza tentar novamente mais tarde.");
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
            preparedStatement.execute();

            connection.commit();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("ID") + " CategoriaId: " + resultSet.getInt("CATEGORIA_ID") + " Nome: " + resultSet.getString("NOME") + " Preço: R$ " + resultSet.getDouble("PRECO") + "Qtd: " + resultSet.getInt("QUANTIDADE"));
            }

        } catch (SQLException e) {
            e = new LerException("Falha ao fazer leitura no banco de dados! \nGentileza verificar e tentar novamente.");
            e.printStackTrace();
        }

    }

    public void consultaProdutos() {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CATEGORIAS.ID, CATEGORIAS.NOME_CTG, PRODUTOS.NOME, " +
                    "AVG(PRECO) AS PRECO_MEDIO, SUM(QUANTIDADE) AS QTD_TOTAL, " +
                    "SUM(QUANTIDADE) * SUM(PRECO) AS TOTAL FROM PRODUTOS " +
                    "INNER JOIN CATEGORIAS ON CATEGORIAS.ID=PRODUTOS.CATEGORIA_ID GROUP BY CATEGORIAS.ID,CATEGORIAS.NOME_CTG,PRODUTOS.NOME");

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("ID") + " - CATEGORIA: " + resultSet.getString("NOME_CTG") +
                        " - PRODUTO: " + resultSet.getString("NOME") + " - PREÇO MÉDIO: R$ " + resultSet.getDouble("PRECO_MEDIO") +
                        " - QTD TOTAL: " + resultSet.getInt("QTD_TOTAL") + " - TOTAL: R$ " + resultSet.getDouble("TOTAL"));
            }
        }catch(SQLException e){
                e = new LerException("Falha ao fazer consulta no banco de dados! \nGentileza verificar e tentar novamenteo!");
            }
        }

    public static void atualizarPreco(Double preco, String Id){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUTOS SET PRECO = ? WHERE ID = ?");

            preparedStatement.setDouble(1,preco);
            preparedStatement.setString(2,Id);
            preparedStatement.execute();
            connection.commit();

        }catch (SQLException e){
            e = new UpdateException("Falha ao atualizar dados! \nGentileza tenta novamente mais tarde.");
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void atualizarQuantidade(int id, int qtd){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
            preparedStatement.setInt(1,qtd);
            preparedStatement.setInt(2,id);

            preparedStatement.execute();

            connection.commit();

            deletar(); // deletando elementos sem quantidade

        }catch (SQLException e){
            e = new UpdateException("Falha ao atualizar dados. \nGentileza tente novamente mais tarde.");
            e.printStackTrace();
            try{
                connection.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    private void deletar(){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTOS WHERE QUANTIDADE = 0");
            preparedStatement.execute();

            connection.commit();

        }catch (SQLException e){
            e = new DeletarException("Falha ao deletar itens do banco de dados! \nGentileza tente novamente mais tarde.");
            e.printStackTrace();
        }

    }

    public void deletar(int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTOS WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            connection.commit();

        }catch (SQLException e){
            e = new DeletarException("Falha ao deletar itens do banco de dados! \nGentileza tente novamente mais tarde.");
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
