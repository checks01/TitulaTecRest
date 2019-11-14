/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import mx.com.itesz.rest.utils.Conexion;
import mx.com.itesz.rest.utils.FormUtil;

/**
 *
 * @author sergiov
 */
public class SolicitudesDao {

    public String getSolicitudesAprobadas() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        String jsonData = "",
                mapping[] = new String[]{
                    "idSolicitud",
                    "idOpcion",
                    "cveOpcion",
                    "descripcion",
                    "fechaElaboracion",
                    "nombreProyecto",
                    "noControl",
                    "idUsuarioAlumno",
                    "nombreAlumno",
                    "idCarrera",
                    "siglas",
                    "carrera",
                    "noEmpleado",
                    "idusuarioAdministrativo",
                    "nombreAdministrativo",
                    "nombrePuesto",
                    "nombreDepartamento",};
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT S.id_solicitud, ");
            query.append("       OP.id_opcion, ");
            query.append("       OP.cve_opcion, ");
            query.append("       OP.descripcion, ");
            query.append("       S.fecha_elaboracion, ");
            query.append("       S.nombre_proyecto, ");
            query.append("       AL.nocontrol, ");
            query.append("       UAL.IDUSUARIO AS ID_USUARIO_AL, ");
            query.append("       UAL.nombre AS NOMBRE_AL, ");
            query.append("       CAR.idcarrera, ");
            query.append("       CAR.siglas, ");
            query.append("       CAR.nombre as CARRERA, ");
            query.append("       AD.noempleado, ");
            query.append("       UAD.IDUSUARIO AS ID_USUARIO_AD, ");
            query.append("       UAD.nombre AS NOMBRE_AD, ");
            query.append("       PU.nombre  AS NOMBRE_PUESTO, ");
            query.append("       DEP.nombre AS NOMBRE_DEPARTAMENTO ");
            query.append("FROM   solicitudes S, ");
            query.append("       alumnos AL, ");
            query.append("       administrativos AD, ");
            query.append("       opciones OP, ");
            query.append("       carreras CAR, ");
            query.append("       puestos PU, ");
            query.append("       departamentos DEP, ");
            query.append("       usuarios UAL, ");
            query.append("       usuarios UAD ");
            query.append("WHERE  S.nocontrol = AL.nocontrol ");
            query.append("       AND AL.idcarrera = CAR.idcarrera ");
            query.append("       AND S.noempleado = AD.noempleado ");
            query.append("       AND AD.idpuesto = PU.idpuesto ");
            query.append("       AND AD.iddepartamento = DEP.iddepartamento ");
            query.append("       AND S.id_opcion = OP.id_opcion ");
            query.append("       AND AL.idusuario = UAL.idusuario ");
            query.append("       AND AD.idusuario = UAD.idusuario ");
            query.append("       AND S.estatus = 'A'");

            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            lista = FormUtil.executeQuery(ps);
            jsonData = FormUtil.generaJsonString(true, "Proceso realizado correctamente", lista.size(), lista, mapping);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return jsonData;
    }
}
