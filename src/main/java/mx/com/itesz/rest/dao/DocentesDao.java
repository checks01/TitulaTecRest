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
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.itesz.rest.dto.Docentes;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class DocentesDao {

    public String getDocentesActivos() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{
                    "noDocente",
                    "noDocente",
                    "idCarrera",
                    "carrera",
                    "idUsuario",
                    "email",
                    "nombre",
                    "escolaridad",
                    "cedula"
                };
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT D.nodocente, ");
            query.append("       CAR.idcarrera, ");
            query.append("       CAR.idCarrera, ");
            query.append("       CAR.nombre AS CARRERA, ");
            query.append("       US.idusuario, ");
            query.append("       US.email, ");
            query.append("       US.nombre, ");
            query.append("       D.escolaridad, ");
            query.append("       D.especialidad, ");
            query.append("       D.cedula ");
            query.append("FROM   docentes D, ");
            query.append("       carreras CAR, ");
            query.append("       usuarios US ");
            query.append("WHERE  D.idcarrera = CAR.idcarrera ");
            query.append("       AND D.idusuario = US.idusuario ");
            query.append("       AND D.estatus = 'A'");

            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            lista = FormUtil.executeQuery(ps);
            jsonData = FormUtil.generaJsonString(true, "Proceso realizado correctamente", lista.size(), lista, mapping);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }

    public String insertaDocente(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO DOCENTES(NODOCENTE, IDCARRERA, IDUSUARIO, ESCOLARIDAD, ESPECIALIDAD, CEDULA, ESTATUS) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Docentes docente;
        try {
            docente = gson.fromJson(datosJob, Docentes.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, docente.getNoDocente());
            ps.setInt(2, docente.getIdCarrera());
            ps.setInt(3, docente.getIdUsuario());
            ps.setString(4, docente.getEscolaridad());
            ps.setString(5, docente.getEspecialidad());
            ps.setString(6, docente.getCedula());
            ps.setString(7, docente.getEstatus());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaDocente(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE DOCENTES SET IDCARRERA = ?, IDUSUARIO = ?, ESCOLARIDAD = ?, ESPECIALIDAD = ?, CEDULA = ?, ESTATUS = ? WHERE NODOCENTE = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Docentes docente;
        try {
            docente = gson.fromJson(datosJob, Docentes.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, docente.getIdCarrera());
            ps.setInt(2, docente.getIdUsuario());
            ps.setString(3, docente.getEscolaridad());
            ps.setString(4, docente.getEspecialidad());
            ps.setString(5, docente.getCedula());
            ps.setString(6, docente.getEstatus());
            ps.setInt(7, docente.getNoDocente());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaDocente(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM DOCENTES WHERE NODOCENTE = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Docentes docente;
        try {
            docente = gson.fromJson(datosJob, Docentes.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, docente.getNoDocente());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
