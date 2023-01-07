package br.com.udemy.demodaojdbc.db;

public class DbIntegrityException extends RuntimeException {
    public DbIntegrityException(String msg) {
        super(msg);
    }
}
