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
public class Carreras {
    private int idCarrera;
    private String siglas;
    private String nombre;
    private int creditos;
    private String especialidad;
    private String estatus;
    private int noEmpleado;

    public Carreras() {
    }

    public Carreras(int idCarrera, String siglas, String nombre, int creditos, String especialidad, String estatus, int noEmpleado) {
        this.idCarrera = idCarrera;
        this.siglas = siglas;
        this.nombre = nombre;
        this.creditos = creditos;
        this.especialidad = especialidad;
        this.estatus = estatus;
        this.noEmpleado = noEmpleado;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }
    
    
}
