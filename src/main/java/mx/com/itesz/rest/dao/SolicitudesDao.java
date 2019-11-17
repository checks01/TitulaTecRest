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
import mx.com.itesz.rest.dto.Solicitudes;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class SolicitudesDao {

    public String getSolicitudesAprobadas() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{
                    "idSolicitud",
                    "idOpcion",
                    "cveOpcion",
                    "descripcion",
                    "fechaElaboracion",
                    "nombreProyecto",
                    "noControl",
                    "idUsuarioAlumno",
                    "nombreAlumno",
                    "emailAlumno",
                    "idCarrera",
                    "siglas",
                    "carrera",
                    "noEmpleado",
                    "idusuarioAdministrativo",
                    "nombreAdministrativo",
                    "emailAdministrativo",
                    "nombrePuesto",
                    "nombreDepartamento"};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT S.id_solicitud, ");
            query.append("       OP.id_opcion, ");
            query.append("       OP.cve_opcion, ");
            query.append("       OP.descripcion, ");
            query.append("       S.fecha_elaboracion, ");
            query.append("       S.nombre_proyecto, ");
            query.append("       AL.nocontrol, ");
            query.append("       UAL.IDUSUARIO AS ID_USUARIO_AL, ");
            query.append("       UAL.nombre AS NOMBRE_AL, ");
            query.append("       UAL.email as email_alumno, ");
            query.append("       CAR.idcarrera, ");
            query.append("       CAR.siglas, ");
            query.append("       CAR.nombre as CARRERA, ");
            query.append("       AD.noempleado, ");
            query.append("       UAD.IDUSUARIO AS ID_USUARIO_AD, ");
            query.append("       UAD.nombre AS NOMBRE_AD, ");
            query.append("       UAD.email as email_administrativo, ");
            query.append("       PU.nombre  AS NOMBRE_PUESTO, ");
            query.append("       DEP.nombre AS NOMBRE_DEPARTAMENTO ");
            query.append("FROM   solicitudes S, ");
            query.append("       alumnos AL, ");
            query.append("       administrativos AD, ");
            query.append("       opciones OP, ");
            query.append("       carreras CAR, ");
            query.append("       puestos PU, ");
            query.append("       departamentos DEP, ");
            query.append("       usuarios UAL, ");
            query.append("       usuarios UAD ");
            query.append("WHERE  S.nocontrol = AL.nocontrol ");
            query.append("       AND AL.idcarrera = CAR.idcarrera ");
            query.append("       AND S.noempleado = AD.noempleado ");
            query.append("       AND AD.idpuesto = PU.idpuesto ");
            query.append("       AND AD.iddepartamento = DEP.iddepartamento ");
            query.append("       AND S.id_opcion = OP.id_opcion ");
            query.append("       AND AL.idusuario = UAL.idusuario ");
            query.append("       AND AD.idusuario = UAD.idusuario ");
            query.append("       AND S.estatus = 'A'");

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

    public String insertaSolicitud(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO SOLICITUDES(ID_SOLICITUD, NOCONTROL, NOEMPLEADO, ID_OPCION, FECHA_ELABORACION, NOMBRE_PROYECTO, ESTATUS) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaElaboracion;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(datosJob.get("idSolicitud").getAsString()));
            ps.setString(2, datosJob.get("noControl").getAsString());
            ps.setInt(3, Integer.parseInt(datosJob.get("noEmpleado").getAsString()));
            ps.setInt(4, Integer.parseInt(datosJob.get("idOpcion").getAsString()));

            fechaElaboracion = sdf.parse(datosJob.get("fechaElaboracion").getAsString());
            ps.setDate(5, new java.sql.Date(fechaElaboracion.getTime()));
            ps.setString(6, datosJob.get("nombreProyecto").getAsString());
            ps.setString(7, datosJob.get("estatus").getAsString());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaSolicitud(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE SOLICITUDES SET NOCONTROL = ?, NOEMPLEADO = ?, ID_OPCION = ?, FECHA_ELABORACION = ?, NOMBRE_PROYECTO = ?, ESTATUS = ? WHERE ID_SOLICITUD = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaElaboracion;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, datosJob.get("noControl").getAsString());
            ps.setInt(2, Integer.parseInt(datosJob.get("noEmpleado").getAsString()));
            ps.setInt(3, Integer.parseInt(datosJob.get("idOpcion").getAsString()));

            fechaElaboracion = sdf.parse(datosJob.get("fechaElaboracion").getAsString());
            ps.setDate(4, new java.sql.Date(fechaElaboracion.getTime()));
            ps.setString(5, datosJob.get("nombreProyecto").getAsString());
            ps.setString(6, datosJob.get("estatus").getAsString());
            ps.setInt(7, Integer.parseInt(datosJob.get("idSolicitud").getAsString()));

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaSolicitud(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM SOLICITUDES WHERE ID_SOLICITUD = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Solicitudes solicitud;
        try {
            solicitud = gson.fromJson(datosJob, Solicitudes.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, solicitud.getIdSolicitud());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }
}
