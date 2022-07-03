package com.api.rest.biblioteca.entidades;

import ch.qos.logback.classic.db.names.ColumnName;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
uniqueContrains : sirve para que el valor del nombre del libro sea único
* */

@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint( columnNames = {"nombre"})})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    /*
    LAZY, solo va a traer información cuando se le indique únicamente y se evita
    errores con la notación de Json Property
    * */
    /*
    @JoinColumn nos sirve para crear el objeto biblioteca_id y que pueda haber esa conexión
    de clave foranea de la BD
    * */
     /*
    @JsonProperty nos sirve para que en la API REST ignore
     la propiedad de serialización de la cadena por la propiedad LAZY
    * */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "biblioteca_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biblioteca biblioteca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
