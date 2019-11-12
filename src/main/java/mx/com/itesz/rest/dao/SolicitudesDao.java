/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.itesz.rest.dto.Administrativos;
import mx.com.itesz.rest.dto.Alumnos;
import mx.com.itesz.rest.dto.Carreras;
import mx.com.itesz.rest.dto.Opciones;
import mx.com.itesz.rest.dto.Solicitudes;
import mx.com.itesz.rest.dto.Usuarios;
import mx.com.itesz.rest.utils.Conexion;

/**
 *
 * @author sergiov
 */
public class SolicitudesDao {

    public ArrayList<Solicitudes> getSolicitudesAprobadas() throws Exception {
        ArrayList<Solicitudes> lista = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
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
            query.append("       CAR.idcarrera, ");
            query.append("       CAR.siglas, ");
            query.append("       CAR.nombre as CARRERA, ");
            query.append("       AD.noempleado, ");
            query.append("       UAD.IDUSUARIO AS ID_USUARIO_AD, ");
            query.append("       UAD.nombre AS NOMBRE_AD, ");
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

            st = Conexion.getInstance().getCn().createStatement();
            rs = st.executeQuery(query.toString());
            while (rs.next()) {
                Opciones op = new Opciones();
                op.setIdOpcion(rs.getInt("id_opcion"));
                op.setCveOpcion(rs.getString("cve_opcion"));
                op.setDescripcion(rs.getString("descripcion"));

                Usuarios usAl = new Usuarios();
                usAl.setIdUsuario(rs.getInt("ID_USUARIO_AL"));
                usAl.setNombre(rs.getString("NOMBRE_AL"));

                Carreras car = new Carreras();
                car.setIdCarrera(rs.getInt("idcarrera"));
                car.setSiglas(rs.getString("siglas"));
                car.setNombre(rs.getString("CARRERA"));

                Alumnos al = new Alumnos();
                al.setNoControl(rs.getInt("nocontrol"));
                al.setUsuario(usAl);
                al.setCarrera(car);

                Usuarios usAdm = new Usuarios();
                usAdm.setIdUsuario(rs.getInt("ID_USUARIO_AD"));
                usAdm.setNombre(rs.getString("NOMBRE_AD"));

                Administrativos adm = new Administrativos();
                adm.setNoEmpleado(rs.getInt("noempleado"));
                adm.setUsuario(usAdm);

                Solicitudes sol = new Solicitudes();
                sol.setIdSolicitud(rs.getInt("id_solicitud"));
                sol.setAlumno(al);
                sol.setAdministrativo(adm);
                sol.setOpcion(op);
                sol.setFechaElaboracion(rs.getDate("fecha_elaboracion"));
                sol.setNombreProyecto(rs.getString("nombre_proyecto"));

                lista.add(sol);
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
