package br.com.udemy.demodaojdbc.model.dao;

import br.com.udemy.demodaojdbc.db.DB;
import br.com.udemy.demodaojdbc.model.dao.impl.SellerDaoJDBC;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DaoFactory {

    public static SellerDao createSellerDao() throws SQLException, FileNotFoundException {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
