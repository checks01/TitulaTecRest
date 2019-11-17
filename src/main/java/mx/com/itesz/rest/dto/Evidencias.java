/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dto;

import java.sql.Blob;

/**
 *
 * @author sergiov
 */
public class Evidencias {

    private int idEvidencia;
    private int idSolicitud;
    private String nombre;
    private Blob archivo;
    private String extension;
    private String estatus;
    private String observaciones;

    public Evidencias() {
    }

    public Evidencias(int idEvidencia, int idSolicitud, String nombre, Blob archivo, String extension, String estatus, String observaciones) {
        this.idEvidencia = idEvidencia;
        this.idSolicitud = idSolicitud;
        this.nombre = nombre;
        this.archivo = archivo;
        this.extension = extension;
        this.estatus = estatus;
        this.observaciones = observaciones;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Blob getArchivo() {
        return archivo;
    }

    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
