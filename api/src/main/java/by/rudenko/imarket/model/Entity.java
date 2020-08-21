package by.rudenko.imarket.model;

import java.io.Serializable;

/**
 * Entity class with abstract ID model
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
public interface Entity extends Serializable {

    Long getId();

    void setId(Long id);


}
