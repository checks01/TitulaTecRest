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
public class Puestos {

    private int idPuesto;
    private String nombre;

    public Puestos() {
    }

    public Puestos(int idPuesto, String nombre) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
