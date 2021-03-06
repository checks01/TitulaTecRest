
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
@WebServiceClient(name = "SalasDisponiblesService", targetNamespace = "http://servicios.msc.mx.com/", wsdlLocation = "http://localhost:8080/UtilsTecWs/SalasDisponiblesService?wsdl")
public class SalasDisponiblesService_Service
    extends Service
{

    private final static URL SALASDISPONIBLESSERVICE_WSDL_LOCATION;
    private final static WebServiceException SALASDISPONIBLESSERVICE_EXCEPTION;
    private final static QName SALASDISPONIBLESSERVICE_QNAME = new QName("http://servicios.msc.mx.com/", "SalasDisponiblesService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/UtilsTecWs/SalasDisponiblesService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SALASDISPONIBLESSERVICE_WSDL_LOCATION = url;
        SALASDISPONIBLESSERVICE_EXCEPTION = e;
    }

    public SalasDisponiblesService_Service() {
        super(__getWsdlLocation(), SALASDISPONIBLESSERVICE_QNAME);
    }

    public SalasDisponiblesService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SALASDISPONIBLESSERVICE_QNAME, features);
    }

    public SalasDisponiblesService_Service(URL wsdlLocation) {
        super(wsdlLocation, SALASDISPONIBLESSERVICE_QNAME);
    }

    public SalasDisponiblesService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SALASDISPONIBLESSERVICE_QNAME, features);
    }

    public SalasDisponiblesService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SalasDisponiblesService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SalasDisponiblesService
     */
    @WebEndpoint(name = "SalasDisponiblesServicePort")
    public SalasDisponiblesService getSalasDisponiblesServicePort() {
        return super.getPort(new QName("http://servicios.msc.mx.com/", "SalasDisponiblesServicePort"), SalasDisponiblesService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SalasDisponiblesService
     */
    @WebEndpoint(name = "SalasDisponiblesServicePort")
    public SalasDisponiblesService getSalasDisponiblesServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicios.msc.mx.com/", "SalasDisponiblesServicePort"), SalasDisponiblesService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SALASDISPONIBLESSERVICE_EXCEPTION!= null) {
            throw SALASDISPONIBLESSERVICE_EXCEPTION;
        }
        return SALASDISPONIBLESSERVICE_WSDL_LOCATION;
    }

}
