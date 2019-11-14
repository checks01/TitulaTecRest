/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    "idCarrera",
                    "siglas",
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
}
