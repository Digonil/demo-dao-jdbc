package br.com.udemy.demodaojdbc.application;

import br.com.udemy.demodaojdbc.model.dao.DaoFactory;
import br.com.udemy.demodaojdbc.model.dao.SellerDao;
import br.com.udemy.demodaojdbc.model.entities.Department;
import br.com.udemy.demodaojdbc.model.entities.Seller;

import java.time.LocalDate;

public class Program {

    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDate.now(), 3000.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(seller);
    }


}
