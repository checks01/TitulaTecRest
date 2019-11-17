/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.facade.impl;

import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mx.com.msc.servicios.ws.EmailService;
import mx.com.msc.servicios.ws.EmailService_Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import mx.com.itesz.rest.facade.IFacade;
import mx.com.itesz.rest.facade.utils.FacadeUtils;
import mx.com.msc.servicios.ws.AnyTypeArray;
import mx.com.msc.servicios.ws.SalasDisponiblesService;
import mx.com.msc.servicios.ws.SalasDisponiblesService_Service;

/**
 *
 * @author sergiov
 */
public class FacadeUtilsImpl implements IFacade {

    private JSONArray registros;
    private JsonConfig config;
    private boolean success;
    private String mensaje;

    public FacadeUtilsImpl() {
        registros = new JSONArray();
        config = new JsonConfig();
        success = true;
        mensaje = "Proceso realizado correctamente";
    }

    @Override
    public List<Object[]> consultaSalasDisponibles(String fechaPresentacion, String horaInicio, String horaFin) {
        List<AnyTypeArray> listaSoap = new ArrayList<>();
        List<Object[]> lista = new ArrayList<>();
        try {
            SalasDisponiblesService_Service salasServiceSoap = new SalasDisponiblesService_Service();
            SalasDisponiblesService port = salasServiceSoap.getSalasDisponiblesServicePort();
            listaSoap = port.consultaSalasDisponibles(fechaPresentacion, horaInicio, horaFin);
            for (AnyTypeArray registro : listaSoap) {
                lista.add(registro.getItem().toArray());
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public String[] getHeadersConsulta(String metodo) {
        return new FacadeUtils().getHeaders(metodo);
    }

    @Override
    public String generaJsonString(List<Object[]> records, String metodo) {
        String jsonData = "",
                headers[];
        JSONObject datosJob;
        try {
            datosJob = new JSONObject();
            headers = this.getHeadersConsulta(metodo);

            for (Object[] registro : records) {
                for (int idx = 0; idx < headers.length; idx++) {
                    datosJob.put(headers[idx], registro[idx]);
                }
                registros.add(datosJob);
            }
        } catch (Exception ex) {
            success = false;
            mensaje = "Ocurrió un error durante el proceso: ".concat(ex.getMessage());
            System.err.println(ex.getMessage());
        }finally{
            jsonData = this.construyeJson();
        }
        return jsonData;
    }

    @Override
    public String generaJsonString(PreparedStatement ps, String metodo) {
        String jsonData = "";
        try {
            this.setJsonConfig();
            jsonData = this.construyeJson(ps, registros, metodo);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return jsonData;
    }

    @Override
    public boolean enviaCorreo(int opcion, JsonObject datosJob,
            java.util.Date fechaPresentacion
    ) {
        boolean success = false;
        String remitente = "",
                destinatarios = "",
                asunto = "",
                mensaje = "Por medio del presente se le notifica que se ha ";
        switch (opcion) {
            case 1:
                asunto = "Registro de nuevo acto de titulación";
                mensaje = mensaje.concat("registrado un nuevo acto de titulación con los siguientes datos:\n\n");
                break;
            case 2:
                asunto = "Autorización de acto de titulación";
                mensaje = mensaje.concat("autorizado el siguiente acto de titulación:\n\n");
                break;
            case 3:
                asunto = "Realización de acto de titulación";
                mensaje = mensaje.concat("realizado el siguiente acto de titulación:\n\n");
                break;
            case 4:
                asunto = "Cancelación de acto de titulación";
                mensaje = mensaje.concat("cancelado el acto de titulación descrito a continuación:\n\n");
                break;
        }
        remitente = datosJob.get("emailAlumno").getAsString();
        destinatarios = datosJob.get("emailAlumno").getAsString().concat(", ")
                .concat(datosJob.get("emailAdministrativo").getAsString().concat(", "))
                .concat(datosJob.get("emailDocenteP").getAsString().concat(", "))
                .concat(datosJob.get("emailDocenteS").getAsString().concat(", "))
                .concat(datosJob.get("emailDocenteV").getAsString());
        mensaje = this.construyeMensaje(mensaje, datosJob, fechaPresentacion);
        success = this.enviaCorreo(remitente, destinatarios, asunto, mensaje);
        return success;
    }

    private String construyeMensaje(String mensaje, JsonObject datosJob, java.util.Date fechaPresentacion) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
        mensaje = mensaje.concat("Nombre de proyecto: ").concat(datosJob.get("nombreProyecto").getAsString()).concat("\n");
        mensaje = mensaje.concat("Fecha de presentación: ").concat(sdf.format(fechaPresentacion)).concat("\n");
        mensaje = mensaje.concat("Hora de inicio: " + datosJob.get("horaInicio").getAsString()).concat(" hrs.\n");
        mensaje = mensaje.concat("Hora de término: " + datosJob.get("horaFin").getAsString()).concat(" hrs.\n\n");
        mensaje = mensaje.concat("DATOS DEL ALUMNO\n");
        mensaje = mensaje.concat("No. control: ").concat(String.valueOf(datosJob.get("noControl").getAsString())).concat("\n");
        mensaje = mensaje.concat("Nombre: ").concat(datosJob.get("nombreAlumno").getAsString()).concat("\n\n");
        mensaje = mensaje.concat("DATOS DEL CÓMITE\n");
        mensaje = mensaje.concat("Presidente: ").concat(datosJob.get("noDocenteP").getAsString().concat(" - ")
                .concat(datosJob.get("nombreDocenteP").getAsString())).concat("\n");
        mensaje = mensaje.concat("Secretario: ").concat(datosJob.get("noDocenteS").getAsString().concat(" - ")
                .concat(datosJob.get("nombreDocenteS").getAsString())).concat("\n");
        mensaje = mensaje.concat("Vocal: ").concat(datosJob.get("noDocenteV").getAsString().concat(" - ")
                .concat(datosJob.get("nombreDocenteV").getAsString())).concat("\n");
        return mensaje;
    }

    private boolean enviaCorreo(String remitente, String destinatarios, String asunto, String mensaje) {
        EmailService_Service emailService = new EmailService_Service();
        EmailService port = emailService.getEmailServicePort();
        return port.enviaCorreo(remitente, destinatarios, asunto, mensaje);
    }

    private void setJsonConfig() {
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    }

    private String construyeJson(PreparedStatement ps, JSONArray registros, String metodo) {
        ResultSet rs;
        JSONObject datosJob;
        String jsonData = "",
                headers[];
        try {
            rs = ps.executeQuery();
            headers = this.getHeadersConsulta(metodo);

            while (rs.next()) {
                datosJob = new JSONObject();
                Object[] record = new Object[headers.length];
                for (int i = 0, idxColumn = 1; i < headers.length; i++, idxColumn++) {
                    record[i] = rs.getObject(idxColumn);
                    datosJob.put(headers[i], String.valueOf(record[i]));
                }
                registros.add(datosJob, config);
            }
        } catch (Exception ex) {
            success = false;
            mensaje = "Ocurrió un error durante el proceso: ".concat(ex.getMessage());
            System.err.println(ex.getMessage());
        } finally {
            jsonData = this.construyeJson();
        }
        return jsonData;
    }

    private String construyeJson() {
        JSONArray datosJar = new JSONArray();
        HashMap<String, Object> jsonHsm = new HashMap<>();

        jsonHsm.put("success", success);
        jsonHsm.put("message", mensaje);
        jsonHsm.put("total", registros.size());
        jsonHsm.put("data", registros);

        datosJar = new JSONArray();
        datosJar.add(jsonHsm, config);
        return datosJar.size() > 0 ? datosJar.getJSONObject(0).toString() : datosJar.toString();
    }
}
