/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.itesz.rest.dto.Carreras;
import mx.com.itesz.rest.dto.Docentes;
import mx.com.itesz.rest.dto.Usuarios;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class DocentesDao {

    public ArrayList<Docentes> getDocentesActivos() throws Exception {
        ArrayList<Docentes> lista = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT D.nodocente, ");
            query.append("       CAR.idcarrera, ");
            query.append("       CAR.siglas, ");
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

            st = Conexion.getInstance().getCn().createStatement();
            rs = st.executeQuery(query.toString());
            while (rs.next()) {
                Carreras carrera = new Carreras();
                carrera.setIdCarrera(rs.getInt("idcarrera"));
                carrera.setSiglas(rs.getString("siglas"));
                carrera.setNombre(rs.getString("carrera"));
                
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                
                Docentes docentes = new Docentes();
                docentes.setNoDocente(rs.getInt("nodocente"));
                docentes.setCarrera(carrera);
                docentes.setUsuario(usuario);
                docentes.setEscolaridad(rs.getString("escolaridad"));
                docentes.setEspecialidad(rs.getString("especialidad"));
                docentes.setCedula(rs.getString("cedula"));
                
                lista.add(docentes);
            }
        } catch (Exception ex) {
            Logger.getLogger(SolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return lista;
    }
}
