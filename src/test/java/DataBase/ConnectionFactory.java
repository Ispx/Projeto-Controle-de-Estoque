package DataBase;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close(){
        try {
            if (comboPooledDataSource != null) {
                comboPooledDataSource.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Properties properties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\isaqu\\OneDrive\\Área de Trabalho\\Alura\\propriedadesdb.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return properties;
    }

}
