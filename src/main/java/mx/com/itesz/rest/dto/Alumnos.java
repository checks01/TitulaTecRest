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

    private String noControl;
    private int idCarrera;
    private int idUsuario;
    private int idEstatus;
    private float promedio;
    private int creditos;

    public Alumnos() {
    }

    public Alumnos(String noControl, int idCarrera, int idUsuario, int idEstatus, float promedio, int creditos) {
        this.noControl = noControl;
        this.idCarrera = idCarrera;
        this.idUsuario = idUsuario;
        this.idEstatus = idEstatus;
        this.promedio = promedio;
        this.creditos = creditos;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
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
