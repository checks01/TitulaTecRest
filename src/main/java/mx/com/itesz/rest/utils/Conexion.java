/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author sergiov
 */
public class Conexion {

    public static Conexion conexion;
    Connection cn;

    private Conexion() throws Exception {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:app/titulatec");
            cn = ds.getConnection();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static Conexion getInstance() throws Exception {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;

    }

    public Connection getCn() {
        return cn;
    }

    public void cerrar() throws SQLException {
        cn.close();
        conexion = null;
    }
}
