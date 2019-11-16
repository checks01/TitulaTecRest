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
 * @author sergiov
 */
public class UsuariosDao {
    
    public String getUsuarios() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{"idUsuario", "email", "clave", "nombre", "sexo", "telefono", "tipo", "estatus"};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM USUARIOS ");
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
    
}
