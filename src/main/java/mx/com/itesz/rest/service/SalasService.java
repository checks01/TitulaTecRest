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
import javax.ws.rs.QueryParam;
import mx.com.itesz.rest.dao.SalasDao;

/**
 *
 * @author checo
 */
@Path("/salas")
public class SalasService {

    private Gson gson;
    private SalasDao salasDao;

    public SalasService() {
        gson = new Gson();
        salasDao = new SalasDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaSalasDisponibles")
    public String consultaSalasDisponibles(
            @QueryParam(value = "fechaPresentacion") String fechaPresentacion,
            @QueryParam(value = "horaInicio") String horaInicio,
            @QueryParam(value = "horaFin") String horaFin) throws Exception {
        return salasDao.getSalasDisponibles(fechaPresentacion, horaInicio, horaFin);
    }

}
