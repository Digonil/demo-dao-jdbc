package br.com.udemy.demodaojdbc.model.dao;

import br.com.udemy.demodaojdbc.model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);

    Department findById(Department obj);

    List<Department> findAll();


}
