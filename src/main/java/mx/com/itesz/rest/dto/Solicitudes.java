/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dto;

import java.util.Date;

/**
 *
 * @author sergiov
 */
public class Solicitudes {
    private int idSolicitud;
    private Alumnos alumno;
    private Administrativos administrativo;
    private Opciones opcion;
    private Date fechaElaboracion;
    private String nombreProyecto;
    private String status;

    public Solicitudes() {
    }

    public Solicitudes(int idSolicitud, Alumnos alumno, Administrativos administrativo, Opciones opcion, Date fechaElaboracion, String nombreProyecto, String status) {
        this.idSolicitud = idSolicitud;
        this.alumno = alumno;
        this.administrativo = administrativo;
        this.opcion = opcion;
        this.fechaElaboracion = fechaElaboracion;
        this.nombreProyecto = nombreProyecto;
        this.status = status;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Administrativos getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Administrativos administrativo) {
        this.administrativo = administrativo;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
