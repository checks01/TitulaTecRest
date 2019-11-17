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
import mx.com.itesz.rest.dao.EvidenciasDao;

/**
 *
 * @author sergiov
 */
@Path("/evidencias")
public class EvidenciasService {

    private Gson gson;
    private EvidenciasDao evidenciasDao;

    public EvidenciasService() {
        gson = new Gson();
        evidenciasDao = new EvidenciasDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaEvidencias")
    public String consultaEvidencias() throws Exception {
        return evidenciasDao.getEvidencias();
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/insertaEvidencia")
    public String insertaEvidencia(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = evidenciasDao.insertaEvidencia(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/actualizaEvidencia")
    public String actualizaEvidencia(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = evidenciasDao.actualizaEvidencia(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/eliminaEvidencia")
    public String eliminaEvidencia(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = evidenciasDao.eliminaEvidencia(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
}
