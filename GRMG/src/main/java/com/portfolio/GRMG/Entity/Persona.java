package com.portfolio.GRMG.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud, máximo 50 caracteres")
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud, máximo 50 caracteres")
    private String apellido;

    @NotNull
    @Size(min = 1, max = 100, message = "No cumple con la longitud, máximo 100 caracteres")
    private String titulo;

    @NotNull
    @Size(min = 1, max = 1000, message = "No cumple con la longitud, máximo 1000 caracteres")
    private String descripcion;

    @NotNull
    private String img;

    //constructores
    public Persona() {
    }

    public Persona(String nombre, String apellido, String titulo, String descripcion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
    }

    //getters y setters
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
