package by.training.provider.entity;

import java.io.Serializable;

/**
 * Interface for marking entities with id.
 */
public interface Identifiable extends Serializable {

    Integer getId();
}
