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
public class Opciones {

    private int idOpcion;
    private String cveOpcion;
    private String descripcion;

    public Opciones() {
    }

    public Opciones(int idOpcion, String cveOpcion, String descripcion) {
        this.idOpcion = idOpcion;
        this.cveOpcion = cveOpcion;
        this.descripcion = descripcion;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getCveOpcion() {
        return cveOpcion;
    }

    public void setCveOpcion(String cveOpcion) {
        this.cveOpcion = cveOpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
