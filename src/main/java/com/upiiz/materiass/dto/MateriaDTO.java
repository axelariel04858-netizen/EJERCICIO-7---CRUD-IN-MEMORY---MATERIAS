package com.upiiz.materiass.dto;

//Data Transfers Object
public class MateriaDTO {
    private String nombre;
    private float creditos;

    public MateriaDTO(){

    }

    public MateriaDTO(String nombre, float creditos) {
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }
}
