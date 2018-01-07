package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.PaymentDao;
import by.training.provider.entity.Payment;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    public List<Payment> getPaymentsByUserId(Integer userId) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PaymentDao dao = new PaymentDao(connection);

        List<Payment> paymentList;
        try {
            paymentList = dao.getByUniqueParameter(String.valueOf(userId));
            connection.commit();
        } catch (SQLException | DataException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return paymentList;
    }

    public void addPayment(Payment payment) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PaymentDao dao = new PaymentDao(connection);

        try {
            dao.insert(payment);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }
    }
}
