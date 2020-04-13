package Exceptions;

import java.sql.SQLException;

public class LerException extends SQLException {

    public LerException(String mensagem){
        super(mensagem);
    }
}
