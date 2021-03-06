/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.facade;

import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author sergiov
 */
public interface IFacade {
    
    public List<Object[]> consultaSalasDisponibles(String fechaPresentacion, String horaInicio, String horaFin);
    
    public String[] getHeadersConsulta(String metodo);

    public String generaJsonString(List<Object[]> registros, String metodo);
    
    public String generaJsonString(PreparedStatement ps, String metodo);
    
    public boolean enviaCorreo(int opcion, JsonObject datosJob, java.util.Date fechaPresentacion);
}
