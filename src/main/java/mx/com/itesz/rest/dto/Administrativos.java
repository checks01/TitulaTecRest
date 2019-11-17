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
public class Administrativos {

    private int noEmpleado;
    private int idPuesto;
    private int idDepartamento;
    private int idUsuario;
    private String estatus;

    public Administrativos() {
    }

    public Administrativos(int noEmpleado, int idPuesto, int idDepartamento, int idUsuario, String estatus) {
        this.noEmpleado = noEmpleado;
        this.idPuesto = idPuesto;
        this.idDepartamento = idDepartamento;
        this.idUsuario = idUsuario;
        this.estatus = estatus;
    }

    public int getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
