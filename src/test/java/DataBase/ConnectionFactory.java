package DataBase;

import Exceptions.ConnectionException;
import Exceptions.PropertiesException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements AutoCloseable{

    private ComboPooledDataSource comboPooledDataSource = null;
    private Properties properties = null;

    public ConnectionFactory(){
        properties = properties();
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(properties.getProperty("dburl")); //ENDEREÇO
        comboPooledDataSource.setUser(properties.getProperty("user")); //USUÁRIO
        comboPooledDataSource.setPassword(properties.getProperty("password")); //SENHA
        comboPooledDataSource.setProperties(properties); //SSL = FALSE
    }

    public void autoCommit(boolean status, ConnectionFactory connection){
        connection.comboPooledDataSource.setAutoCommitOnClose(status);

    }
    public void autoCommit(boolean status){
        comboPooledDataSource.setAutoCommitOnClose(status);
    }

    public void quantityConnections(int quantity){
        comboPooledDataSource.setMaxPoolSize(quantity);
    }

    public Connection getConnection() {
        try {
            return comboPooledDataSource.getConnection();
        }catch (SQLException e){
            e = new ConnectionException("Falha ao obter conexão! \nGentileza verificar propriedades de conexão.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close(){
        if (comboPooledDataSource != null) {
            comboPooledDataSource.close();
        }



    }

    private Properties properties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\isaqu\\OneDrive\\Área de Trabalho\\Alura\\propriedadesdb.txt"));
        } catch (PropertiesException e) {
            e = new PropertiesException("Falha ao obter propriedades de conexão! \nGentileza verificar.");
            System.out.println(e.getMessage());
        }catch (IOException exx){
            exx.printStackTrace();
        }
        return properties;
    }

}
