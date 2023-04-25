package com.portfolio.GRMG.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHys {

    @NotBlank
    private String nombreH;
    @NotBlank
    private int porcentaje;

    //constructores
    public DtoHys() {
    }

    public DtoHys(String nombreH, int porcentaje) {
        this.nombreH = nombreH;
        this.porcentaje = porcentaje;
    }

    //getters y setters
    public String getNombreH() {
        return nombreH;
    }

    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

}
