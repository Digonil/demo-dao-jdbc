package br.com.udemy.demodaojdbc.model.dao.impl;

import br.com.udemy.demodaojdbc.db.DB;
import br.com.udemy.demodaojdbc.db.DbException;
import br.com.udemy.demodaojdbc.model.dao.SellerDao;
import br.com.udemy.demodaojdbc.model.entities.Department;
import br.com.udemy.demodaojdbc.model.entities.Seller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO seller\n"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n"
                    + "VALUES\n"
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
            ps.setDouble(4, obj.getBaseSalary());
            ps.setInt(5, obj.getDepartment().getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(Seller obj) {


        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE seller\n"
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?\n"
                    + "WHERE Id = ?");

            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
            ps.setDouble(4, obj.getBaseSalary());
            ps.setInt(5, obj.getDepartment().getId());
            ps.setInt(6, obj.getId());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }


    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName \n" + "FROM seller INNER JOIN department \n" + "ON seller.DepartmentId = department.Id \n" + "WHERE seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                //Instanciando o departamento
                Department dep = instantiateDepartment(rs);

                //Instanciando o vendedor
                Seller obj = instantiateSeller(rs, dep);

                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        obj.setDepartment(dep);

        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentID"));
        dep.setName(rs.getString("DepName"));

        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n"
                            + "FROM seller INNER JOIN department \n"
                            + "ON seller.DepartmentId = department.Id\n"
                            + "ORDER BY Name");

            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }
                //Instanciando o vendedor
                Seller obj = instantiateSeller(rs, dep);

                list.add(obj);


            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }


    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n"
                            + "FROM seller INNER JOIN department \n"
                            + "ON seller.DepartmentId = department.Id\n"
                            + "WHERE DepartmentId = ?\n"
                            + "ORDER BY Name");

            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }
                //Instanciando o vendedor
                Seller obj = instantiateSeller(rs, dep);

                list.add(obj);


            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }
}
