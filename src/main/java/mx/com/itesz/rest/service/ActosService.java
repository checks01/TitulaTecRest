/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.service;

import com.google.gson.Gson;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import mx.com.itesz.rest.dao.ActosDao;
import mx.com.itesz.rest.dto.Actos;

/**
 *
 * @author sergiov
 */
@Path("/actos")
public class ActosService {

    Gson gson;
    ActosDao actosDao;

    public ActosService() {
        gson = new Gson();
        actosDao = new ActosDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaActos")
    public String consultaActos(@QueryParam(value = "idSolicitud") int idSolicitud) throws Exception {
        return gson.toJson(actosDao.getActos(idSolicitud));
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/insertaActo")
    public boolean insertaActo(@WebParam(name = "data") String data) throws Exception {
        boolean success;
        Actos acto = gson.fromJson(data, Actos.class);
        success = actosDao.insertaActo(acto);
        return success;
    }
}
