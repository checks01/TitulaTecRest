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
public class Docentes {
    private int noDocente;
    private Carreras carrera;
    private Usuarios usuario;
    private String escolaridad;
    private String especialidad;
    private String cedula;
    private String estatus;

    public Docentes() {
    }

    public Docentes(int noDocente, Carreras carrera, Usuarios usuario, String escolaridad, String especialidad, String cedula, String estatus) {
        this.noDocente = noDocente;
        this.carrera = carrera;
        this.usuario = usuario;
        this.escolaridad = escolaridad;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.estatus = estatus;
    }

    public int getNoDocente() {
        return noDocente;
    }

    public void setNoDocente(int noDocente) {
        this.noDocente = noDocente;
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

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
