/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
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
        FacadeUtilsImpl facade = new FacadeUtilsImpl();
        List<Object[]> lista = new ArrayList<>();
        try {
            lista = facade.consultaSalasDisponibles(fechaPresentacion, horaInicio, horaFin);

            jsonData = facade.generaJsonString(lista, "getSalasDisponibles");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
