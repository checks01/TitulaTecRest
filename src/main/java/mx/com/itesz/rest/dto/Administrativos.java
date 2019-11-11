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
    private Puestos puesto;
    private Departamentos departamento;
    private Usuarios usuario;
    private String estatus;

    public Administrativos() {
    }

    public Administrativos(int noEmpleado, Puestos puesto, Departamentos departamento, Usuarios usuario, String estatus) {
        this.noEmpleado = noEmpleado;
        this.puesto = puesto;
        this.departamento = departamento;
        this.usuario = usuario;
        this.estatus = estatus;
    }

    public int getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
