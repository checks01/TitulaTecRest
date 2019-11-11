/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itesz.rest.interfaces;

import mx.com.msc.servicios.ws.GeneraReporte;
import mx.com.msc.servicios.ws.GeneraReporte.Parametros;

/**
 *
 * @author chavon
 */
public class ParametrosReporte extends Parametros implements IParametros {

    @Override
    public void put(String clave, Object valor) {
        GeneraReporte.Parametros.Entry c = new GeneraReporte.Parametros.Entry();
        c.setKey(clave);
        c.setValue(valor);
        this.getEntry().add(c);
    }

}
