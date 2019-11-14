/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.utils;

import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import mx.com.msc.servicios.ws.EmailService;
import mx.com.msc.servicios.ws.EmailService_Service;

/**
 *
 * @author sergiov
 */
public class EmailUtils {

    public boolean enviaCorreo(int opcion, JsonObject datosJob, java.util.Date fechaPresentacion) {
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
//
    private String construyeMensaje(String mensaje, JsonObject datosJob, java.util.Date fechaPresentacion) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
        mensaje = mensaje.concat("Nombre de proyecto: ").concat(datosJob.get("nombreProyecto").getAsString()).concat("\n");
        mensaje = mensaje.concat("Fecha de presentación: ").concat(sdf.format(fechaPresentacion)).concat("\n");
        mensaje = mensaje.concat("Hora de inicio: " + datosJob.get("horaInicio").getAsString()).concat(" hrs.\n");
        mensaje = mensaje.concat("Hora de inicio: " + datosJob.get("horaFin").getAsString()).concat(" hrs.\n\n");
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

}
