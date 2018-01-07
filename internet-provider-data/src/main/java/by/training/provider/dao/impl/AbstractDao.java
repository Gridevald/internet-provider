package by.training.provider.dao.impl;

import by.training.provider.dao.BaseDao;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Identifiable;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract class with realization of CRUD operations in DB.
 *
 * @param <T> type of object to process.
 */
public abstract class AbstractDao<T extends Identifiable>
        implements BaseDao<T> {

    /**
     * Connection to be used in methods.
     */
    private Connection connection;

    //////////////////////////////////////////////////////////////////////

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Getter for that child classes who need connection in
     * it's own methods.
     *
     * @return Connection.
     */
    protected Connection getConnection() {
        return connection;
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Returns query for select all elements from table.
     *
     * @return string sql query.
     */
    protected abstract String getSelectQuery();

    /**
     * Returns query with unique parameter.
     *
     * @return string sql query.
     */
    protected abstract String getUniqueQuery();

    /**
     * Returns postfix "where id" for select query.
     *
     * @return string sql query.
     */
    protected abstract String getWhereIdQuery();

    /**
     * Returns query for update element in table.
     *
     * @return string sql query.
     */
    protected abstract String getUpdateQuery();

    /**
     * Returns query for delete element from table.
     *
     * @return string sql query.
     */
    protected abstract String getDeleteQuery();

    /**
     * Returns query for insert element in table.
     *
     * @return string sql query.
     */
    protected abstract String getInsertQuery();

    /**
     * Parses ResultSet to List of T objects. Parses only direct-object
     * parameters (there is no included objects from related tables).
     *
     * @param resultSet to parse.
     * @return List of T objects with only main body.
     * @throws DataException when SQLException occurred while parsing.
     */
    protected abstract List<T> parseResultSet(ResultSet resultSet)
            throws DataException;

    /**
     * Fills PreparedStatement with information for updating.
     *
     * @param statement to fill.
     * @param element   to update.
     * @throws DataException when SQLException occurred while filling
     *                      PreparedStatement
     */
    protected abstract void prepareForUpdate(PreparedStatement statement,
                                             T element)
            throws DataException;

    /**
     * Fills PreparedStatement with information for inserting.
     *
     * @param statement to fill.
     * @param element   to insert.
     * @throws DataException when SQLException occurred while filling
     *                      PreparedStatement
     */
    protected abstract void prepareForInsert(PreparedStatement statement,
                                             T element)
            throws DataException;

    //////////////////////////////////////////////////////////////////////

    /**
     * Returns "lazy" T object by unique parameter.
     *
     * @param value unique value (for example, by email) to find element.
     * @return T object.
     * @throws DataException when parsing result set or executing query.
     */
    @Override
    public List<T> getByUniqueParameter(String value)
            throws DataException {

        String query = getUniqueQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, value);

            ResultSet resultSet = statement.executeQuery();

            return parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Returns "lazy" T object from appropriate table in DB.
     *
     * @param id   index of record to find.
     * @return T element, if exists.
     * @throws DataException when got more than one element by unique ID
     *                      or SQLException occurred while executing query.
     */
    public T getById(Integer id)
            throws DataException {

        String query = getWhereIdQuery();
        List<T> elements;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            elements = parseResultSet(resultSet);

            if (elements.size() > 1) {
                throw new DataException("Got more than one element by ID.");
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        T element = null;

        if (elements.size() != 0) {
            Iterator<T> iterator = elements.iterator();
            element = iterator.next();
        }

        return element;
    }

    /**
     * Returns List of "lazy" T objects from appropriate table in DB.
     *
     * @return List of T objects
     * @throws DataException when SQLException occurred while executing query.
     */
    public List<T> getAll()
            throws DataException {

        String query = getSelectQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            return parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Updates element in appropriate table in DB.
     *
     * @param element to update.
     * @throws DataException when not only one row affected or
     *                      SQLException occurred while executing query.
     */
    public void update(T element) throws DataException {

        String query = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareForUpdate(statement, element);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DataException("On update rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Deletes element in appropriate table in DB.
     *
     * @param element to delete.
     * @throws DataException when not only one row affected or
     *                      SQLException occurred while executing query.
     */
    public void delete(T element) throws DataException {

        String query = getDeleteQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Integer id = element.getId();
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DataException("On delete rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Inserts element in appropriate table in DB.
     *
     * @param element to insert.
     * @throws DataException when not only one row affected or
     *                      SQLException occurred while executing query.
     */
    public void insert(T element) throws DataException {

        String query = getInsertQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareForInsert(statement, element);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DataException("On insert rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
