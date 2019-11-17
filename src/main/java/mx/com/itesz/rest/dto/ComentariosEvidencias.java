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
public class ComentariosEvidencias {

    private int idComentarioEvidencia;
    private int idEvidencia;
    private Date fecha;
    private String observaciones;

    public ComentariosEvidencias() {
    }

    public ComentariosEvidencias(int idComentarioEvidencia, int idEvidencia, Date fecha, String observaciones) {
        this.idComentarioEvidencia = idComentarioEvidencia;
        this.idEvidencia = idEvidencia;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public int getIdComentarioEvidencia() {
        return idComentarioEvidencia;
    }

    public void setIdComentarioEvidencia(int idComentarioEvidencia) {
        this.idComentarioEvidencia = idComentarioEvidencia;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
