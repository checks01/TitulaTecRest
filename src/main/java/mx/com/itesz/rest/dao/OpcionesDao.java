/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Opciones;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class OpcionesDao {

    public String getOpciones() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM OPCIONES ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getOpciones");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaOpcion(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO OPCIONES(ID_OPCION, CVE_OPCION, DESCRIPCION) VALUES(?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Opciones opcion;
        try {
            opcion = gson.fromJson(datosJob, Opciones.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, opcion.getIdOpcion());
            ps.setString(2, opcion.getCveOpcion());
            ps.setString(3, opcion.getDescripcion());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaOpcion(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE OPCIONES SET CVE_OPCION = ?, DESCRIPCION = ? WHERE ID_OPCION = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Opciones opcion;
        try {
            opcion = gson.fromJson(datosJob, Opciones.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, opcion.getCveOpcion());
            ps.setString(2, opcion.getDescripcion());
            ps.setInt(3, opcion.getIdOpcion());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaOpcion(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM OPCIONES WHERE ID_OPCION = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Opciones opcion;
        try {
            opcion = gson.fromJson(datosJob, Opciones.class);
            
            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, opcion.getIdOpcion());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
