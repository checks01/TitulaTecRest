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
import mx.com.itesz.rest.dto.Departamentos;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class DepartamentosDao {

    public String getDepartamentos() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{"idDepartamento", "nombre"};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM DEPARTAMENTOS ");
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

    public String insertaDepartamento(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO DEPARTAMENTOS(IDDEPARTAMENTO, NOMBRE) VALUES(?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Departamentos departamento;
        try {
            departamento = gson.fromJson(datosJob, Departamentos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, departamento.getIdDepartamento());
            ps.setString(2, departamento.getNombre());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaDepartamento(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE DEPARTAMENTOS SET NOMBRE = ? WHERE IDDEPARTAMENTO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Departamentos departamento;
        try {
            departamento = gson.fromJson(datosJob, Departamentos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, departamento.getNombre());
            ps.setInt(2, departamento.getIdDepartamento());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaDepartamento(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM DEPARTAMENTOS WHERE IDDEPARTAMENTO = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Departamentos departamento;
        try {
            departamento = gson.fromJson(datosJob, Departamentos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, departamento.getIdDepartamento());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
