package Exceptions;

import java.sql.SQLException;

public class AdcionaException extends SQLException {

    public AdcionaException(String mensagem){
        super(mensagem);
    }



}
