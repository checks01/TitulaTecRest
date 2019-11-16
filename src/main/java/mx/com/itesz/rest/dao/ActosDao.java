/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.EmailUtils;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class ActosDao {

    public String getActos(int idSolicitud) throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{
                    "idActo",
                    "idSolicitud",
                    "alumno",
                    "nombreProyecto",
                    "opcionTitulacion",
                    "idSala",
                    "cveSala",
                    "sala",
                    "noDocenteP",
                    "nombreDocenteP",
                    "emailP",
                    "noDocenteS",
                    "nombreDocenteS",
                    "emailS",
                    "noDocenteV",
                    "nombreDocenteV",
                    "emailV",
                    "fechaPresentacion",
                    "horaInicio",
                    "horaFin",
                    "dictamen",
                    "cveEstatus",
                    "estatus"
                };
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT A.id_acto, ");
            query.append("       A.id_solicitud, ");
            query.append("       CONCAT(AL.nocontrol, ' - ', ua.nombre) as alumno, ");
            query.append("       s.nombre_proyecto, ");
            query.append("       OP.descripcion as opcionTitulacion, ");
            query.append("       A.id_sala, ");
            query.append("       SAL.cve_sala, ");
            query.append("       SAL.descripcion as sala, ");
            query.append("       A.no_docente_p, ");
            query.append("       usP.nombre as nombre_docente_p, ");
            query.append("       usP.email as emailP, ");
            query.append("       A.no_docente_s, ");
            query.append("       usS.nombre as nombre_docente_s, ");
            query.append("       usS.email as emailS, ");
            query.append("       A.no_docente_v, ");
            query.append("       usV.nombre as nombre_docente_v, ");
            query.append("       usV.email as emailV, ");
            query.append("       A.fecha_presentacion, ");
            query.append("       A.hora_inicio, ");
            query.append("       A.hora_fin, ");
            query.append("       A.dictamen, ");
            query.append("       A.estatus as cve_estatus, ");
            query.append("       CASE WHEN A.estatus = 'P' THEN 'Pendiente' WHEN A.estatus = 'A' THEN 'Aprobado' ");
            query.append("       WHEN A.estatus = 'R' THEN 'Realizado' WHEN A.estatus = 'C' THEN 'Cancelado' END AS estatus ");
            query.append("FROM   actos A,  ");
            query.append("salas sal,  ");
            query.append("solicitudes s,  ");
            query.append("opciones op,  ");
            query.append("alumnos al,  ");
            query.append("usuarios ua,  ");
            query.append("docentes docP,  ");
            query.append("docentes docS,  ");
            query.append("docentes docV,  ");
            query.append("usuarios usP,  ");
            query.append("usuarios usS,  ");
            query.append("usuarios usV  ");
            query.append("WHERE A.id_solicitud = s.id_solicitud ");
            query.append("and A.id_sala = sal.id_sala ");
            query.append("and s.id_opcion = op.id_opcion ");
            query.append("and s.nocontrol = al.nocontrol ");
            query.append("and al.idusuario = ua.idusuario ");
            query.append("and A.no_docente_p = docP.nodocente ");
            query.append("and docP.idusuario = usP.idUsuario ");
            query.append("and A.no_docente_s = docS.nodocente ");
            query.append("and docS.idusuario = usS.idusuario ");
            query.append("and A.no_docente_v = docV.nodocente ");
            query.append("and docV.idusuario = usV.idusuario ");

            if (idSolicitud > 0) {
                query.append("AND A.id_solicitud = ? ");
            }
            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            if (idSolicitud > 0) {
                ps.setInt(1, idSolicitud);
            }

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

    public String insertaActo(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO ACTOS(ID_SOLICITUD, ID_SALA, NO_DOCENTE_P, NO_DOCENTE_S, NO_DOCENTE_V, FECHA_PRESENTACION, HORA_INICIO, HORA_FIN, DICTAMEN, ESTATUS) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false,
                enviaCorreo = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaPresentacion;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(datosJob.get("idSolicitud").getAsString()));
            ps.setInt(2, Integer.parseInt(datosJob.get("idSala").getAsString()));
            ps.setInt(3, Integer.parseInt(datosJob.get("noDocenteP").getAsString()));
            ps.setInt(4, Integer.parseInt(datosJob.get("noDocenteS").getAsString()));
            ps.setInt(5, Integer.parseInt(datosJob.get("noDocenteV").getAsString()));

            fechaPresentacion = sdf.parse(datosJob.get("fechaPresentacion").getAsString());

            ps.setDate(6, new java.sql.Date(fechaPresentacion.getTime()));
            ps.setTime(7, Time.valueOf(datosJob.get("horaInicio").getAsString()));
            ps.setTime(8, Time.valueOf(datosJob.get("horaFin").getAsString()));
            ps.setString(9, datosJob.get("dictamen").getAsString());
            ps.setString(10, datosJob.get("estatus").getAsString());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
                enviaCorreo = new EmailUtils().enviaCorreo(1, datosJob, fechaPresentacion);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + ", enviaCorreo: " + enviaCorreo + "}";
    }

    public String actualizaActo(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE ACTOS SET ID_SALA = ?, NO_DOCENTE_P = ?, NO_DOCENTE_S = ?, NO_DOCENTE_V = ?, FECHA_PRESENTACION = ?, HORA_INICIO = ?, HORA_FIN = ?, DICTAMEN = ?, ESTATUS = ? WHERE ID_ACTO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false,
                enviaCorreo = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaPresentacion;
        try {

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, Integer.parseInt(datosJob.get("idSala").getAsString()));
            ps.setInt(2, Integer.parseInt(datosJob.get("noDocenteP").getAsString()));
            ps.setInt(3, Integer.parseInt(datosJob.get("noDocenteS").getAsString()));
            ps.setInt(4, Integer.parseInt(datosJob.get("noDocenteV").getAsString()));
            
            fechaPresentacion = sdf.parse(datosJob.get("fechaPresentacion").getAsString());
            
            ps.setDate(5, new java.sql.Date(fechaPresentacion.getTime()));
            ps.setTime(6, Time.valueOf(datosJob.get("horaInicio").getAsString()));
            ps.setTime(7, Time.valueOf(datosJob.get("horaFin").getAsString()));
            ps.setString(8, datosJob.get("dictamen").getAsString());
            ps.setString(9, datosJob.get("estatus").getAsString());
            ps.setInt(10, Integer.parseInt(datosJob.get("idActo").getAsString()));

            if (ps.executeUpdate() > 0) {
                /*
                        P - pendiente
                        A - autorizado
                        R - realizado
                        C - cancelado
                 */
                actualizaRegistro = true;
                int operacion = datosJob.get("estatus").getAsString().equals("A") ? 2 : datosJob.get("estatus").getAsString().equals("R") ? 3 : 4;
                enviaCorreo = new EmailUtils().enviaCorreo(operacion, datosJob, fechaPresentacion);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + ", enviaCorreo: " + enviaCorreo + "}";
    }
}
