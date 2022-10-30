package com.portfolio.GRMG.Dto;

import javax.validation.constraints.NotBlank;

public class DtoEducacion {

    @NotBlank
    private String nombreEd;
    @NotBlank
    private String descripcionEd;

    //constructores
    public DtoEducacion() {
    }

    public DtoEducacion(String nombreEd, String descripcionEd) {
        this.nombreEd = nombreEd;
        this.descripcionEd = descripcionEd;
    }

    //setters y getters
    public String getNombreEd() {
        return nombreEd;
    }

    public void setNombreEd(String nombreEd) {
        this.nombreEd = nombreEd;
    }

    public String getDescripcionEd() {
        return descripcionEd;
    }

    public void setDescripcionEd(String descripcionEd) {
        this.descripcionEd = descripcionEd;
    }

}
