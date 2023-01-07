package br.com.udemy.demodaojdbc.application;

import br.com.udemy.demodaojdbc.model.entities.Department;

public class Program {

    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        System.out.println(obj);
    }


}
