/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author checo
 */
public class SalasDao {

    public String getSalasDisponibles(String fechaPresentacion, String horaInicio, String horaFin) throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{"idSala", "cveSala", "descripcion"};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM SALAS ");
            query.append("WHERE ID_SALA NOT IN( ");
            query.append("SELECT id_sala FROM ACTOS ");
            query.append("WHERE fecha_presentacion =  str_to_date(?, '%Y-%m-%d') ");
            query.append("and hora_inicio between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s') ");
            query.append("or hora_fin between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s'))");

            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            ps.setString(1, fechaPresentacion);
            ps.setString(2, horaInicio);
            ps.setString(3, horaFin);
            ps.setString(4, horaInicio);
            ps.setString(5, horaFin);
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
}
