/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Salas;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author checo
 */
public class SalasDao {

    public String getSalasDisponibles(String fechaPresentacion, String horaInicio, String horaFin) throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM SALAS ");
            query.append("WHERE ID_SALA NOT IN( ");
            query.append("SELECT id_sala FROM ACTOS ");
            query.append("WHERE fecha_presentacion =  str_to_date(?, '%Y-%m-%d') ");
            query.append("and hora_inicio between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s') ");
            query.append("or hora_fin between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s'))");

            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            ps.setString(1, fechaPresentacion);
            ps.setString(2, horaInicio);
            ps.setString(3, horaFin);
            ps.setString(4, horaInicio);
            ps.setString(5, horaFin);
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getSalasDisponibles");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaSala(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO SALAS(ID_SALA, CVE_SALA, DESCRIPCION) VALUES(?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Salas sala;
        try {
            sala = gson.fromJson(datosJob, Salas.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, sala.getIdSala());
            ps.setString(2, sala.getCveSala());
            ps.setString(3, sala.getDescripcion());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaSala(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE SALAS SET CVE_SALA = ?, DESCRIPCION = ? WHERE ID_SALA = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Salas sala;
        try {
            sala = gson.fromJson(datosJob, Salas.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, sala.getCveSala());
            ps.setString(2, sala.getDescripcion());
            ps.setInt(3, sala.getIdSala());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaSala(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM SALAS WHERE ID_SALA = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Salas sala;
        try {
            sala = gson.fromJson(datosJob, Salas.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, sala.getIdSala());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
