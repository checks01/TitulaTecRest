/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import mx.com.itesz.rest.dao.ActosDao;

/**
 *
 * @author sergiov
 */
@Path("/actos")
public class ActosService {

    Gson gson;
    ActosDao actosDao;

    public ActosService() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        actosDao = new ActosDao();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/consultaActos")
    public String consultaActos(@QueryParam(value = "idSolicitud") int idSolicitud) throws Exception {
        return actosDao.getActos(idSolicitud);
    }

    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/insertaActo")
    /*
    data:{acto:{solicitud:{idSolicitud:3,nombreProyecto:'Generación de compras de proveedores a partir de comprobantes fiscales',alumno:{noControl:'06010255',usuario:{nombre:'Salvador Antonio Ayala Chávez',email:'sayala002@accitesz.com'}},administrativo:{noEmpleado:1,usuario:{nombre:'Zinzún 1',email:'isc.sva@gmail.com'}}},sala:{idSala:1},noDocenteP:{noDocente:1,usuario:{nombre:'Edgar Rojas',email:'svalle016@accitesz.com'}},noDocenteS:{noDocente:2,usuario:{nombre:'Ana Celia Segundo',email:'svalle016@accitesz.com'}},noDocenteV:{noDocente:3,usuario:{nombre:'Aarón Jr',email:'svalle016@accitesz.com'}},fechaPresentacion:'2019-11-12',horaInicio:'11:00:00 AM',horaFin:'02:00:00 PM',dictamen:'',estatus:'P'}}
    data2:{idSolicitud:3,idSala:1,noDocenteP:1,nombreDocenteP:'Edgar Rojas',emailDocenteP:'svalle016@accitesz.com',noDocenteS:2,nombreDocenteS:'Ana Celia Segundo',emailDocenteS:'svalle016@accitesz.com',noDocenteV:3,nombreDocenteV:'Aarón Jr',emailDocenteV:'svalle016@accitesz.com',fechaPresentacion:'2019-11-12',horaInicio:'11:00:00',horaFin:'14:00:00',dictamen:'chido vato',estatus:'P',nombreProyecto:'Generación de compras de proveedores a partir de comprobantes fiscales',noControl:'06010255',nombreAlumno:'Salvador Antonio Ayala Chávez',emailAlumno:'sayala002@accitesz.com',nombreAdministrativo:'Zinzún 1',emailAdministrativo:'isc.sva@gmail.com'}
    */
    public String insertaActo(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = actosDao.insertaActo(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
//    
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/actualizaActo")
    /*
    data:{acto:{idActo:29,solicitud:{idSolicitud:3,nombreProyecto:'Generación de compras de proveedores a partir de comprobantes fiscales',alumno:{noControl:'06010255',usuario:{nombre:'Salvador Antonio Ayala Chávez',email:'sayala002@accitesz.com'}},administrativo:{noEmpleado:1,usuario:{nombre:'Zinzún 1',email:'isc.sva@gmail.com'}}},sala:{idSala:1},noDocenteP:{noDocente:4,usuario:{nombre:'Omar Diaz Campos',email:'svalle016@accitesz.com'}},noDocenteS:{noDocente:5,usuario:{nombre:'Rafael Campos Carmona',email:'svalle016@accitesz.com'}},noDocenteV:{noDocente:6,usuario:{nombre:'Aldo Martinez Vargas',email:'svalle016@accitesz.com'}},fechaPresentacion:'2019-11-14',horaInicio:'09:00:00 AM',horaFin:'12:00:00 PM',dictamen:'APROBADO',estatus:'A'}}
    data2:{idActo:32,idSolicitud:3,idSala:2,noDocenteP:4,nombreDocenteP:'Omar Diaz Campos',emailDocenteP:'svalle016@accitesz.com',noDocenteS:5,nombreDocenteS:'Rafael Campos Carmona',emailDocenteS:'svalle016@accitesz.com',noDocenteV:6,nombreDocenteV:'Aldo Martinez Vargas',emailDocenteV:'svalle016@accitesz.com',fechaPresentacion:'2019-11-14',horaInicio:'09:00:00',horaFin:'12:00:00',dictamen:'APROBADO',estatus:'A',nombreProyecto:'Generación de compras de proveedores a partir de comprobantes fiscales',noControl:'06010255',nombreAlumno:'Salvador Antonio Ayala Chávez',emailAlumno:'sayala002@accitesz.com',nombreAdministrativo:'Zinzún 1',emailAdministrativo:'isc.sva@gmail.com'}
    */
    public String actualizaActo(@WebParam(name = "data") String data) throws Exception {
        String respuesta;
        JsonObject datosJob = new JsonParser().parse(data).getAsJsonObject();
        respuesta = actosDao.actualizaActo(gson, datosJob);
        return gson.toJson(new JsonParser().parse(respuesta).getAsJsonObject());
    }
}
