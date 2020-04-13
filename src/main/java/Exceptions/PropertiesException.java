package Exceptions;

import java.io.FileNotFoundException;

public class PropertiesException extends FileNotFoundException {
    public PropertiesException(String mensagem){
        super(mensagem);
    }
}
