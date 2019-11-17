/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/insertaUsuario")
    public String insertaUsuario(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = usuariosDao.insertaUsuario(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/actualizaUsuario")
    public String actualizaUsuario(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = usuariosDao.actualizaUsuario(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/eliminaUsuario")
    public String eliminaUsuario(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = usuariosDao.eliminaUsuario(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
}
