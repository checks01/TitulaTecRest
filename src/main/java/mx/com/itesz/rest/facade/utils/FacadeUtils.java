/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.facade.utils;

/**
 *
 * @author sergiov
 */
public class FacadeUtils {

    public String[] getHeaders(String metodo) {
        String headers[];
        switch (metodo) {
            case "getAdministrativos":
                headers = new String[]{"noEmpleado", "idPuesto", "idDepartamento", "idUsuario", "estatus"};
                break;
            case "getAlumnos":
                headers = new String[]{"noControl", "idCarrera", "idUsuario", "idEstatus", "promedio", "creditos"};
                break;
            case "getCarreras":
                headers = new String[]{"idCarrera", "siglas", "nombre", "creditos", "especialidad", "estatus", "noEmpleado"};
                break;
            case "getComentariosEvidencias":
                headers = new String[]{"idComentarioEvidencia", "idEvidencia", "fecha", "observaciones"};
                break;
            case "getDepartamentos":
                headers = new String[]{"idDepartamento", "nombre"};
                break;
            case "getEstatus":
                headers = new String[]{"idEstatus", "nombre"};
                break;
            case "getEvidencias":
                headers = new String[]{"idEvidencia", "idSolicitud", "nombre", "archivo", "extension", "estatus", "observaciones"};
                break;
            case "getOpciones":
                headers = new String[]{"idOpcion", "cveOpcion", "descripcion"};
                break;
            case "getPuestos":
                headers = new String[]{"idPuesto", "nombre"};
                break;
            case "getSalasDisponibles":
                headers = new String[]{"idSala", "cveSala", "descripcion"};
                break;
            case "getUsuarios":
                headers = new String[]{"idUsuario", "email", "clave", "nombre", "sexo", "telefono", "tipo", "estatus"};
                break;
            case "getActos":
                headers = new String[]{
                    "idActo",
                    "idSolicitud",
                    "alumno",
                    "nombreProyecto",
                    "opcionTitulacion",
                    "idSala",
                    "cveSala",
                    "sala",
                    "noDocenteP",
                    "nombreDocenteP",
                    "emailP",
                    "noDocenteS",
                    "nombreDocenteS",
                    "emailS",
                    "noDocenteV",
                    "nombreDocenteV",
                    "emailV",
                    "fechaPresentacion",
                    "horaInicio",
                    "horaFin",
                    "dictamen",
                    "cveEstatus",
                    "estatus"
                };
                break;
            case "getSolicitudesAprobadas":
                headers = new String[]{
                    "idSolicitud",
                    "idOpcion",
                    "cveOpcion",
                    "descripcion",
                    "fechaElaboracion",
                    "nombreProyecto",
                    "noControl",
                    "idUsuarioAlumno",
                    "nombreAlumno",
                    "emailAlumno",
                    "idCarrera",
                    "siglas",
                    "carrera",
                    "noEmpleado",
                    "idusuarioAdministrativo",
                    "nombreAdministrativo",
                    "emailAdministrativo",
                    "nombrePuesto",
                    "nombreDepartamento"};
                break;
            case "getDocentesActivos":
                headers = new String[]{
                    "noDocente",
                    "idCarrera",
                    "siglas",
                    "carrera",
                    "idUsuario",
                    "email",
                    "nombre",
                    "escolaridad",
                    "especialidad",
                    "cedula"
                };
                break;
            default:
                headers = new String[]{};
                break;
        }
        return headers;
    }
}
