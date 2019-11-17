/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.Blob;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Evidencias;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class EvidenciasDao {

    public String getEvidencias() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM EVIDENCIAS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getEvidencias");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO EVIDENCIAS(ID_EVIDENCIA, ID_SOLICITUD, NOMBRE, ARCHIVO, EXTENSION, ESTATUS, OBSERVACIONES) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        byte[] byteContent;
        Blob archivo;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(datosJob.get("idEvidencia").getAsString()));
            ps.setInt(2, Integer.parseInt(datosJob.get("idSolicitud").getAsString()));
            ps.setString(3, datosJob.get("nombre").getAsString());

            byteContent = datosJob.get("archivo").getAsString().getBytes();
            archivo = Conexion.getInstance().getCn().createBlob();
            archivo.setBytes(1, byteContent);

            ps.setBlob(4, archivo);
            ps.setString(5, datosJob.get("extension").getAsString());
            ps.setString(6, datosJob.get("estatus").getAsString());
            ps.setString(7, datosJob.get("observaciones").getAsString());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE EVIDENCIAS SET ID_SOLICITUD = ?, NOMBRE = ?, ARCHIVO = ?, EXTENSION = ?, ESTATUS = ?, OBSERVACIONES = ? WHERE ID_EVIDENCIA = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        byte[] byteContent;
        Blob archivo;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, Integer.parseInt(datosJob.get("idSolicitud").getAsString()));
            ps.setString(2, datosJob.get("nombre").getAsString());

            byteContent = datosJob.get("archivo").getAsString().getBytes();
            archivo = Conexion.getInstance().getCn().createBlob();
            archivo.setBytes(1, byteContent);

            ps.setBlob(3, archivo);
            ps.setString(4, datosJob.get("extension").getAsString());
            ps.setString(5, datosJob.get("estatus").getAsString());
            ps.setString(6, datosJob.get("observaciones").getAsString());
            ps.setInt(7, Integer.parseInt(datosJob.get("idEvidencia").getAsString()));

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM EVIDENCIAS WHERE ID_EVIDENCIA = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Evidencias evidencia;
        try {
            evidencia = gson.fromJson(datosJob, Evidencias.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, evidencia.getIdEvidencia());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
