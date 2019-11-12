/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.itesz.rest.dto.Actos;
import mx.com.itesz.rest.dto.Docentes;
import mx.com.itesz.rest.dto.Salas;
import mx.com.itesz.rest.dto.Solicitudes;
import mx.com.itesz.rest.utils.Conexion;

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
            query.append("       A.no_docente_s, ");
            query.append("       A.no_docente_v, ");
            query.append("       A.fecha_presentacion, ");
            query.append("       A.hora_inicio, ");
            query.append("       A.hora_fin, ");
            query.append("       A.dictamen, ");
            query.append("       A.estatus ");
            query.append("FROM   actos A ");

            if (idSolicitud > 0) {
                query.append("WHERE A.id_solicitud = ? ");
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

                Docentes docenteS = new Docentes();
                docenteS.setNoDocente(rs.getInt("no_docente_s"));

                Docentes docenteV = new Docentes();
                docenteV.setNoDocente(rs.getInt("no_docente_v"));
                
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

    public boolean insertaActo(Actos acto) throws Exception {
        String insert = "INSERT INTO ACTOS(ID_SOLICITUD, ID_SALA, NO_DOCENTE_P, NO_DOCENTE_S, NO_DOCENTE_V, FECHA_PRESENTACION, HORA_INICIO, HORA_FIN, DICTAMEN, ESTATUS VALUES(?,?,?,?,?,?,?,?,?,?))";
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, acto.getSolicitud().getIdSolicitud());
            ps.setInt(2, acto.getSala().getIdSala());
            ps.setInt(3, acto.getNoDocenteP().getNoDocente());
            ps.setInt(4, acto.getNoDocenteS().getNoDocente());
            ps.setInt(5, acto.getNoDocenteV().getNoDocente());
            ps.setDate(6, new java.sql.Date(acto.getFechaPresentacion().getTime()));
            ps.setDate(7, new java.sql.Date(acto.getHoraInicio().getTime()));
            ps.setDate(8, new java.sql.Date(acto.getHoraFin().getTime()));
            ps.setString(9, acto.getDictamen());
            ps.setString(10, acto.getEstatus());

            if (ps.executeUpdate() > 0) {
                success = true;
            }

        } catch (Exception ex) {
            Logger.getLogger(ActosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
}
