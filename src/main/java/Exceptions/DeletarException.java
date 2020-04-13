package Exceptions;

import java.sql.SQLException;

public class DeletarException extends SQLException {

    public DeletarException(String mensagem){
        super(mensagem);
    }
}
