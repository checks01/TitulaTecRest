/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 *
 * @author sergiov
 */
public class FormUtil {

    public static List<Object[]> executeQuery(PreparedStatement ps) {
        List<Object[]> lista = new ArrayList<>();
        ResultSet rs;
        int columns;
        try {
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            columns = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] record = new Object[columns];
                for (int idxRec = 0, idxColumn = 1; idxColumn <= columns; idxRec++, idxColumn++) {
                    record[idxRec] = rs.getObject(idxColumn);
                }
                lista.add(record);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public static String generaJsonString(boolean success, String message, Integer total, List<Object[]> data, String[] headers) {
        String jsonData = "";
        HashMap<String, Object> jsonHsm = new HashMap<>();
        JSONArray jsonArray,
                datosJar;
        JSONObject datosJob;
        try {
            jsonArray = new JSONArray();

            JsonConfig config = new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

            for (Object[] registro : data) {
                datosJob = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    datosJob.put(headers[i], String.valueOf(registro[i]));
                }
                jsonArray.add(datosJob, config);
            }

            jsonHsm.put("success", success);
            jsonHsm.put("message", message);
            jsonHsm.put("total", total != null ? total : 0);
            jsonHsm.put("data", jsonArray);

            datosJar = new JSONArray();
            datosJar.add(jsonHsm, config);
            jsonData = datosJar.size() > 0 ? datosJar.getJSONObject(0).toString() : datosJar.toString();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return jsonData;
    }
}
