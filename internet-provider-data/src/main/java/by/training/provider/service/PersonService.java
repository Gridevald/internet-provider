package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Person;

public interface PersonService {

    Person getPersonByEmail(String email) throws DataException;
}
