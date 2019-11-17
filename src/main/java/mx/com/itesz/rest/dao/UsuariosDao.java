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
import mx.com.itesz.rest.dto.Usuarios;
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

    public String insertaUsuario(Gson gson, JsonObject datosJob) throws Exception {
        String insert = "INSERT INTO USUARIOS(IDUSUARIO, EMAIL, CLAVE, NOMBRE, SEXO, TELEFONO, TIPO, ESTATUS) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        boolean insertaRegistro = false;
        Usuarios usuario;
        try {
            usuario = gson.fromJson(datosJob, Usuarios.class);

            ps = Conexion.getInstance().getCn().prepareStatement(insert);
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getSexo());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getTipo());
            ps.setString(8, usuario.getEstatus());

            if (ps.executeUpdate() > 0) {
                insertaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{insertaRegistro: " + insertaRegistro + "}";
    }

    public String actualizaUsuario(Gson gson, JsonObject datosJob) throws Exception {
        String update = "UPDATE USUARIOS SET EMAIL = ?, CLAVE = ?, NOMBRE = ?, SEXO = ?, TELEFONO = ?, TIPO = ?, ESTATUS = ? WHERE IDUSUARIO = ?";
        PreparedStatement ps = null;
        boolean actualizaRegistro = false;
        Usuarios usuario;
        try {
            usuario = gson.fromJson(datosJob, Usuarios.class);

            ps = Conexion.getInstance().getCn().prepareStatement(update);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getSexo());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getTipo());
            ps.setString(7, usuario.getEstatus());
            ps.setInt(8, usuario.getIdUsuario());

            if (ps.executeUpdate() > 0) {
                actualizaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{actualizaRegistro: " + actualizaRegistro + "}";
    }

    public String eliminaUsuario(Gson gson, JsonObject datosJob) throws Exception {
        String delete = "DELETE FROM USUARIOS WHERE IDUSUARIO = ?";
        PreparedStatement ps = null;
        boolean eliminaRegistro = false;
        Usuarios usuario;
        try {
            usuario = gson.fromJson(datosJob, Usuarios.class);

            ps = Conexion.getInstance().getCn().prepareStatement(delete);
            ps.setInt(1, usuario.getIdUsuario());

            if (ps.executeUpdate() > 0) {
                eliminaRegistro = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return "{eliminaRegistro: " + eliminaRegistro + "}";
    }

}
