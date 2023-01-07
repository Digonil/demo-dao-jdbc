package br.com.udemy.demodaojdbc.application;

import br.com.udemy.demodaojdbc.model.dao.DaoFactory;
import br.com.udemy.demodaojdbc.model.dao.SellerDao;
import br.com.udemy.demodaojdbc.model.entities.Department;
import br.com.udemy.demodaojdbc.model.entities.Seller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Program {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    }


}
