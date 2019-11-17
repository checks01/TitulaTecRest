/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import mx.com.itesz.rest.dto.ComentariosEvidencias;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class ComentariosEvidenciasDao {

    public String getComentariosEvidencias() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{"idComentarioEvidencia", "idEvidencia", "fecha", "observaciones"};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM COMENTARIOS_EVIDENCIAS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            lista = FormUtil.executeQuery(ps);
            jsonData = FormUtil.generaJsonString(true, "Proceso realizado correctamente", lista.size(), lista, mapping);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaComentarioEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO COMENTARIOS_EVIDENCIAS(ID_COMENTARIO_EVIDENCIA, ID_EVIDENCIA, FECHA, OBSERVACIONES) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(datosJob.get("idComentarioEvidencia").getAsString()));
            ps.setInt(2, Integer.parseInt(datosJob.get("idEvidencia").getAsString()));

            fecha = sdf.parse(datosJob.get("fecha").getAsString());
            ps.setDate(3, new java.sql.Date(fecha.getTime()));
            ps.setString(4, datosJob.get("observaciones").getAsString());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaComentarioEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE COMENTARIOS_EVIDENCIAS SET ID_EVIDENCIA = ?, FECHA = ?, OBSERVACIONES = ? WHERE ID_COMENTARIO_EVIDENCIA = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, Integer.parseInt(datosJob.get("idEvidencia").getAsString()));

            fecha = sdf.parse(datosJob.get("fecha").getAsString());
            ps.setDate(2, new java.sql.Date(fecha.getTime()));
            ps.setString(3, datosJob.get("observaciones").getAsString());

            ps.setInt(4, Integer.parseInt(datosJob.get("idComentarioEvidencia").getAsString()));

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaComentarioEvidencia(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM COMENTARIOS_EVIDENCIAS WHERE ID_COMENTARIO_EVIDENCIA = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        ComentariosEvidencias comentarioEvidencia;
        try {
            comentarioEvidencia = gson.fromJson(datosJob, ComentariosEvidencias.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, comentarioEvidencia.getIdComentarioEvidencia());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
