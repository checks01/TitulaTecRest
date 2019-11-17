/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dto;

import java.sql.Date;

/**
 *
 * @author sergiov
 */
public class Solicitudes {

    private int idSolicitud;
    private String noControl;
    private int noEmpleado;
    private int idOpcion;
    private Date fechaElaboracion;
    private String nombreProyecto;
    private String estatus;

    public Solicitudes() {
    }

    public Solicitudes(int idSolicitud, String noControl, int noEmpleado, int idOpcion, Date fechaElaboracion, String nombreProyecto, String estatus) {
        this.idSolicitud = idSolicitud;
        this.noControl = noControl;
        this.noEmpleado = noEmpleado;
        this.idOpcion = idOpcion;
        this.fechaElaboracion = fechaElaboracion;
        this.nombreProyecto = nombreProyecto;
        this.estatus = estatus;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public int getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
