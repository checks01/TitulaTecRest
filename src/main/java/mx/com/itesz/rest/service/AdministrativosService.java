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
import mx.com.itesz.rest.dao.AdministrativosDao;

/**
 *
 * @author sergiov
 */
@Path("/administrativos")
public class AdministrativosService {
    private Gson gson;
    private AdministrativosDao administrativosDao;

    public AdministrativosService() {
        gson = new Gson();
        administrativosDao = new AdministrativosDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaAdministrativos")
    public String consultaAdministrativos() throws Exception {
        return administrativosDao.getAdministrativos();
    }
    
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/insertaAdministrativo")
    public String insertaAdministrativo(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = administrativosDao.insertaAdministrativo(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
    
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/actualizaAdministrativo")
    public String actualizaAdministrativo(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = administrativosDao.actualizaAdministrativo(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
    
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/eliminaAdministrativo")
    public String eliminaAdministrativo(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = administrativosDao.eliminaAdministrativo(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
}
