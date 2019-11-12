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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import mx.com.itesz.rest.dao.SolicitudesDao;

/**
 *
 * @author sergiov
 */
@Path("/solicitudes")
public class SolicitudesService {

    private Gson gson;
    private SolicitudesDao solicitudesDao;

    @Context
    private UriInfo context;

    public SolicitudesService() {
        gson = new Gson();
        solicitudesDao = new SolicitudesDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/solicitudesAprobadas")
    public String solicitudesAprobadas() throws Exception {
        return gson.toJson(solicitudesDao.getSolicitudesAprobadas());
    }
}
