package com.tiendavinos.proyectofinal.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

}
