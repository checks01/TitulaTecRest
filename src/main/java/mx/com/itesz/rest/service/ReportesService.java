/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import mx.com.itesz.rest.interfaces.ParametrosReporte;
import mx.com.msc.servicios.ws.GeneraReporte.Parametros;
import mx.com.msc.servicios.ws.Reportes;
import mx.com.msc.servicios.ws.Reportes_Service;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author chavon
 *
 */
public class ReportesService {

    public File getReporte(String nombreReporte, String extension, String parametrosReporte) {
        byte[] decodeBuffer;
        String reporteBase64;
        File reporteFile = null;
        try {
            reporteBase64 = generaReporte(nombreReporte, extension, getParametrosReporte(parametrosReporte));
            decodeBuffer = new Base64().decode(reporteBase64);
            reporteFile = new File(nombreReporte.concat(".").concat(extension));
            FileUtils.writeByteArrayToFile(reporteFile, decodeBuffer);
        } catch (IOException e) {
            System.out.println("Error titulatec:" + e.getMessage());
        }
        return reporteFile;

    }

    private static String generaReporte(String nombreReporte, String extension, Parametros parametros) {
        Reportes_Service service = new Reportes_Service();
        Reportes port = service.getReportesPort();
        return port.generaReporte(nombreReporte, extension, parametros);
    }

    private ParametrosReporte getParametrosReporte(String parametros) {
        ParametrosReporte pr = new ParametrosReporte();
        try {
            if (!parametros.isEmpty()) {
                JsonObject jsonObject = new JsonParser().parse(parametros).getAsJsonObject();
                Set<Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    String key = entry.getKey();
                    pr.put(key, String.valueOf(jsonObject.get(key)).replace("\"", ""));
                }
            }

        } catch (Exception e) {
            System.out.println("Error titulatec:" + e.getMessage());
        }
        return pr;
    }
}
