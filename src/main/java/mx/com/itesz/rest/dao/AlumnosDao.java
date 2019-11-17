/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import mx.com.itesz.rest.dto.Alumnos;
import mx.com.itesz.rest.facade.impl.FacadeUtilsImpl;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class AlumnosDao {

    public String getAlumnos() throws Exception {
        String jsonData = "";
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM ALUMNOS ");
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            
            jsonData = new FacadeUtilsImpl().generaJsonString(ps, "getAlumnos");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaAlumno(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO ALUMNOS(NOCONTROL, IDCARRERA, IDUSUARIO, IDESTATUS, PROMEDIO, CREDITOS) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Alumnos alumno;
        try {
            alumno = gson.fromJson(datosJob, Alumnos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setString(1, alumno.getNoControl());
            ps.setInt(2, alumno.getIdCarrera());
            ps.setInt(3, alumno.getIdUsuario());
            ps.setInt(4, alumno.getIdEstatus());
            ps.setFloat(5, alumno.getPromedio());
            ps.setInt(6, alumno.getCreditos());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaAlumno(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE ALUMNOS SET IDCARRERA = ?, IDUSUARIO = ?, IDESTATUS = ?, PROMEDIO = ?, CREDITOS = ? WHERE NOCONTROL = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Alumnos alumno;
        try {
            alumno = gson.fromJson(datosJob, Alumnos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, alumno.getIdCarrera());
            ps.setInt(2, alumno.getIdUsuario());
            ps.setInt(3, alumno.getIdEstatus());
            ps.setFloat(4, alumno.getPromedio());
            ps.setInt(5, alumno.getCreditos());
            ps.setString(6, alumno.getNoControl());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaAlumno(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM ALUMNOS WHERE NOCONTROL = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Alumnos alumno;
        try {
            alumno = gson.fromJson(datosJob, Alumnos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setString(1, alumno.getNoControl());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
