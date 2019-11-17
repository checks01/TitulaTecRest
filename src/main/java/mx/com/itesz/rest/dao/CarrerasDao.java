/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Carreras;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class CarrerasDao {

    public String getCarreras() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM CARRERAS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getCarreras");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaCarrera(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO CARRERAS(IDCARRERA, SIGLAS, NOMBRE, CREDITOS, ESPECIALIDAD, ESTATUS, NOEMPLEADO) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Carreras carrera;
        try {
            carrera = gson.fromJson(datosJob, Carreras.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, carrera.getIdCarrera());
            ps.setString(2, carrera.getSiglas());
            ps.setString(3, carrera.getNombre());
            ps.setInt(4, carrera.getCreditos());
            ps.setString(5, carrera.getEspecialidad());
            ps.setString(6, carrera.getEstatus());
            ps.setInt(7, carrera.getNoEmpleado());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaCarrera(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE CARRERAS SET SIGLAS = ?, NOMBRE = ?, CREDITOS = ?, ESPECIALIDAD = ?, ESTATUS = ?, NOEMPLEADO = ? WHERE IDCARRERA = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Carreras carrera;
        try {
            carrera = gson.fromJson(datosJob, Carreras.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, carrera.getSiglas());
            ps.setString(2, carrera.getNombre());
            ps.setInt(3, carrera.getCreditos());
            ps.setString(4, carrera.getEspecialidad());
            ps.setString(5, carrera.getEstatus());
            ps.setInt(6, carrera.getNoEmpleado());
            ps.setInt(7, carrera.getIdCarrera());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaCarrera(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM CARRERAS WHERE IDCARRERA = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Carreras carrera;
        try {
            carrera = gson.fromJson(datosJob, Carreras.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, carrera.getIdCarrera());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
