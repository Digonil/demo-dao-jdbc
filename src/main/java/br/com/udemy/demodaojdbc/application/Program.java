package br.com.udemy.demodaojdbc.application;

import br.com.udemy.demodaojdbc.model.dao.DaoFactory;
import br.com.udemy.demodaojdbc.model.dao.SellerDao;
import br.com.udemy.demodaojdbc.model.entities.Department;
import br.com.udemy.demodaojdbc.model.entities.Seller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Program {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 3: seller findAll ===");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: seller insert ===");

        Seller newSeller = new Seller(null, "Greg", "greg@mail.com", LocalDate.now(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println(seller);
    }


}
