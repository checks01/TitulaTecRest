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
public class ComentariosEvidencias {
    private int idComentarioEvidencia;
    private Evidencias evidencia;
    private Date fecha;
    private String observaciones;

    public ComentariosEvidencias() {
    }

    public ComentariosEvidencias(int idComentarioEvidencia, Evidencias evidencia, Date fecha, String observaciones) {
        this.idComentarioEvidencia = idComentarioEvidencia;
        this.evidencia = evidencia;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public int getIdComentarioEvidencia() {
        return idComentarioEvidencia;
    }

    public void setIdComentarioEvidencia(int idComentarioEvidencia) {
        this.idComentarioEvidencia = idComentarioEvidencia;
    }

    public Evidencias getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencias evidencia) {
        this.evidencia = evidencia;
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
