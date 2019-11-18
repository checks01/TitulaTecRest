
package mx.com.msc.servicios.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "UsuariosService", targetNamespace = "http://servicios.msc.mx.com/", wsdlLocation = "http://localhost:8080/UtilsTecWs/UsuariosService?wsdl")
public class UsuariosService_Service
    extends Service
{

    private final static URL USUARIOSSERVICE_WSDL_LOCATION;
    private final static WebServiceException USUARIOSSERVICE_EXCEPTION;
    private final static QName USUARIOSSERVICE_QNAME = new QName("http://servicios.msc.mx.com/", "UsuariosService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/UtilsTecWs/UsuariosService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        USUARIOSSERVICE_WSDL_LOCATION = url;
        USUARIOSSERVICE_EXCEPTION = e;
    }

    public UsuariosService_Service() {
        super(__getWsdlLocation(), USUARIOSSERVICE_QNAME);
    }

    public UsuariosService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), USUARIOSSERVICE_QNAME, features);
    }

    public UsuariosService_Service(URL wsdlLocation) {
        super(wsdlLocation, USUARIOSSERVICE_QNAME);
    }

    public UsuariosService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, USUARIOSSERVICE_QNAME, features);
    }

    public UsuariosService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UsuariosService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns UsuariosService
     */
    @WebEndpoint(name = "UsuariosServicePort")
    public UsuariosService getUsuariosServicePort() {
        return super.getPort(new QName("http://servicios.msc.mx.com/", "UsuariosServicePort"), UsuariosService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UsuariosService
     */
    @WebEndpoint(name = "UsuariosServicePort")
    public UsuariosService getUsuariosServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicios.msc.mx.com/", "UsuariosServicePort"), UsuariosService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (USUARIOSSERVICE_EXCEPTION!= null) {
            throw USUARIOSSERVICE_EXCEPTION;
        }
        return USUARIOSSERVICE_WSDL_LOCATION;
    }

}
