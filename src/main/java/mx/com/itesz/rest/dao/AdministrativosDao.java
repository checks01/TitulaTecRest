/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Administrativos;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class AdministrativosDao {

    public String getAdministrativos() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM ADMINISTRATIVOS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getAdministrativos");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaAdministrativo(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO ADMINISTRATIVOS(NOEMPLEADO, IDPUESTO, IDDEPARTAMENTO, IDUSUARIO, ESTATUS) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Administrativos administrativo;
        try {
            administrativo = gson.fromJson(datosJob, Administrativos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, administrativo.getNoEmpleado());
            ps.setInt(2, administrativo.getIdPuesto());
            ps.setInt(3, administrativo.getIdDepartamento());
            ps.setInt(4, administrativo.getIdUsuario());
            ps.setString(5, administrativo.getEstatus());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaAdministrativo(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE ADMINISTRATIVOS SET IDPUESTO = ?, IDDEPARTAMENTO = ?, IDUSUARIO = ?, ESTATUS = ? WHERE NOEMPLEADO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Administrativos administrativo;
        try {
            administrativo = gson.fromJson(datosJob, Administrativos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, administrativo.getIdPuesto());
            ps.setInt(2, administrativo.getIdDepartamento());
            ps.setInt(3, administrativo.getIdUsuario());
            ps.setString(4, administrativo.getEstatus());
            ps.setInt(5, administrativo.getNoEmpleado());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaAdministrativo(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM ADMINISTRATIVOS WHERE NOEMPLEADO = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Administrativos administrativo;
        try {
            administrativo = gson.fromJson(datosJob, Administrativos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, administrativo.getNoEmpleado());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
