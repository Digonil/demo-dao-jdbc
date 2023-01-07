package br.com.udemy.demodaojdbc.model.dao;

import br.com.udemy.demodaojdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }
}
