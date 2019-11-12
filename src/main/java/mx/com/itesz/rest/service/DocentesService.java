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
import mx.com.itesz.rest.dao.DocentesDao;

/**
 *
 * @author sergiov
 */
@Path("/docentes")
public class DocentesService {

    private Gson gson;
    private DocentesDao docentesDao;

    public DocentesService() {
        gson = new Gson();
        docentesDao = new DocentesDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaDocentesActivos")
    public String consultaDocentesActivos() throws Exception {
        return gson.toJson(docentesDao.getDocentesActivos());
    }
}
