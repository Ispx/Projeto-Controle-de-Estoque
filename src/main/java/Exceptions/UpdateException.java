package Exceptions;

import java.sql.SQLException;

public class UpdateException extends SQLException {
    public UpdateException(String mensagem){
        super(mensagem);
    }
}
