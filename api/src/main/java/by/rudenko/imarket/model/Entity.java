package by.rudenko.imarket.model;

import java.io.Serializable;

/**
 * абстрактная модель Сущности объектов с АйДи
 */
public interface Entity extends Serializable {

    Long getId();

    void setId(Long id);


}
