/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.service;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import mx.com.itesz.rest.dao.UsuariosDao;

/**
 *
 * @author sergiov
 */
@Path("/usuarios")
public class UsuariosService {
    private Gson gson;
    private UsuariosDao usuariosDao;

    public UsuariosService() {
        gson = new Gson();
        usuariosDao = new UsuariosDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaUsuarios")
    public String consultaUsuarios() throws Exception {
        return usuariosDao.getUsuarios();
    }
}
