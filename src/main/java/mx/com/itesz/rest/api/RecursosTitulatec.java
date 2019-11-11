/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.api;

import java.io.File;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import mx.com.itesz.rest.service.ReportesService;

/**
 *
 * @author chavon
 */
@Path("/recursosTitulatec")
public class RecursosTitulatec {
    
    @GET
    public String HelloWorld(String params) {
        return "Hola mundo";
    }

    /**
     *
     * @param nombreReporte
     * @param extension
     * @param parametrosReporte {nombreReporte, Extension, [P_?]}
     * @return
     */
    @GET
    @Path("/descargaReporte")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargaReporte(@QueryParam(value = "nombreReporte") String nombreReporte,
            @QueryParam(value = "extension") String extension,
            @QueryParam(value = "parametrosReporte") String parametrosReporte) throws IOException {
        ReportesService service = new ReportesService();
        File file = service.getReporte(nombreReporte, extension, parametrosReporte);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=".concat(nombreReporte.concat(".").concat(extension)));
        return response.build();
    }
}
