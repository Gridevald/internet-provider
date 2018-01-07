package by.training.provider.dao;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Identifiable;

import java.util.List;

/**
 * Base DAO interface to make CRUD operations in DB.
 *
 * @param <T> type of object to process.
 */
public interface BaseDao<T extends Identifiable> {

    /**
     * Returns List of T objects by specified unique parameter.
     *
     * @param value unique parameter.
     * @return List of T objects.
     * @throws DataException when parsing result set or executing query.
     */
    List<T> getByUniqueParameter(String value) throws DataException;

    /**
     * Returns T object from appropriate table in DB.
     *
     * @param id index of record to find.
     * @return T element, if exists.
     * @throws DataException when got more than one element by unique ID
     *                      or SQLException occurred while executing query.
     */
    T getById(Integer id) throws DataException;

    /**
     * Returns List of T objects from appropriate table in DB.
     *
     * @return List of T objects
     * @throws DataException when SQLException occurred while executing query.
     */
    List<T> getAll() throws DataException;

    /**
     * Updates element in appropriate table in DB.
     *
     * @param element to update.
     * @throws DataException when affected rows more than one or
     *                      SQLException occurred while executing query.
     */
    void update(T element) throws DataException;

    /**
     * Deletes element in appropriate table in DB.
     *
     * @param element to delete.
     * @throws DataException when not one row affected or
     *                      SQLException occurred while executing query.
     */
    void delete(T element) throws DataException;

    /**
     * Inserts element in appropriate table in DB.
     *
     * @param element to insert.
     * @throws DataException when not only one row affected or
     *                      SQLException occurred while executing query.
     */
    void insert(T element) throws DataException;
}
