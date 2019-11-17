/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Puestos;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class PuestosDao {

    public String getPuestos() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM PUESTOS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getPuestos");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaPuesto(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO PUESTOS(IDPUESTO, NOMBRE) VALUES(?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Puestos puesto;
        try {
            puesto = gson.fromJson(datosJob, Puestos.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, puesto.getIdPuesto());
            ps.setString(2, puesto.getNombre());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaPuesto(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE PUESTOS SET NOMBRE = ? WHERE IDPUESTO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Puestos puesto;
        try {
            puesto = gson.fromJson(datosJob, Puestos.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, puesto.getNombre());
            ps.setInt(2, puesto.getIdPuesto());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaPuesto(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM PUESTOS WHERE IDPUESTO = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Puestos puesto;
        try {
            puesto = gson.fromJson(datosJob, Puestos.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, puesto.getIdPuesto());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
