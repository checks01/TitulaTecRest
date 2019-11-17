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
public class Salas {

    private int idSala;
    private String cveSala;
    private String descripcion;

    public Salas() {
    }

    public Salas(int idSala, String cveSala, String descripcion) {
        this.idSala = idSala;
        this.cveSala = cveSala;
        this.descripcion = descripcion;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getCveSala() {
        return cveSala;
    }

    public void setCveSala(String cveSala) {
        this.cveSala = cveSala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
