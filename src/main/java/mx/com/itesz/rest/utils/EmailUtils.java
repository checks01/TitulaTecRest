/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.utils;

import java.text.SimpleDateFormat;
import mx.com.itesz.rest.dto.Actos;
import mx.com.msc.servicios.ws.EmailService;
import mx.com.msc.servicios.ws.EmailService_Service;

/**
 *
 * @author sergiov
 */
public class EmailUtils {

    public boolean enviaCorreo(int opcion, Actos acto) {
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
        remitente = acto.getSolicitud().getAlumno().getUsuario().getEmail();
        destinatarios = acto.getSolicitud().getAlumno().getUsuario().getEmail().concat(", ")
                .concat(acto.getSolicitud().getAdministrativo().getUsuario().getEmail().concat(", "))
                .concat(acto.getNoDocenteP().getUsuario().getEmail().concat(", "))
                .concat(acto.getNoDocenteS().getUsuario().getEmail().concat(", "))
                .concat(acto.getNoDocenteV().getUsuario().getEmail());
        mensaje = this.construyeMensaje(mensaje, acto);
        success = this.enviaCorreo(remitente, destinatarios, asunto, mensaje);
        return success;
    }

    private String construyeMensaje(String mensaje, Actos acto) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
        mensaje = mensaje.concat("Nombre de proyecto: ").concat(acto.getSolicitud().getNombreProyecto()).concat("\n");
        mensaje = mensaje.concat("Fecha de presentación: ").concat(sdf.format(acto.getFechaPresentacion())).concat("\n");
        mensaje = mensaje.concat("Hora de inicio: " + acto.getHoraInicio()).concat(" hrs.\n");
        mensaje = mensaje.concat("Hora de inicio: " + acto.getHoraFin()).concat(" hrs.\n\n");
        mensaje = mensaje.concat("DATOS DEL ALUMNO\n");
        mensaje = mensaje.concat("No. control: ").concat(String.valueOf(acto.getSolicitud().getAlumno().getNoControl())).concat("\n");
        mensaje = mensaje.concat("Nombre: ").concat(acto.getSolicitud().getAlumno().getUsuario().getNombre()).concat("\n\n");
        mensaje = mensaje.concat("DATOS DEL CÓMITE\n");
        mensaje = mensaje.concat("Presidente: ").concat(String.valueOf(acto.getNoDocenteP().getNoDocente()).concat(" - ")
                .concat(acto.getNoDocenteP().getUsuario().getNombre())).concat("\n");
        mensaje = mensaje.concat("Secretario: ").concat(String.valueOf(acto.getNoDocenteS().getNoDocente()).concat(" - ")
                .concat(acto.getNoDocenteS().getUsuario().getNombre())).concat("\n");
        mensaje = mensaje.concat("Vocal: ").concat(String.valueOf(acto.getNoDocenteV().getNoDocente()).concat(" - ")
                .concat(acto.getNoDocenteV().getUsuario().getNombre())).concat("\n");
        return mensaje;
    }

    private boolean enviaCorreo(String remitente, String destinatarios, String asunto, String mensaje) {
        EmailService_Service emailService = new EmailService_Service();
        EmailService port = emailService.getEmailServicePort();
        return port.enviaCorreo(remitente, destinatarios, asunto, mensaje);
    }

}
