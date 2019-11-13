/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.itesz.rest.dto.Actos;
import mx.com.itesz.rest.dto.Docentes;
import mx.com.itesz.rest.dto.Salas;
import mx.com.itesz.rest.dto.Solicitudes;
import mx.com.itesz.rest.dto.Usuarios;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.EmailUtils;

/**
 *
 * @author sergiov
 */
public class ActosDao {

    public ArrayList<Actos> getActos(int idSolicitud) throws Exception {
        ArrayList<Actos> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT A.id_acto, ");
            query.append("       A.id_solicitud, ");
            query.append("       A.id_sala, ");
            query.append("       A.no_docente_p, ");
            query.append("       usP.email as emailP, ");
            query.append("       A.no_docente_s, ");
            query.append("       usS.email as emailS, ");
            query.append("       A.no_docente_v, ");
            query.append("       usV.email as emailV, ");
            query.append("       A.fecha_presentacion, ");
            query.append("       A.hora_inicio, ");
            query.append("       A.hora_fin, ");
            query.append("       A.dictamen, ");
            query.append("       A.estatus ");
            query.append("FROM   actos A,  ");
            query.append("docentes docP,  ");
            query.append("docentes docS,  ");
            query.append("docentes docV,  ");
            query.append("usuarios usP,  ");
            query.append("usuarios usS,  ");
            query.append("usuarios usV  ");
            query.append("WHERE A.no_docente_p = docP.nodocente ");
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
            rs = ps.executeQuery();
            while (rs.next()) {
                Salas sala = new Salas();
                sala.setIdSala(rs.getInt("id_sala"));

                Solicitudes solicitud = new Solicitudes();
                solicitud.setIdSolicitud(rs.getInt("id_solicitud"));

                Docentes docenteP = new Docentes();
                docenteP.setNoDocente(rs.getInt("no_docente_p"));
                Usuarios usuarioP = new Usuarios();
                usuarioP.setEmail(rs.getString("emailP"));
                docenteP.setUsuario(usuarioP);

                Docentes docenteS = new Docentes();
                docenteS.setNoDocente(rs.getInt("no_docente_s"));
                Usuarios usuarioS = new Usuarios();
                usuarioS.setEmail(rs.getString("emailS"));
                docenteS.setUsuario(usuarioS);

                Docentes docenteV = new Docentes();
                docenteV.setNoDocente(rs.getInt("no_docente_v"));
                Usuarios usuarioV = new Usuarios();
                usuarioV.setEmail(rs.getString("emailV"));
                docenteV.setUsuario(usuarioV);

                Actos ac = new Actos();
                ac.setIdActo(rs.getInt("id_acto"));
                ac.setSolicitud(solicitud);
                ac.setSala(sala);
                ac.setNoDocenteP(docenteP);
                ac.setNoDocenteS(docenteS);
                ac.setNoDocenteV(docenteV);
                ac.setFechaPresentacion(rs.getDate("fecha_presentacion"));
                ac.setHoraInicio(rs.getTime("hora_inicio"));
                ac.setHoraFin(rs.getTime("hora_fin"));
                ac.setDictamen(rs.getString("dictamen"));
                ac.setEstatus(rs.getString("estatus"));

                lista.add(ac);
            }
        } catch (Exception ex) {
            Logger.getLogger(SolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return lista;
    }

    public String insertaActo(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO ACTOS(ID_SOLICITUD, ID_SALA, NO_DOCENTE_P, NO_DOCENTE_S, NO_DOCENTE_V, FECHA_PRESENTACION, HORA_INICIO, HORA_FIN, DICTAMEN, ESTATUS) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false,
                enviaCorreo = false;
        Actos acto;
        try {
            acto = gson.fromJson(datosJob.get("acto"), Actos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, acto.getSolicitud().getIdSolicitud());
            ps.setInt(2, acto.getSala().getIdSala());
            ps.setInt(3, acto.getNoDocenteP().getNoDocente());
            ps.setInt(4, acto.getNoDocenteS().getNoDocente());
            ps.setInt(5, acto.getNoDocenteV().getNoDocente());
            ps.setDate(6, new java.sql.Date(acto.getFechaPresentacion().getTime()));
            ps.setTime(7, new Time(acto.getHoraInicio().getTime()));
            ps.setTime(8, new Time(acto.getHoraFin().getTime()));
            ps.setString(9, acto.getDictamen());
            ps.setString(10, acto.getEstatus());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
                enviaCorreo = new EmailUtils().enviaCorreo(1, acto);
            }

        } catch (Exception ex) {
            Logger.getLogger(ActosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "{insertaRegistro: " + insertaRegistro + ", enviaCorreo: " + enviaCorreo + "}";
    }

    public String actualizaActo(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE ACTOS SET ID_SALA = ?, NO_DOCENTE_P = ?, NO_DOCENTE_S = ?, NO_DOCENTE_V = ?, FECHA_PRESENTACION = ?, HORA_INICIO = ?, HORA_FIN = ?, DICTAMEN = ?, ESTATUS = ? WHERE ID_ACTO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false,
                enviaCorreo = false;
        Actos acto;
        try {
            acto = gson.fromJson(datosJob.get("acto"), Actos.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setInt(1, acto.getSala().getIdSala());
            ps.setInt(2, acto.getNoDocenteP().getNoDocente());
            ps.setInt(3, acto.getNoDocenteS().getNoDocente());
            ps.setInt(4, acto.getNoDocenteV().getNoDocente());
            ps.setDate(5, new java.sql.Date(acto.getFechaPresentacion().getTime()));
            ps.setTime(6, new Time(acto.getHoraInicio().getTime()));
            ps.setTime(7, new Time(acto.getHoraFin().getTime()));
            ps.setString(8, acto.getDictamen());
            ps.setString(9, acto.getEstatus());
            ps.setInt(10, acto.getIdActo());

            if (ps.executeUpdate() > 0) {
                /*
                        P - pendiente
                        A - autorizado
                        R - realizado
                        C - cancelado
                 */
                actualizaRegistro = true;
                int operacion = acto.getEstatus().equals("A") ? 2 : acto.getEstatus().equals("R") ? 3 : 4;
                enviaCorreo = new EmailUtils().enviaCorreo(operacion, acto);
            }

        } catch (Exception ex) {
            Logger.getLogger(ActosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "{actualizaRegistro: " + actualizaRegistro + ", enviaCorreo: " + enviaCorreo + "}";
    }
}
