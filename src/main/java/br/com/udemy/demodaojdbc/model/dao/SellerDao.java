package br.com.udemy.demodaojdbc.model.dao;

import br.com.udemy.demodaojdbc.model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);

    void update(Seller obj);

    void deleteById(Integer id);

    Seller findById(Seller obj);

    List<Seller> findAll();


}
