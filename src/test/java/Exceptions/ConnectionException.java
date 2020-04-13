package Exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
    public ConnectionException(String mensagem){
        super(mensagem);
    }
}
