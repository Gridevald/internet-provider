package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.AdminDao;
import by.training.provider.entity.Admin;
import by.training.provider.entity.Person;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminService implements PersonService {

    public Admin getByUnique(String email) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        AdminDao dao = new AdminDao(connection);

        List<Admin> adminList;
        try {
            adminList = dao.getByUniqueParameter(email);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        Admin admin = null;
        if (adminList.size() > 1) {
            throw new DataException("Got more than one admin by unique parameter.");
        } else {
            if (adminList.size() == 1) {
                admin = adminList.iterator().next();
            }
        }

        return admin;
    }

    @Override
    public Person getPersonByEmail(String email) throws DataException {
        return getByUnique(email);
    }
}
