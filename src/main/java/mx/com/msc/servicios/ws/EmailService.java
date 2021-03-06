
package mx.com.msc.servicios.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmailService", targetNamespace = "http://servicios.msc.mx.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmailService {


    /**
     * 
     * @param asunto
     * @param remitente
     * @param mensaje
     * @param destinatario
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "enviaCorreo", targetNamespace = "http://servicios.msc.mx.com/", className = "mx.com.msc.servicios.ws.EnviaCorreo")
    @ResponseWrapper(localName = "enviaCorreoResponse", targetNamespace = "http://servicios.msc.mx.com/", className = "mx.com.msc.servicios.ws.EnviaCorreoResponse")
    @Action(input = "http://servicios.msc.mx.com/EmailService/enviaCorreoRequest", output = "http://servicios.msc.mx.com/EmailService/enviaCorreoResponse")
    public boolean enviaCorreo(
        @WebParam(name = "remitente", targetNamespace = "")
        String remitente,
        @WebParam(name = "destinatario", targetNamespace = "")
        String destinatario,
        @WebParam(name = "asunto", targetNamespace = "")
        String asunto,
        @WebParam(name = "mensaje", targetNamespace = "")
        String mensaje);

}
