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
    private Solicitudes solicitud;
    private Salas sala;
    private Docentes noDocenteP;
    private Docentes noDocenteS;
    private Docentes noDocenteV;
    private Date fechaPresentacion;
    private Time horaInicio;
    private Time horaFin;
    private String dictamen;
    private String estatus;

    public Actos() {
    }

    public Actos(int idActo, Solicitudes solicitud, Salas sala, Docentes noDocenteP, Docentes noDocenteS, Docentes noDocenteV, Date fechaPresentacion, Time horaInicio, Time horaFin, String dictamen, String estatus) {
        this.idActo = idActo;
        this.solicitud = solicitud;
        this.sala = sala;
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

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    public Docentes getNoDocenteP() {
        return noDocenteP;
    }

    public void setNoDocenteP(Docentes noDocenteP) {
        this.noDocenteP = noDocenteP;
    }

    public Docentes getNoDocenteS() {
        return noDocenteS;
    }

    public void setNoDocenteS(Docentes noDocenteS) {
        this.noDocenteS = noDocenteS;
    }

    public Docentes getNoDocenteV() {
        return noDocenteV;
    }

    public void setNoDocenteV(Docentes noDocenteV) {
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
