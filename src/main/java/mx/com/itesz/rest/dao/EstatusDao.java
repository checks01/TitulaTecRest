/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Estatus;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class EstatusDao {

    public String getEstatus() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM ESTATUS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());

            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getEstatus");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaEstatus(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO ESTATUS(IDESTATUS, NOMBRE) VALUES(?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Estatus estatus;
        try {
            estatus = gson.fromJson(datosJob, Estatus.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, estatus.getIdEstatus());
            ps.setString(2, estatus.getNombre());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaEstatus(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE ESTATUS SET NOMBRE = ? WHERE IDESTATUS = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Estatus estatus;
        try {
            estatus = gson.fromJson(datosJob, Estatus.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, estatus.getNombre());
            ps.setInt(2, estatus.getIdEstatus());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaEstatus(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM ESTATUS WHERE IDESTATUS = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Estatus estatus;
        try {
            estatus = gson.fromJson(datosJob, Estatus.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, estatus.getIdEstatus());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
