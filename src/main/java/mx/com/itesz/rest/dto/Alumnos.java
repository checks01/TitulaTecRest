/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dto;

/**
 *
 * @author sergiov
 */
public class Alumnos {
    private int noControl;
    private Carreras carrera;
    private Usuarios usuario;
    private Estatus estatus;
    private float promedio;
    private int creditos;

    public Alumnos() {
    }

    public Alumnos(int noControl, Carreras carrera, Usuarios usuario, Estatus estatus, float promedio, int creditos) {
        this.noControl = noControl;
        this.carrera = carrera;
        this.usuario = usuario;
        this.estatus = estatus;
        this.promedio = promedio;
        this.creditos = creditos;
    }

    public int getNoControl() {
        return noControl;
    }

    public void setNoControl(int noControl) {
        this.noControl = noControl;
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    
}
