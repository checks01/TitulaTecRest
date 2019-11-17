/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dto;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author sergiov
 */
public class Actos {

    private int idActo;
    private int idSolicitud;
    private int idSala;
    private int noDocenteP;
    private int noDocenteS;
    private int noDocenteV;
    private Date fechaPresentacion;
    private Time horaInicio;
    private Time horaFin;
    private String dictamen;
    private String estatus;

    public Actos() {
    }

    public Actos(int idActo, int idSolicitud, int idSala, int noDocenteP, int noDocenteS, int noDocenteV, Date fechaPresentacion, Time horaInicio, Time horaFin, String dictamen, String estatus) {
        this.idActo = idActo;
        this.idSolicitud = idSolicitud;
        this.idSala = idSala;
        this.noDocenteP = noDocenteP;
        this.noDocenteS = noDocenteS;
        this.noDocenteV = noDocenteV;
        this.fechaPresentacion = fechaPresentacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dictamen = dictamen;
        this.estatus = estatus;
    }

    public int getIdActo() {
        return idActo;
    }

    public void setIdActo(int idActo) {
        this.idActo = idActo;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getNoDocenteP() {
        return noDocenteP;
    }

    public void setNoDocenteP(int noDocenteP) {
        this.noDocenteP = noDocenteP;
    }

    public int getNoDocenteS() {
        return noDocenteS;
    }

    public void setNoDocenteS(int noDocenteS) {
        this.noDocenteS = noDocenteS;
    }

    public int getNoDocenteV() {
        return noDocenteV;
    }

    public void setNoDocenteV(int noDocenteV) {
        this.noDocenteV = noDocenteV;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getDictamen() {
        return dictamen;
    }

    public void setDictamen(String dictamen) {
        this.dictamen = dictamen;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
